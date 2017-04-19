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
import io.peqo.kbahelper.activity.adapter.TeamListAdapter;
import io.peqo.kbahelper.model.wrapper.TeamListWrapper;
import io.peqo.kbahelper.repository.TeamListWrapperRepositoryImpl;

public class TeamOverviewFragment extends Fragment {

    TeamListWrapperRepositoryImpl teamListWrapperRepository = new TeamListWrapperRepositoryImpl();

    @BindView(R.id.teamList) ListView teamList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_overview, container, false);
        ButterKnife.bind(this, view);
        new RetrieveTeamListFromApi().execute();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Alle Teams");
    }

    public void onBackPressed() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.remove(this);
        ft.commit();
        manager.popBackStack();
    }

    private class RetrieveTeamListFromApi extends AsyncTask<Void, Void, List<TeamListWrapper>> {

        @Override
        protected List<TeamListWrapper> doInBackground(Void... voids) {
            return teamListWrapperRepository.fetchAll();
        }

        @Override
        protected void onPostExecute(List<TeamListWrapper> teamListWrappers) {
            final TeamListAdapter adapter = new TeamListAdapter(getContext(), teamListWrappers);
            teamList.setAdapter(adapter);
        }
    }

}
