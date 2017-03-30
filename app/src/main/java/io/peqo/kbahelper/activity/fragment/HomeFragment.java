package io.peqo.kbahelper.activity.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.activity.adapter.RequisitionListAdapter;
import io.peqo.kbahelper.database.SQLiteHandler;
import io.peqo.kbahelper.model.User;
import io.peqo.kbahelper.model.wrapper.RequisitionListWrapper;
import io.peqo.kbahelper.repository.RequisitionListWrapperRepositoryImpl;
import io.peqo.kbahelper.repository.contract.RequisitionListWrapperRepository;

public class HomeFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "Home fragment";

    private RequisitionListWrapperRepository requisitionRepository;
    private SQLiteHandler db;

    @BindView(R.id.requisitionList) ListView requisitionList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        requisitionRepository = new RequisitionListWrapperRepositoryImpl();
        db = new SQLiteHandler(getContext());
        new RetrieveRequisitionListFromApi().execute();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Mine rekvisitioner");
    }

    private class RetrieveRequisitionListFromApi extends AsyncTask<Void, Void, List<RequisitionListWrapper>> {

        @Override
        protected List<RequisitionListWrapper> doInBackground(Void... voids) {
            User user = db.getUser();
            return requisitionRepository.fetchAllFromUser(user.id);
        }

        @Override
        protected void onPostExecute(List<RequisitionListWrapper> requisitions) {
            final RequisitionListAdapter adapter = new RequisitionListAdapter(getContext(), requisitions);
            requisitionList.setAdapter(adapter);
            requisitionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Bundle bundle = new Bundle();
                    bundle.putLong("id", adapter.getItemId(i));

                    Fragment fragment = new RequisitionFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction ft = getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction();
                    ft.replace(R.id.content_main, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });
        }
    }
}
