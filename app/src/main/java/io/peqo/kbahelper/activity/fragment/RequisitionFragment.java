package io.peqo.kbahelper.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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


public class RequisitionFragment extends Fragment {

    private Long reqId;
    private Bundle bundle;

    private Requisition requisition;
    private Patient patient;
    private Requestor requestor;

    private int count = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requisition_single, container, false);

        DaoSession daoSession = ((MainApplication) getActivity().getApplication()).getDaoSession();
        RequisitionDao requisitionDao = daoSession.getRequisitionDao();
        PatientDao patientDao = daoSession.getPatientDao();
        RequestorDao requestorDao = daoSession.getRequestorDao();

        bundle = this.getArguments();
        reqId = bundle.getLong("reqId");

        requisition = requisitionDao.load(reqId);
        patient = patientDao.load(requisition.getPatientId());
        requestor = requestorDao.load(requisition.getRequestorId());

        final List<Sample> samples = requisition.getSamples();
        final List<TextView> textViews = new ArrayList<>();

        // Widgets
        final LinearLayout samplesLayout = (LinearLayout) view.findViewById(R.id.layoutReqSamples);
        TextView patientName = (TextView) view.findViewById(R.id.textReqPatientName);
        TextView patientCpr = (TextView) view.findViewById(R.id.textReqPatientCpr);
        TextView requestorName = (TextView) view.findViewById(R.id.textReqRequestorName);
        TextView requestorDepartment = (TextView) view.findViewById(R.id.textReqRequestorDepartment);
        TextView requestorAddress = (TextView) view.findViewById(R.id.textReqRequestorAddress);
        TextView requestorZip = (TextView) view.findViewById(R.id.textReqRequestorZip);
        TextView requestorCountry = (TextView) view.findViewById(R.id.textReqRequestorCountry);
        Button scanBracelet = (Button) view.findViewById(R.id.btnReqScanBracelet);
        Button scanSample = (Button) view.findViewById(R.id.btnReqScanSample);

        patientName.setText(patient.getFullName());
        patientCpr.setText(patient.getCprNum());
        requestorName.setText(requestor.getName());
        requestorDepartment.setText(requestor.getDepartment());
        requestorAddress.setText(requestor.getAddress());
        requestorZip.setText(requestor.getPostalCode());
        requestorCountry.setText(requestor.getCountry());

        for(int i = 0; i < samples.size(); i++) {
            final TextView sampleText = new TextView(this.getActivity());
            sampleText.setText(samples.get(i).getName());
            sampleText.setId(i);
            textViews.add(sampleText);
            samplesLayout.addView(sampleText);
        }

        scanBracelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!patient.isRegistered()) {
                    patient.setRegistered(true);
                    Toast
                            .makeText(getActivity().getApplicationContext(), "Armbånd succesfuldt scannet", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast
                            .makeText(getActivity().getApplicationContext(), "Patient er registreret.", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        scanSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(patient.isRegistered()) {
                    if(count < samples.size()) {
                        samplesLayout.removeView(textViews.get(count));
                        count++;
                    } else {
                        Toast
                                .makeText(getActivity().getApplicationContext(), "Alle prøver taget.", Toast.LENGTH_SHORT)
                                .show();
                    }
                } else {
                    Toast
                            .makeText(getActivity().getApplicationContext(), "Armbånd ikke scannet.", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Rekvisition: #" + reqId);
    }

}
