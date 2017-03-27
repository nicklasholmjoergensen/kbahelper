package io.peqo.kbahelper.activity.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.activity.adapter.DeptListAdapter;
import io.peqo.kbahelper.model.wrapper.DeptListWrapper;
import io.peqo.kbahelper.repository.DeptListWrapperRepositoryImpl;

public class DepartmentOverviewFragment extends Fragment {

    private static final String TAG = "Dept. overview Fragment";

    private DeptListWrapperRepositoryImpl deptListWrapperRepository = new DeptListWrapperRepositoryImpl();

    @BindView(R.id.departmentList) ListView departmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dept_overview, container, false);
        ButterKnife.bind(this, view);
        new RetrieveDeptListFromApi().execute();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Alle afdelinger");
    }

    public void onBackPressed() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.remove(this);
        ft.commit();
        manager.popBackStack();
    }

    private class RetrieveDeptListFromApi extends AsyncTask<Void, Void, List<DeptListWrapper>> {

        @Override
        protected List<DeptListWrapper> doInBackground(Void... voids) {
            return deptListWrapperRepository.fetchAll();
        }

        @Override
        protected void onPostExecute(List<DeptListWrapper> deptListWrappers) {
            final DeptListAdapter adapter = new DeptListAdapter(getContext(), deptListWrappers);
            departmentList.setAdapter(adapter);
        }
    }
}
