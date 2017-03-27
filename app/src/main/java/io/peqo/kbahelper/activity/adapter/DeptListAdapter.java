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
import io.peqo.kbahelper.model.wrapper.DeptListWrapper;

public class DeptListAdapter extends BaseAdapter {

    private Context c;
    private List<DeptListWrapper> departments;

    @BindView(R.id.departmentListName) TextView deptName;
    @BindView(R.id.listDeptOverviewUnfinishedReq) TextView unfinishedReq;
    @BindView(R.id.listDeptOverviewTotalReq) TextView totalReq;

    public DeptListAdapter(Context context, List<DeptListWrapper> departments) {
        this.c = context;
        this.departments = departments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_department_overview, parent, false);
        }
        ButterKnife.bind(this, convertView);

        DeptListWrapper dept = (DeptListWrapper) getItem(position);
        deptName.setText(dept.name);
        unfinishedReq.setText(String.valueOf(dept.finishedReq));
        totalReq.setText(String.valueOf(dept.totalReq));

        return convertView;
    }

    @Override
    public int getCount() {
        return departments.size();
    }

    @Override
    public Object getItem(int i) {
        return departments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return departments.get(i).id;
    }
}
