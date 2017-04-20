package io.peqo.kbahelper.activity.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
import io.peqo.kbahelper.repository.BedRepositoryImpl;
import io.peqo.kbahelper.repository.DepartmentRepositoryImpl;
import io.peqo.kbahelper.repository.PatientRepositoryImpl;
import io.peqo.kbahelper.repository.RequestorRepositoryImpl;
import io.peqo.kbahelper.repository.RequisitionRepositoryImpl;
import io.peqo.kbahelper.repository.RoomRepositoryImpl;

import static io.peqo.kbahelper.R.drawable.ic_done_green_24dp;

public class RequisitionDoneFragment extends android.support.v4.app.Fragment {

    private static final String TAG = RequisitionDoneFragment.class.getSimpleName();
    private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    // Model objects for UI
    private Requisition requisition;
    private Patient patient;
    private Bed bed;
    private Room room;
    private Department department;
    private Requestor requestor;

    @BindView(R.id.layoutReqDoneRequestorContainer) LinearLayout requestorContainer;
    @BindView(R.id.layoutReqDoneRequestorDesc) LinearLayout requestorDescription;
    @BindView(R.id.btnReqDoneExpand) ImageView expandRequestor;
    @BindView(R.id.reqDoneFinishIcon) LinearLayout reqFinishIcon;
    @BindView(R.id.textReqDonePatientName) TextView patientName;
    @BindView(R.id.textReqDonePatientCpr) TextView patientCpr;
    @BindView(R.id.textReqDonePatientDept) TextView patientDept;
    @BindView(R.id.textReqDonePatientRoom) TextView patientRoom;
    @BindView(R.id.textReqDonePatientBed) TextView patientBed;
    @BindView(R.id.textReqDoneRequestorName) TextView requestorName;
    @BindView(R.id.textReqDoneRequestorDepartment) TextView requestorDepartment;
    @BindView(R.id.textReqDoneRequestorAddress) TextView requestorAddress;
    @BindView(R.id.textReqDoneRequestorZip) TextView requestorZip;
    @BindView(R.id.textReqDoneRequestorCountry) TextView requestorCountry;
    @BindView(R.id.textReqDoneTestDate) TextView requisitionDate;
    @BindView(R.id.textReqDoneFulfilledDate) TextView requisitionFulfilledDate;
    @BindView(R.id.textReqDoneDescription) TextView requisitionDescription;

    @BindView(R.id.requisitionDoneProgressLayout) LinearLayout progressLayout;
    @BindView(R.id.requisitionDoneProgress) ProgressBar progressBar;

    private Long reqId;

    // Repositories
    private final BedRepositoryImpl bedRepository = new BedRepositoryImpl();
    private final DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl();
    private final PatientRepositoryImpl patientRepository  = new PatientRepositoryImpl();
    private final RequestorRepositoryImpl requestorRepository  = new RequestorRepositoryImpl();
    private final RequisitionRepositoryImpl requisitionRepository = new RequisitionRepositoryImpl();
    private final RoomRepositoryImpl roomRepository = new RoomRepositoryImpl();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requisition_single_done, container, false);
        ButterKnife.bind(this, view);
        reqId = this.getArguments().getLong("id");
        new RetrieveRequisitionFromApi().execute();
        return view;
    }

    @OnClick(R.id.layoutReqDoneRequestorContainer)
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

    private class RetrieveRequisitionFromApi extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            requisition = requisitionRepository.fetchObject(reqId);
            patient = patientRepository.fetchObject(requisition.patientId);
            requestor = requestorRepository.fetchObject(requisition.requestorId);
            bed = bedRepository.fetchObject(patient.id);
            room = roomRepository.fetchObject(bed.roomId);
            department = departmentRepository.fetchObject(room.departmentId);
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
            requisitionFulfilledDate.setText(df.format(requisition.fulfilledDate));

            final ImageView img = new ImageView(getActivity());
            final LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            img.setLayoutParams(llp);
            img.setImageResource(ic_done_green_24dp);
            reqFinishIcon.addView(img);

            getActivity().setTitle("Rekvisition: #" + requisition.reqNum);
            progressLayout.setVisibility(View.GONE);
        }

    }
}
