package com.supermanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm.model.Player;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by usuario on 23/12/2016.
 */

public class PlayerViewHolder extends RecyclerView.ViewHolder {

    private Context context;

    private TextView txtName;
    private TextView txtTeam;
    private TextView txtPoints;
    private TextView txtValue;
    private ImageView imgImage;
    private ImageView doubtful;
    private ImageView injury;
    private ImageView spanish;
    private ImageView extracommunity;
    private Button sell;
    private Button sign;
    private Button cancel;

    public PlayerViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        txtName = (TextView)itemView.findViewById(R.id.name);
        txtTeam = (TextView)itemView.findViewById(R.id.team);
        txtPoints = (TextView)itemView.findViewById(R.id.points);
        txtValue = (TextView)itemView.findViewById(R.id.value);
        imgImage = (ImageView)itemView.findViewById(R.id.imageUrl);
        doubtful = (ImageView)itemView.findViewById(R.id.doubtful);
        injury = (ImageView)itemView.findViewById(R.id.injury);
        spanish = (ImageView)itemView.findViewById(R.id.spanish);
        extracommunity = (ImageView)itemView.findViewById(R.id.extracommunity);
        sell = (Button)itemView.findViewById(R.id.sell);
        sign = (Button)itemView.findViewById(R.id.sign);
        cancel = (Button)itemView.findViewById(R.id.cancel);
    }

    public void bindTitular(Player player) {
        txtName.setText(player.getName());
        txtTeam.setText(player.getTeam());
        txtPoints.setText(player.getPoints());
        txtValue.setText(player.getPrice());
        Picasso.with(context).load(player.getImageUrl()).into(imgImage);
        //Glide.with(context).load(player.getImageUrl()).into(imgImage);
        visibleIfCondition(doubtful, player.isDoubtful());
        visibleIfCondition(injury, player.isInjured());
        visibleIfCondition(spanish, player.isNational());
        visibleIfCondition(extracommunity, player.isExtracommunity());
        visibleIfCondition(sell, !player.getSellUrl().isEmpty());
        visibleIfCondition(sign, !player.getBuyUrl().isEmpty());
        visibleIfCondition(cancel, !player.getCancelUrl().isEmpty());
    }

    private void visibleIfCondition(View view, boolean condition) {
        if (condition) {
            view.setVisibility(View.VISIBLE);
        }
        else {
            view.setVisibility(View.GONE);
        }
    }
}
