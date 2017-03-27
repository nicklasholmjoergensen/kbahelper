package io.peqo.kbahelper.activity.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.model.wrapper.DeptListWrapper;

public class DepartmentOverviewFragment extends Fragment {

    private static final String TAG = "Dept. overview Fragment";

    @BindView(R.id.departmentList) ListView departmentList;
    @BindView(R.id.listDeptOverviewUnfinishedReq) TextView numUnfinishedReq;
    @BindView(R.id.listDeptOverviewTotalReq) TextView totalReq;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dept_overview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private class RetrieveDeptListFromApi extends AsyncTask<Void, Void, List<DeptListWrapper>> {

        @Override
        protected List<DeptListWrapper> doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(List<DeptListWrapper> deptListWrappers) {
            super.onPostExecute(deptListWrappers);
        }
    }
}
