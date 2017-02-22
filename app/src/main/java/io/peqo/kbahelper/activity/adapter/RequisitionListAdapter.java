package io.peqo.kbahelper.activity.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.peqo.kbahelper.R;
import io.peqo.kbahelper.model.Requisition;

public class RequisitionListAdapter extends BaseAdapter {

    Context c;
    List<Requisition> mRequisitions;

    public RequisitionListAdapter(Context context, List<Requisition> requisitions) {
        this.c = context;
        this.mRequisitions = requisitions;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row_requisition_overview, parent, false);

        TextView patientName = (TextView) row.findViewById(R.id.requistionListPatientName);
        TextView patientCpr = (TextView) row.findViewById(R.id.requistionListPatientCpr);

        Requisition requisition = mRequisitions.get(position);

        patientName.setText(requisition.getPatient().getFirstName() + " " + requisition.getPatient().getLastName());
        patientCpr.setText(requisition.getPatient().getCprNum());

        return row;
    }

    @Override
    public int getCount() {
        return mRequisitions.size();
    }

    @Override
    public Object getItem(int position) {
        return mRequisitions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mRequisitions.get(position).getId();
    }
}
