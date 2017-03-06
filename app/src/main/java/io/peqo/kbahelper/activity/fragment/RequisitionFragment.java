package io.peqo.kbahelper.activity.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.peqo.kbahelper.MainApplication;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.model.Bed;
import io.peqo.kbahelper.model.BedDao;
import io.peqo.kbahelper.model.DaoSession;
import io.peqo.kbahelper.model.Department;
import io.peqo.kbahelper.model.DepartmentDao;
import io.peqo.kbahelper.model.Patient;
import io.peqo.kbahelper.model.PatientDao;
import io.peqo.kbahelper.model.Requestor;
import io.peqo.kbahelper.model.RequestorDao;
import io.peqo.kbahelper.model.Requisition;
import io.peqo.kbahelper.model.RequisitionDao;
import io.peqo.kbahelper.model.Room;
import io.peqo.kbahelper.model.RoomDao;
import io.peqo.kbahelper.model.Sample;
import io.peqo.kbahelper.model.SampleDao;

public class RequisitionFragment extends Fragment {

    private static final String TAG = "Requisition Fragment";

    private Long reqId;
    private List<Sample> samples;
    private List<CardView> cardViews;

    private Requisition requisition;
    private Patient patient;

    private RequisitionDao requisitionDao;
    private SampleDao sampleDao;

    // Set up widgets for Async Task
    private LinearLayout samplesLayout;
    private LinearLayout requestorContainer;
    private LinearLayout requestorDescription;
    private Button scanSample;
    private ImageView expandRequisitor;

    // Keep track of samples
    private int count = 0;

    // Bind views
    @BindView(R.id.btnReqScanBracelet) Button scanBracelet;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requisition_single, container, false);
        ButterKnife.bind(this, view);
        setup(view);
        new PrepareUIElements().execute(requisition.getId());
        return view;
    }

    @OnClick(R.id.btnReqScanSample)
    public void scanSample() {
        if(patient.isRegistered()) {
            if(count < samples.size()) {
                new UpdateSample().execute(count);
                count++;
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Alle prøver taget.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Armbånd ikke scannet.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btnReqScanBracelet)
    public void scanBracelet() {
        if(!patient.isRegistered()) {
            patient.setRegistered(true);
            Toast.makeText(getActivity().getApplicationContext(), "Armbånd succesfuldt scannet", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Patient er registreret.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.layoutReqRequestorContainer)
    public void toggleRequestorInfo() {
        toggleVisibility(requestorDescription);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Rekvisition: #" + reqId);
    }

    private void setup(View view) {
        DaoSession daoSession = ((MainApplication) getActivity().getApplication()).getDaoSession();
        sampleDao = daoSession.getSampleDao();
        requisitionDao = daoSession.getRequisitionDao();
        PatientDao patientDao = daoSession.getPatientDao();
        RequestorDao requestorDao = daoSession.getRequestorDao();
        BedDao bedDao = daoSession.getBedDao();
        RoomDao roomDao = daoSession.getRoomDao();
        DepartmentDao departmentDao = daoSession.getDepartmentDao();

        Bundle bundle = this.getArguments();
        reqId = bundle.getLong("reqId");

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault());

        requisition = requisitionDao.load(reqId);
        patient = patientDao.load(requisition.getPatientId());
        Bed bed = bedDao.load(patient.getBedId());
        Room room = roomDao.load(bed.getRoomId());
        Department department = departmentDao.load(room.getDepartmentId());
        Requestor requestor = requestorDao.load(requisition.getRequestorId());

        samplesLayout = (LinearLayout) view.findViewById(R.id.layoutReqSamples);
        scanSample = (Button) view.findViewById(R.id.btnReqScanSample);
        expandRequisitor = (ImageView) view.findViewById(R.id.btnReqExpand);
        requestorContainer = (LinearLayout) view.findViewById(R.id.layoutReqRequestorContainer);
        requestorDescription = (LinearLayout) view.findViewById(R.id.layoutReqRequestorDesc);

        patientName.setText(patient.getFullName());
        patientCpr.setText(patient.getCprNum());
        patientDept.setText(department.getName());
        patientRoom.setText(String.valueOf(room.getRoomNumber()));
        patientBed.setText(String.valueOf(bed.getBedNumber()));
        requestorName.setText(requestor.getName());
        requestorDepartment.setText(requestor.getDepartment());
        requestorAddress.setText(requestor.getAddress());
        requestorZip.setText(requestor.getPostalCode());
        requestorCountry.setText(requestor.getCountry());
        requisitionDate.setText(df.format(requisition.getTestTime()));
        requisitionNumber.setText(String.valueOf(requisition.getReqNum()));
        requisitionRunNumber.setText(String.valueOf(requisition.getRunNum()));
        requestorDescription.setVisibility(View.GONE);
    }

    private void toggleVisibility(final View view) {
        if(view.getVisibility() == View.GONE) {
            expandRequisitor.setImageResource(R.drawable.ic_expand_less_black_24dp);
            view.setVisibility(View.VISIBLE);
        } else {
            expandRequisitor.setImageResource(R.drawable.ic_expand_more_black_24dp);
            view.setVisibility(View.GONE);
        }
    }

    private class PrepareUIElements extends AsyncTask<Long, Void, List<Sample>> {

        @Override
        protected List<Sample> doInBackground(Long... id) {
            final Long reqId = id[0];
            samples = sampleDao.queryRawCreate(
                    "WHERE T.REQUISITION_ID = " + reqId +
                            " AND T.TAKEN != 1"
            ).list();
            return samples;
        }

        @Override
        protected void onPostExecute(final List<Sample> samples) {
            cardViews = new ArrayList<>();

            for(int i = 0; i < samples.size(); i++) {
                final CardView card = new CardView(getActivity());
                card.setUseCompatPadding(true);
                card.setPadding(12, 12, 12, 12);
                final TextView sampleText = new TextView(getActivity());
                sampleText.setText(samples.get(i).getName());
                sampleText.setId(i);
                sampleText.setPadding(12, 12, 12, 12);
                card.addView(sampleText);
                cardViews.add(card);
                samplesLayout.addView(card);
            }
        }
    }

    private class UpdateSample extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            final int count = params[0];

            samples.get(count).setTaken(true);
            sampleDao.insertOrReplace(samples.get(count));
            if(count == samples.size() - 1) {
                requisition.setDone(true);
                requisitionDao.insertOrReplace(requisition);
            }

            return count;
        }

        @Override
        protected void onPostExecute(Integer count) {
            samplesLayout.removeView(cardViews.get(count));
        }
    }

}
