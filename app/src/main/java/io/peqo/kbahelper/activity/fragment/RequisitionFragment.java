package io.peqo.kbahelper.activity.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
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

public class RequisitionFragment extends Fragment {

    private static final String TAG = "Requisition Fragment";

    private List<Sample> samples;
    private List<CardView> cardViews;

    // Keep track of samples
    private int count = 0;
    private Long reqId;

    // Bind views
    @BindView(R.id.layoutReqRequestorContainer) LinearLayout requestorContainer;
    @BindView(R.id.layoutReqRequestorDesc) LinearLayout requestorDescription;
    @BindView(R.id.btnReqExpand) ImageView expandRequestor;
    @BindView(R.id.layoutReqSamples) LinearLayout samplesLayout;
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
    @BindView(R.id.textReqRunNumber) TextView requisitionRunNumber;
    @BindView(R.id.textReqNumber) TextView requisitionNumber;

    @BindView(R.id.requisitionProgressLayout) LinearLayout progressLayout;
    @BindView(R.id.requisitionProgress) ProgressBar progressBar;

    // Repositories
    private BedRepositoryImpl bedRepository;
    private DepartmentRepositoryImpl departmentRepository;
    private PatientRepositoryImpl patientRepository;
    private RequestorRepositoryImpl requestorRepository;
    private RequisitionRepositoryImpl requisitionRepository;
    private RoomRepositoryImpl roomRepository;
    private SampleRepositoryImpl sampleRepository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requisition_single, container, false);
        ButterKnife.bind(this, view);

        bedRepository = new BedRepositoryImpl();
        departmentRepository = new DepartmentRepositoryImpl();
        patientRepository = new PatientRepositoryImpl();
        requestorRepository = new RequestorRepositoryImpl();
        requisitionRepository = new RequisitionRepositoryImpl();
        roomRepository = new RoomRepositoryImpl();
        sampleRepository = new SampleRepositoryImpl();

        Bundle bundle = this.getArguments();
        reqId = bundle.getLong("id");

        new RetrieveRequisitionFromApi().execute();

        return view;
    }

    @OnClick(R.id.btnReqScanSample)
    public void scanSample() {}

    @OnClick(R.id.btnReqScanBracelet)
    public void scanBracelet() {}

    @OnClick(R.id.layoutReqRequestorContainer)
    public void toggleRequestorInfo() {
        toggleVisibility(requestorDescription);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Rekvisition: #" + reqId);
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

    public class RetrieveRequisitionFromApi extends AsyncTask<Void, Void, Object[]> {

        @Override
        protected Object[] doInBackground(Void... voids) {
            Log.d(TAG, "" + reqId);
            Requisition req = requisitionRepository.fetchObject(reqId);
            Patient patient = patientRepository.fetchObject(req.patientId);
            Requestor requestor = requestorRepository.fetchObject(req.requestorId);
            Bed bed = bedRepository.fetchObject(patient.id);
            Room room = roomRepository.fetchObject(bed.roomId);
            Department dept = departmentRepository.fetchObject(room.departmentId);

            Object[] args = {
                    req,
                    patient,
                    requestor,
                    bed,
                    room,
                    dept
            };
            return args;
        }

        @Override
        protected void onPostExecute(Object[] objects) {
            Requisition req = (Requisition) objects[0];
            Patient patient = (Patient) objects[1];
            Requestor requestor = (Requestor) objects[2];
            Bed bed = (Bed) objects[3];
            Room room = (Room) objects[4];
            Department dept = (Department) objects[5];
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");

            requisitionNumber.setText(String.valueOf(req.runNum));
            requisitionRunNumber.setText(String.valueOf(req.runNum));
            requisitionDate.setText(df.format(req.testTime));
            patientName.setText(patient.firstName + " " + patient.lastName);
            patientCpr.setText(patient.cprNum);
            patientBed.setText(String.valueOf(bed.bedNumber));
            patientRoom.setText(String.valueOf(room.roomNumber));
            patientDept.setText(dept.name);
            requestorName.setText(requestor.name);
            requestorDepartment.setText(requestor.department);
            requestorAddress.setText(requestor.address);
            requestorZip.setText(requestor.postalCode);
            requestorCountry.setText(requestor.country);

            progressLayout.setVisibility(View.GONE);
        }
    }

}
