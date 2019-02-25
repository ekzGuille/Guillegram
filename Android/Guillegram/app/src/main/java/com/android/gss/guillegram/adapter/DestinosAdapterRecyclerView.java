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
import com.android.gss.guillegram.model.api.beans.Destino;
import com.android.gss.guillegram.views.PictureDetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DestinosAdapterRecyclerView extends RecyclerView.Adapter<DestinosAdapterRecyclerView.DestinoViewHolder> {

    private Context context;
    private List<Destino> items;
    private int resource;

    public DestinosAdapterRecyclerView(Context context, List<Destino> items, int resource) {
        this.context = context;
        this.items = items;
        this.resource = resource;
    }

    @NonNull
    @Override
    public DestinoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);
        return new DestinoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinoViewHolder pictureViewHolder, int i) {
        Destino destino = items.get(i);

        pictureViewHolder.userNameCard.setText(destino.getUsuario().getNombre());
        pictureViewHolder.localizacionCard.setText(destino.getNombre());
//        pictureViewHolder.likeNumberCard.setText(picture.getLikeNumber());

        Glide.with(context).load(destino.getImagen()).centerCrop().into(pictureViewHolder.pictureCard);

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


    public class DestinoViewHolder extends RecyclerView.ViewHolder {

        private ImageView pictureCard;
        private TextView userNameCard;
        private TextView localizacionCard;
//        private TextView likeNumberCard;

        public DestinoViewHolder(@NonNull View itemView) {
            super(itemView);

            pictureCard = itemView.findViewById(R.id.pictureCard);
            userNameCard = itemView.findViewById(R.id.userNameCard);
            localizacionCard = itemView.findViewById(R.id.localizacionFoto);
//            likeNumberCard = itemView.findViewById(R.id.likeNumberCard);
        }
    }


}
