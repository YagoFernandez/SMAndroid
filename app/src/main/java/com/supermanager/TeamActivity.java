package com.supermanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sm.model.Player;
import com.sm.model.Team;


import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yago on 26/09/16.
 */
public class TeamActivity extends AppCompatActivity {

    @InjectView(R.id.name)
    protected TextView name;

//    @InjectView(R.id.players_list)
//    public ListView playersList;
//    public PlayerListAdapter playerListAdapter;

    @InjectView(R.id.players_list)
    protected RecyclerView playersList;
    private PlayerAdapter playerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        ButterKnife.inject(this);

        Team team = (Team) getIntent().getSerializableExtra("team");

        name.setText(team.getName());

        // Sets the data behind this ListView
        initPlayerList(team.getPlayers());

    }

    /*private void initPlayerList(List<Player> players) {
        playerListAdapter = new PlayerListAdapter(this, players);
        this.playersList.setAdapter(playerListAdapter);
    }*/

    private void initPlayerList(List<Player> players) {

        playersList = (RecyclerView) findViewById(R.id.players_list);
        playersList.setHasFixedSize(true);

        playerAdapter = new PlayerAdapter(players, this);
        playersList.setAdapter(playerAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        playersList.setLayoutManager(layoutManager);
        
    }

}
