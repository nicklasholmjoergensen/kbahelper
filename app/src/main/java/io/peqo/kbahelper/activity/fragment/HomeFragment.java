package io.peqo.kbahelper.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import io.peqo.kbahelper.MainApplication;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.activity.adapter.RequisitionListAdapter;
import io.peqo.kbahelper.model.DaoSession;
import io.peqo.kbahelper.model.Requisition;
import io.peqo.kbahelper.model.RequisitionDao;

public class HomeFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "Home fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        DaoSession daoSession = ((MainApplication) getActivity().getApplication()).getDaoSession();
        final RequisitionDao requisitionDao = daoSession.getRequisitionDao();

        ListView listView = (ListView) view.findViewById(R.id.requistionList);
        final List<Requisition> requisitions = requisitionDao.loadAll();

        final RequisitionListAdapter adapter = new RequisitionListAdapter(
                getActivity(),
                requisitions
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                Requisition req = requisitionDao.load(adapter.getItemId(position));
                Log.d("Event", "ListView clicked: " + req.getId());
                bundle.putLong("reqId", req.getId());
                Fragment fragment = new RequisitionFragment();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Hjem");
    }

}
