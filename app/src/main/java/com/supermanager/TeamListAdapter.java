package com.supermanager;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm.model.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yago on 26/09/16.
 */
public class TeamListAdapter extends BaseAdapter {

    private Context context;
    private List<Team> teams;

    public TeamListAdapter(Context context) {
        this.context = context;
        this.teams = new ArrayList<Team>();
    }

    public TeamListAdapter(Context context, List<Team> teams) {
        this.context = context;
        this.teams = teams;
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
        return i;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {

        View rowView = inflateView(view, viewGroup);
        initViewData(rowView, index);
        return rowView;
    }

    public void setTeams(List<Team> teams) {
        this.teams.clear();
        this.teams.addAll(teams);
        notifyDataSetChanged();
    }

    private View inflateView(View view, ViewGroup viewGroup) {

        View rowView = view;

        if (view == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.activity_team_list_item, viewGroup, false);
        }

        return rowView;
    }

    private void initViewData(View view, int index) {
        Team team = this.teams.get(index);
        initName(view, team);
        initPoints(view, team);
    }

    private void initName(View view, Team team) {
        TextView nameView = (TextView) view.findViewById(R.id.name);
        nameView.setText(team.getName());
    }

    private void initPoints(View view, Team team) {
        TextView pointsView = (TextView) view.findViewById(R.id.points);
        pointsView.setText(team.getPoints().toString());
    }
}
