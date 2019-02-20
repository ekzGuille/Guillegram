package com.android.gss.guillegram.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.gss.guillegram.R;
import com.android.gss.guillegram.model.Picture;
import com.android.gss.guillegram.views.PictureDetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder> {

    private Context context;
    private ArrayList<Picture> items;
    private int resource;

    public PictureAdapterRecyclerView(Context context, ArrayList<Picture> items, int resource) {
        this.context = context;
        this.items = items;
        this.resource = resource;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder pictureViewHolder, int i) {
        Picture picture = items.get(i);

        pictureViewHolder.userNameCard.setText(picture.getUsername());
        pictureViewHolder.timeCard.setText(picture.getTime());
        pictureViewHolder.likeNumberCard.setText(picture.getLikeNumber());

        Glide.with(context).load(picture.getPicture()).centerCrop().into(pictureViewHolder.pictureCard);

        pictureViewHolder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PictureDetailActivity.class);
                /*
                No es necesaria hacer la comrpobación porque nuestra SDK elegida es mayor a LOLLIPOP
                por lo que los moviles que tengan versiones menores que LOLLIPOP no podran correr nuestra App
                */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode ex = new Explode();
                    ex.setDuration(1000);
                    ((Activity) context).getWindow().setExitTransition(ex);
                    context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(((Activity) context),v,((Activity) context).getString(R.string.trasitionname_picture)).toBundle());
                }else{
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class PictureViewHolder extends RecyclerView.ViewHolder {

        private ImageView pictureCard;
        private TextView userNameCard;
        private TextView timeCard;
        private TextView likeNumberCard;

        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);

            pictureCard = itemView.findViewById(R.id.pictureCard);
            userNameCard = itemView.findViewById(R.id.userNameCard);
            timeCard = itemView.findViewById(R.id.timeCard);
            likeNumberCard = itemView.findViewById(R.id.likeNumberCard);
        }
    }


}
