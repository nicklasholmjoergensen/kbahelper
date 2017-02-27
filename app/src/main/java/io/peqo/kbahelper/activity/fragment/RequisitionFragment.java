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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.peqo.kbahelper.MainApplication;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.model.DaoSession;
import io.peqo.kbahelper.model.Patient;
import io.peqo.kbahelper.model.PatientDao;
import io.peqo.kbahelper.model.Requestor;
import io.peqo.kbahelper.model.RequestorDao;
import io.peqo.kbahelper.model.Requisition;
import io.peqo.kbahelper.model.RequisitionDao;
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
    private Button scanBracelet;
    private Button scanSample;

    // Keep track of samples
    private int count = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requisition_single, container, false);

        setup(view);

        new PrepareUIElements().execute(requisition.getId());

        scanBracelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!patient.isRegistered()) {
                    patient.setRegistered(true);
                    Toast.makeText(getActivity().getApplicationContext(), "Armbånd succesfuldt scannet", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Patient er registreret.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        scanSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(patient.isRegistered()) {
                    if(count < samples.size()) {
                        samples.get(count).setTaken(true);
                        sampleDao.insertOrReplace(samples.get(count));
                        samplesLayout.removeView(cardViews.get(count));
                        if(count == samples.size() - 1) {
                            requisition.setDone(true);
                            requisitionDao.insertOrReplace(requisition);
                        }
                        count++;
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Alle prøver taget.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Armbånd ikke scannet.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        requestorContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility(requestorDescription);
            }
        });

        return view;
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

        Bundle bundle = this.getArguments();
        reqId = bundle.getLong("reqId");

        requisition = requisitionDao.load(reqId);
        patient = patientDao.load(requisition.getPatientId());
        Requestor requestor = requestorDao.load(requisition.getRequestorId());

        samplesLayout = (LinearLayout) view.findViewById(R.id.layoutReqSamples);
        scanBracelet = (Button) view.findViewById(R.id.btnReqScanBracelet);
        scanSample = (Button) view.findViewById(R.id.btnReqScanSample);
        TextView patientName = (TextView) view.findViewById(R.id.textReqPatientName);
        TextView patientCpr = (TextView) view.findViewById(R.id.textReqPatientCpr);
        TextView requestorName = (TextView) view.findViewById(R.id.textReqRequestorName);
        TextView requestorDepartment = (TextView) view.findViewById(R.id.textReqRequestorDepartment);
        TextView requestorAddress = (TextView) view.findViewById(R.id.textReqRequestorAddress);
        TextView requestorZip = (TextView) view.findViewById(R.id.textReqRequestorZip);
        TextView requestorCountry = (TextView) view.findViewById(R.id.textReqRequestorCountry);
        TextView requestorTitle = (TextView) view.findViewById(R.id.textReqRequestor);
        requestorContainer = (LinearLayout) view.findViewById(R.id.layoutReqRequestorContainer);
        requestorDescription = (LinearLayout) view.findViewById(R.id.layoutReqRequestorDesc);

        patientName.setText(patient.getFullName());
        patientCpr.setText(patient.getCprNum());
        requestorName.setText(requestor.getName());
        requestorDepartment.setText(requestor.getDepartment());
        requestorAddress.setText(requestor.getAddress());
        requestorZip.setText(requestor.getPostalCode());
        requestorCountry.setText(requestor.getCountry());
        requestorDescription.setVisibility(View.GONE);
    }

    private void toggleVisibility(View view) {
        if(view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        } else {
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

}
