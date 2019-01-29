package com.supermanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm.model.Player;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by yago on 12/10/16.
 */
public class PlayerListAdapter extends BaseAdapter {

    private Context context;
    private List<Player> players;

    public PlayerListAdapter(Context context, List<Player> players) {
        this.context = context;
        this.players = players;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int i) {
        return players.get(i);
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

    private View inflateView(View view, ViewGroup viewGroup) {

        View rowView = view;

        if (view == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.activity_player_list_item_relative, viewGroup, false);
        }

        return rowView;
    }

    private void initViewData(View view, int index) {
        Player player = this.players.get(index);
        initName(view, player);
        initTeam(view, player);
        initValue(view, player);
        initPoints(view, player);
        initImage(view, player);
        //initIcons(view, player);
    }

    private void initImage(View view, Player player) {
        ImageView imageView = (ImageView) view.findViewById(R.id.imageUrl);
        Picasso.with(context).load(player.getImageUrl()).into(imageView);
    }

    private void initName(View view, Player player) {
        TextView nameView = (TextView) view.findViewById(R.id.name);
        nameView.setText(player.getName());
    }

    private void initTeam(View view, Player player) {
        TextView teamView = (TextView) view.findViewById(R.id.team);
        teamView.setText(player.getTeam());
    }

    private void initValue(View view, Player player) {
        TextView valueView = (TextView) view.findViewById(R.id.value);
        valueView.setText(player.getPrice());
    }

    private void initPoints(View view, Player player) {
        TextView pointsView = (TextView) view.findViewById(R.id.points);
        pointsView.setText(player.getPointsAsNumber().toString());
    }

    private void initIcons(View view, Player player) {

        ImageView aviso1 = (ImageView) view.findViewById(R.id.aviso1);
        ImageView aviso2 = (ImageView) view.findViewById(R.id.aviso2);
        ImageView aviso3 = (ImageView) view.findViewById(R.id.aviso3);

        clearImageView(aviso1);
        clearImageView(aviso2);
        clearImageView(aviso3);

        List<ImageView> avisos = new ArrayList<ImageView>();
        avisos.add(aviso1);
        avisos.add(aviso2);
        avisos.add(aviso3);

        if (player.isNational()) {
            Picasso.with(context).load(R.drawable.ico_espanol).into(avisos.get(0));
            avisos.remove(0);
        }

        if (player.isExtracommunity()) {
            Picasso.with(context).load(R.drawable.ico_extracomunitario).into(avisos.get(0));
            avisos.remove(0);
        }

        if (player.isInjured()) {
            Picasso.with(context).load(R.drawable.ico_lesionado).into(avisos.get(0));
            avisos.remove(0);
        }

        if (player.isDoubtful()) {
            Picasso.with(context).load(R.drawable.ico_masinfo).into(avisos.get(0));
            avisos.remove(0);
        }

    }

    private void clearImageView(ImageView view) {
        view.setImageResource(0);
    }

}
