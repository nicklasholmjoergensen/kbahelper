package io.peqo.kbahelper.activity.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.model.Bed;
import io.peqo.kbahelper.model.Department;
import io.peqo.kbahelper.model.Patient;
import io.peqo.kbahelper.model.Requestor;
import io.peqo.kbahelper.model.Requisition;
import io.peqo.kbahelper.model.Room;
import io.peqo.kbahelper.model.Sample;
import io.peqo.kbahelper.repository.BedRepositoryImpl;
import io.peqo.kbahelper.repository.DepartmentRepositoryImpl;
import io.peqo.kbahelper.repository.PatientRepositoryImpl;
import io.peqo.kbahelper.repository.RequestorRepositoryImpl;
import io.peqo.kbahelper.repository.RequisitionRepositoryImpl;
import io.peqo.kbahelper.repository.RoomRepositoryImpl;
import io.peqo.kbahelper.repository.SampleRepositoryImpl;

import static io.peqo.kbahelper.R.color.colorWhite;
import static io.peqo.kbahelper.R.drawable.ic_done_black_24dp;
import static io.peqo.kbahelper.R.drawable.ic_done_green_24dp;

public class RequisitionFragment extends Fragment {

    private static final String TAG = "Requisition Fragment";
    private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    // Model objects for UI
    private Requisition requisition;
    private Patient patient;
    private Bed bed;
    private Room room;
    private Department department;
    private Requestor requestor;
    private List<Sample> samples;
    private List<TextView> textViews;

    // Requisition ID passed from activity
    private Long reqId;

    // Keep track of samples
    int count = 0;

    // See if bracelet is scanned
    private boolean braceletScanned;

    // Bind views
    @BindView(R.id.layoutReqRequestorContainer) LinearLayout requestorContainer;
    @BindView(R.id.layoutReqRequestorDesc) LinearLayout requestorDescription;
    @BindView(R.id.btnReqExpand) ImageView expandRequestor;
    @BindView(R.id.layoutReqSamples) LinearLayout samplesLayout;
    @BindView(R.id.reqFinishIcon) LinearLayout reqFinishIcon;
    @BindView(R.id.btnReqScanBracelet) Button scanBracelet;
    @BindView(R.id.btnReqScanSample) Button scanSample;
    @BindView(R.id.textReqPatientName) TextView patientName;
    @BindView(R.id.textReqPatientCpr) TextView patientCpr;
    @BindView(R.id.textReqPatientDept) TextView patientDept;
    @BindView(R.id.textReqPatientRoom) TextView patientRoom;
    @BindView(R.id.textReqPatientBed) TextView patientBed;
    @BindView(R.id.textReqRequestorName) TextView requestorName;
    @BindView(R.id.textReqRequestorDepartment) TextView requestorDepartment;
    @BindView(R.id.textReqRequestorAddress) TextView requestorAddress;
    @BindView(R.id.textReqRequestorZip) TextView requestorZip;
    @BindView(R.id.textReqRequestorCountry) TextView requestorCountry;
    @BindView(R.id.textReqTestDate) TextView requisitionDate;
    @BindView(R.id.textReqFulfilledDate) TextView requisitionFulfilledDate;
    @BindView(R.id.textReqDescription) TextView requisitionDescription;

    @BindView(R.id.requisitionProgressLayout) LinearLayout progressLayout;
    @BindView(R.id.requisitionProgress) ProgressBar progressBar;

