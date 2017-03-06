package io.peqo.kbahelper.activity.fragment;

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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.model.Patient;
import io.peqo.kbahelper.model.Requisition;
import io.peqo.kbahelper.model.Sample;

public class RequisitionFragment extends Fragment {

    private static final String TAG = "Requisition Fragment";

    private Long reqId;
    private List<Sample> samples;
    private List<CardView> cardViews;

    private Requisition requisition;
    private Patient patient;

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

    private void setup(View view) {
        Bundle bundle = this.getArguments();
        reqId = bundle.getLong("reqId");

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault());
        samplesLayout = (LinearLayout) view.findViewById(R.id.layoutReqSamples);
        scanSample = (Button) view.findViewById(R.id.btnReqScanSample);
        expandRequisitor = (ImageView) view.findViewById(R.id.btnReqExpand);
        requestorContainer = (LinearLayout) view.findViewById(R.id.layoutReqRequestorContainer);
        requestorDescription = (LinearLayout) view.findViewById(R.id.layoutReqRequestorDesc);
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

}
