package io.peqo.kbahelper.activity.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.model.wrapper.RequisitionListWrapper;

public class RequisitionListAdapter extends BaseAdapter {

    private Context c;
    private List<RequisitionListWrapper> requisitions;

    @BindView(R.id.requistionListPatientName) TextView patientName;
    @BindView(R.id.requistionListPatientCpr) TextView patientCpr;
    @BindView(R.id.listReqOverviewDept) TextView patientDept;
    @BindView(R.id.listReqOverviewRoom) TextView patientRoom;
    @BindView(R.id.listReqOverviewBed) TextView patientBed;

    public RequisitionListAdapter(Context context, List<RequisitionListWrapper> requisitions) {
        this.c = context;
        this.requisitions = requisitions;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_requisition_overview, parent, false);
        }
        ButterKnife.bind(this, convertView);
        return convertView;
    }

    @Override
    public int getCount() {
        return requisitions.size();
    }

    @Override
    public Object getItem(int position) {
        return requisitions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return requisitions.get(position).id;
    }
}
