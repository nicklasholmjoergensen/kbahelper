package io.peqo.kbahelper.activity.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.database.SQLiteHandler;
import io.peqo.kbahelper.model.wrapper.TeamListWrapper;
import io.peqo.kbahelper.network.ApiConnection;
import io.peqo.kbahelper.repository.TeamListWrapperRepositoryImpl;
import okhttp3.Response;

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

    public class TeamListAdapter extends BaseAdapter {

        private Context c;
        private List<TeamListWrapper> teams;

        @BindView(R.id.teamListName) TextView teamName;
        @BindView(R.id.teamListNumUsers) TextView numUsers;
        @BindView(R.id.teamListFinishedReq) TextView finishedReq;
        @BindView(R.id.teamListTotalReq) TextView totalReq;
        @BindView(R.id.teamListAssistBtn) Button assistBtn;

        public TeamListAdapter(Context c, List<TeamListWrapper> teams) {
            this.c = c;
            this.teams = teams;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (view == null) {
                view = layoutInflater.inflate(R.layout.row_team_overview, viewGroup, false);
            }
            ButterKnife.bind(this, view);

            TeamListWrapper team = (TeamListWrapper) getItem(i);
            teamName.setText(team.name);
            numUsers.setText(String.valueOf(team.numUsers));
            finishedReq.setText(String.valueOf(team.finishedReq));
            totalReq.setText(String.valueOf(team.totalReq));

            assistBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Long id = getItemId(i);
                    new AssistOtherTeams().execute(id);
                }
            });

            return view;
        }

        @Override
        public int getCount() {
            return teams.size();
        }

        @Override
        public Object getItem(int i) {
            return teams.get(i);
        }

        @Override
        public long getItemId(int i) {
            return teams.get(i).id;
        }
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

    private class AssistOtherTeams extends AsyncTask<Long, Void, Boolean> {

        private static final String URL = "http://207.154.199.94/api/assist";

        @Override
        protected Boolean doInBackground(Long... longs) {
            final SQLiteHandler db = new SQLiteHandler(getActivity().getApplicationContext());
            final Long userId = db.getUser().id;
            final Long teamId = longs[0];
            final ObjectMapper mapper = new ObjectMapper();

            Map<String, Long> data = new HashMap<>();
            data.put("user_id", userId);
            data.put("team_id", teamId);

            try {
                Response response = ApiConnection.open(URL)
                        .syncPostRequest(mapper.writeValueAsString(data));

                if (response != null && response.isSuccessful()) {
                    JSONObject obj = new JSONObject(response.body().string());
                    int status = obj.getInt("status");

                    if (status == 0) return true;
                }
            } catch (Exception e) {
                Log.d("DEBUG", "Error: " + e);
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result) {
                Toast.makeText(getContext(), "Tilknyttet Team", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Allerede tilknyttet Team", Toast.LENGTH_LONG).show();
            }
        }
    }

}