    // Repositories
    private final BedRepositoryImpl bedRepository = new BedRepositoryImpl();
    private final DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl();
    private final PatientRepositoryImpl patientRepository  = new PatientRepositoryImpl();
    private final RequestorRepositoryImpl requestorRepository  = new RequestorRepositoryImpl();
    private final RequisitionRepositoryImpl requisitionRepository = new RequisitionRepositoryImpl();
    private final RoomRepositoryImpl roomRepository = new RoomRepositoryImpl();
    private final SampleRepositoryImpl sampleRepository = new SampleRepositoryImpl();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requisition_single, container, false);
        ButterKnife.bind(this, view);
        reqId = this.getArguments().getLong("id");
        new RetrieveRequisitionFromApi().execute();
        return view;
    }

    @OnClick(R.id.btnReqScanSample)
    public void scanSample() {
        if(braceletScanned) {
            new UpdateSampleFromApi().execute();
        } else {
            Toast.makeText(getActivity(), "Armbånd endnu ikke registreret.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btnReqScanBracelet)
    public void scanBracelet() {
        this.braceletScanned = true;
        Toast.makeText(getActivity(), "Armbånd registreret.", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.layoutReqRequestorContainer)
    public void toggleRequestorInfo() {
        toggleVisibility(requestorDescription);
    }

    private void toggleVisibility(final View view) {
        if(view.getVisibility() == View.GONE) {
            expandRequestor.setImageResource(R.drawable.ic_expand_less_black_24dp);
            view.setVisibility(View.VISIBLE);
        } else {
            expandRequestor.setImageResource(R.drawable.ic_expand_more_black_24dp);
            view.setVisibility(View.GONE);
        }
    }

    public void onBackPressed() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.remove(this);
        ft.commit();
        manager.popBackStack();
    }

    private class RetrieveRequisitionFromApi extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            requisition = requisitionRepository.fetchObject(reqId);
            patient = patientRepository.fetchObject(requisition.patientId);
            requestor = requestorRepository.fetchObject(requisition.requestorId);
            bed = bedRepository.fetchObject(patient.id);
            room = roomRepository.fetchObject(bed.roomId);
            department = departmentRepository.fetchObject(room.departmentId);
            samples = sampleRepository.fetchAllFromId(requisition.id);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            requisitionDate.setText(df.format(requisition.orderDate));
            patientName.setText(patient.firstName + " " + patient.lastName);
            patientCpr.setText(patient.cprNum);
            patientBed.setText(String.valueOf(bed.bedNumber));
            patientRoom.setText(String.valueOf(room.roomNumber));
            patientDept.setText(department.name);
            requestorName.setText(requestor.name);
            requestorDepartment.setText(requestor.department);
            requestorAddress.setText(requestor.address);
            requestorZip.setText(requestor.postalCode);
            requestorCountry.setText(requestor.country);

            if(!requisition.description.isEmpty()) requisitionDescription.setText(requisition.description);
            if(requisition.fulfilledDate != null) requisitionFulfilledDate.setText(df.format(requisition.fulfilledDate));

            if(samples.size() > 0) {
                textViews = new ArrayList<>();
                for(int i = 0; i < samples.size(); i++) {
                    final TextView text = new TextView(getActivity());
                    final LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    llp.setMargins(0, 0, 20, 0);
                    text.setLayoutParams(llp);
                    text.setBackgroundColor(Color.parseColor(samples.get(i).colorCode));
                    text.setText(samples.get(i).amount);
                    text.setTextColor(ContextCompat.getColor(getContext(), colorWhite));
                    text.setTextSize(18);
                    text.setId(i);
                    text.setPadding(12, 12, 12, 12);
                    textViews.add(text);
                    samplesLayout.addView(text);
                }
            }

            if(requisition.status == 3) {
                final ImageView img = new ImageView(getActivity());
                final LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );
                img.setLayoutParams(llp);
                img.setImageResource(ic_done_green_24dp);

                reqFinishIcon.addView(img);

                Toast.makeText(getActivity(), "Rekvisition afsluttet.", Toast.LENGTH_SHORT).show();
            }

            getActivity().setTitle("Rekvisition: #" + requisition.reqNum);
            progressLayout.setVisibility(View.GONE);
        }
    }

    private class UpdateRequisitionFromApi extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            final Requisition updatedReq = new Requisition.Builder()
                    .basedOn(requisition)
                    .setStatus(3)
                    .setFullfilledDate(new Date())
                    .build();
            return requisitionRepository.save(updatedReq);
        }

        @Override
        protected void onPostExecute(Integer status) {
            if(status == 200) {
                scanBracelet.setEnabled(false);
                scanSample.setEnabled(false);
                new RetrieveRequisitionFromApi().execute();
            } else {
                progressLayout.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Error: " + status, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class UpdateSampleFromApi extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            Sample sample = samples.get(count);
            return sampleRepository.save(sample);
        }

        @Override
        protected void onPostExecute(Integer status) {
            if(status == 200) {
                TextView text = textViews.get(count);
                samplesLayout.removeView(text);
                count++;
                if(count == samples.size()) {
                    progressLayout.setVisibility(View.VISIBLE);
                    new UpdateRequisitionFromApi().execute();
                }
            } else {
                Toast.makeText(getActivity(), "Error: " + status, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
