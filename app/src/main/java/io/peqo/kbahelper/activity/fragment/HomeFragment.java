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
import io.peqo.kbahelper.model.Requisition;
import io.peqo.kbahelper.repository.RequisitionRepositoryImpl;

public class HomeFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "Home fragment";

    private RequisitionRepositoryImpl requisitionRepository;

    @BindView(R.id.requisitionList) ListView requisitionList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        requisitionRepository = new RequisitionRepositoryImpl();
        Log.d(TAG, "OnCreate method fired.");
        new ReturnReq().execute();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Hjem");
    }

    private class ReturnReq extends AsyncTask<Void, Void, List<Requisition>> {

        @Override
        protected List<Requisition> doInBackground(Void... voids) {
            return requisitionRepository.fetchAll();
        }

        @Override
        protected void onPostExecute(List<Requisition> requisitions) {
            Log.d(TAG, requisitions.toString());
        }
    }
}
