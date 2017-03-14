package io.peqo.kbahelper.activity.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.activity.adapter.RequisitionListAdapter;
import io.peqo.kbahelper.model.wrapper.RequisitionListWrapper;
import io.peqo.kbahelper.repository.RequisitionListWrapperRepositoryImpl;
import io.peqo.kbahelper.repository.contract.RequisitionListWrapperRepository;

public class HomeFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "Home fragment";

    private RequisitionListWrapperRepository requisitionRepository;

    @BindView(R.id.requisitionList) ListView requisitionList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        requisitionRepository = new RequisitionListWrapperRepositoryImpl();
        Log.d(TAG, "OnCreate method fired.");
        new ReturnReq().execute();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Hjem");
    }

    private class ReturnReq extends AsyncTask<Void, Void, List<RequisitionListWrapper>> {

        @Override
        protected List<RequisitionListWrapper> doInBackground(Void... voids) {
            return requisitionRepository.fetchAll();
        }

        @Override
        protected void onPostExecute(List<RequisitionListWrapper> requisitions) {
            RequisitionListAdapter adapter = new RequisitionListAdapter(getContext(), requisitions);
            requisitionList.setAdapter(adapter);
        }
    }
}
