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
import io.peqo.kbahelper.model.wrapper.RequisitionListWrapper;
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

    private class RetrieveRequisitions extends AsyncTask<Void, Void, List<RequisitionListWrapper>> {

        @Override
        protected List<RequisitionListWrapper> doInBackground(Void... voids) {
            List<RequisitionListWrapper> requisitions = new ArrayList<>();
            final OkHttpClient client = new OkHttpClient();
            final Gson gson = new Gson();
            Request request = new Request.Builder()
                    .url("http://207.154.199.94/api/req")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                JSONArray jsonArray = new JSONArray(responseData);
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    RequisitionListWrapper wrapper = new RequisitionListWrapper.Builder()
                            .setId(obj.getLong("id"))
                            .setFirstName(obj.getString("first_name"))
                            .setLastName(obj.getString("last_name"))
                            .setCprNum(obj.getString("cpr_num"))
                            .setDeptName(obj.getString("dept_name"))
                            .setRoomNumber(obj.getInt("room_number"))
                            .setBedNumber(obj.getInt("bed_number"))
                            .build();
                    requisitions.add(wrapper);
                }
                Log.d(TAG, jsonArray.toString());
            } catch(Exception e) {
                Log.d(TAG, "Error: " + e);
            }

            return requisitions;
        }

        @Override
        protected void onPostExecute(List<RequisitionListWrapper> requisitions) {
            requisitionList.setAdapter(new RequisitionListAdapter(getActivity(), requisitions));
        }
    }

}
