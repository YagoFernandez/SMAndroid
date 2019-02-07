package com.supermanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sm.model.SMPlayer;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by usuario on 23/12/2016.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerViewHolder> {

    private Context context;
    private List<SMPlayer> players;

    public PlayerAdapter(List<SMPlayer> players, Context context) {

        this.players = players;
        this.context = context;
    }

    @Override
    /**
     * Encargado de crear los nuevos objetos ViewHolder necesarios para los elementos de la colección.
     */
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflateItemView(parent, R.layout.activity_player_list_item_relative);
        PlayerViewHolder viewholder = createViewHolderForView(itemView, context);
        return viewholder;
    }

    private View inflateItemView(ViewGroup parent, int layout) {
        return LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
    }

    private PlayerViewHolder createViewHolderForView(View view, Context context) {
        return new PlayerViewHolder(view, context);
    }

    @Override
    /**
     * Encargado de actualizar los datos de un ViewHolder ya existente.
     */
    public void onBindViewHolder(PlayerViewHolder holder, int position) {

        SMPlayer player = players.get(position);
        holder.bindTitular(player);
    }

    @Override
    /**
     * Indica el número de elementos de la colección de datos.
     */
    public int getItemCount() {
        return players.size();
    }
}
