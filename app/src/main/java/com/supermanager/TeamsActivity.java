package com.supermanager;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sm.model.Manager;
import com.sm.model.Team;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yago on 26/09/16.
 */
public class TeamsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Manager> {

    @InjectView(R.id.loading_layout)
    public LinearLayout loading;

    @InjectView(R.id.working_layout)
    public SwipeRefreshLayout working;

    @InjectView(R.id.teams_list)
    public ListView teamsList;

    public TeamListAdapter teamListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        ButterKnife.inject(this);

        // Acción que refresca los datos de la actividad al arrastrar hacia abajo
        working.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    Log.i("TeamsSwapLoad", "Refrescando equipos");
                    getLoaderManager().initLoader(1, null, TeamsActivity.this).forceLoad();
                    working.setRefreshing(false);
                }
            });

        loading.setVisibility(View.VISIBLE);
        working.setVisibility(View.INVISIBLE);

        // Sets the data behind this ListView
        teamListAdapter = new TeamListAdapter(this);
        this.teamsList.setAdapter(teamListAdapter);
        this.teamsList.setOnItemClickListener(new TeamListListener(this));

        getLoaderManager().initLoader(1, null, this).forceLoad();

    }

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {

        Log.i("OnCreateLoader", "Refrescando equipos");

        SharedPreferences settings = getSharedPreferences("SESSION", 0);
        String user = settings.getString("user", "");
        String pass = settings.getString("pass", "");

        ManagerLoaderTask loader = new ManagerLoaderTask(this, user, pass);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Manager> loader, Manager manager) {

        List<Team> teams = manager.getTeams();
        teamListAdapter.setTeams(teams);

        loading.setVisibility(View.INVISIBLE);
        working.setVisibility(View.VISIBLE);

    }


    @Override
    public void onLoaderReset(Loader loader) {

    }


    public class TeamListListener implements AdapterView.OnItemClickListener {

        private AppCompatActivity parentActivity;

        public TeamListListener(AppCompatActivity parentActivity) {
            this.parentActivity = parentActivity;
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {

            Intent intent = new Intent(parentActivity, TeamActivity.class);

            Team team = (Team) adapterView.getItemAtPosition(index);
            intent.putExtra("team", (Serializable) team);
            startActivity(intent);
        }

    }

}



