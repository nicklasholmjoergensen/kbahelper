package io.peqo.kbahelper.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.peqo.kbahelper.MainApplication;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.model.DaoSession;
import io.peqo.kbahelper.model.Patient;
import io.peqo.kbahelper.model.PatientDao;
import io.peqo.kbahelper.model.Requisition;
import io.peqo.kbahelper.model.RequisitionDao;


public class RequisitionFragment extends Fragment {

    private Long reqId;
    private Bundle bundle;

    private Requisition requisition;
    private Patient patient;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requisition_single, container, false);

        DaoSession daoSession = ((MainApplication) getActivity().getApplication()).getDaoSession();
        RequisitionDao requisitionDao = daoSession.getRequisitionDao();
        PatientDao patientDao = daoSession.getPatientDao();

        bundle = this.getArguments();
        reqId = bundle.getLong("reqId");

        requisition = requisitionDao.load(reqId);
        patient = patientDao.load(requisition.getPatientId());

        // Widgets
        TextView patientName = (TextView) view.findViewById(R.id.textReqPatientName);
        TextView patientCpr = (TextView) view.findViewById(R.id.textReqPatientCpr);

        patientName.setText(patient.getFullName());
        patientCpr.setText(patient.getCprNum());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Rekvisition: #" + reqId);
    }

}
