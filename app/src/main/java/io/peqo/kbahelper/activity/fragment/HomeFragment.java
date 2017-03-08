package io.peqo.kbahelper.activity.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.activity.adapter.RequisitionListAdapter;
import io.peqo.kbahelper.model.Patient;
import io.peqo.kbahelper.model.Requisition;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "Home fragment";

    @BindView(R.id.requisitionList) ListView requisitionList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        /*
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                Requisition req = requisitionDao.load(adapter.getItemId(position));
                bundle.putLong("reqId", req.getId());
                Fragment fragment = new RequisitionFragment();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
            }
        });
        */
        Log.d(TAG, "OnCreate method fired.");

        new RetrieveRequisitions().execute();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Hjem");
    }

    private class RetrieveRequisitions extends AsyncTask<Void, Void, List<Requisition>> {

        @Override
        protected List<Requisition> doInBackground(Void... voids) {
            List<Requisition> requisitions = new ArrayList<>();
            final OkHttpClient client = new OkHttpClient();
            final Gson gson = new Gson();
            Request request = new Request.Builder()
                    .url("http://207.154.199.94/api/req")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                JSONObject json = new JSONObject(responseData);
                JSONArray jsonArray = json.getJSONArray("requisitions");
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONObject patient = jsonObject.getJSONObject("patient");
                    Patient p = new Patient.Builder()
                            .setId(patient.getLong("id"))
                            .setFirstName(patient.getString("first_name"))
                            .setLastName(patient.getString("last_name"))
                            .setCprNum(patient.getString("cpr_num"))
                            .setRegistered(false)
                            .build();
                    Requisition r = new Requisition.Builder()
                            .setId(jsonObject.getLong("id"))
                            .setReqNum(jsonObject.getInt("req_num"))
                            .setRunNum(jsonObject.getInt("run_num"))
                            .setDone(false)
                            .setPatient(p)
                            .build();
                    requisitions.add(r);
                }
            } catch(Exception e) {
                Log.d(TAG, "Error: " + e);
            }

            return requisitions;
        }

        @Override
        protected void onPostExecute(List<Requisition> requisitions) {
            requisitionList.setAdapter(new RequisitionListAdapter(getActivity(), requisitions));
        }
    }

}
