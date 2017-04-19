package io.peqo.kbahelper.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.peqo.kbahelper.R;
import io.peqo.kbahelper.model.wrapper.TeamListWrapper;

public class TeamListAdapter extends BaseAdapter {

    private Context c;
    private List<TeamListWrapper> teams;

    @BindView(R.id.teamListName) TextView teamName;
    @BindView(R.id.teamListFinishedReq) TextView finishedReq;
    @BindView(R.id.teamListTotalReq) TextView totalReq;

    public TeamListAdapter(Context c, List<TeamListWrapper> teams) {
        this.c = c;
        this.teams = teams;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null) {
            view = layoutInflater.inflate(R.layout.row_team_overview, viewGroup, false);
        }
        ButterKnife.bind(this, view);

        TeamListWrapper team = (TeamListWrapper) getItem(i);
        teamName.setText(team.name);
        finishedReq.setText(String.valueOf(team.finishedReq));
        totalReq.setText(String.valueOf(team.totalReq));

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
