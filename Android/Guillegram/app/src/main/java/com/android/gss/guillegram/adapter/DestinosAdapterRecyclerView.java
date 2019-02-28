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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.gss.guillegram.R;
import com.android.gss.guillegram.model.Picture;
import com.android.gss.guillegram.model.api.beans.Destino;
import com.android.gss.guillegram.util.AppData;
import com.android.gss.guillegram.views.PictureDetailActivity;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        return new DestinoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinoViewHolder pictureViewHolder, final int i) {
        Destino destino = items.get(i);

        pictureViewHolder.localizacionFoto.setText(destino.getNombre());
        pictureViewHolder.userNameCard.setText(destino.getUsuario().getNombre());
        pictureViewHolder.likeNumberCard.setText(destino.getUsuariosFav().size() + " Me gusta");

        Glide.with(context).load(destino.getImagen()).centerCrop().into(pictureViewHolder.pictureCard);

        pictureViewHolder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PictureDetailActivity.class);
                AppData.setDestinoSeleccionado(items.get(i));
                intent.putExtra("destino", items.get(i));

                /*
                No es necesaria hacer la comrpobación porque nuestra SDK elegida es mayor a LOLLIPOP
                por lo que los moviles que tengan versiones menores que LOLLIPOP no podran correr nuestra App
                */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ((Activity) context).getWindow().setExitTransition(new Explode());
                    context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(((Activity) context), v, ((Activity) context).getString(R.string.trasitionname_picture)).toBundle());
                } else {
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
        private TextView localizacionFoto;
        private TextView likeNumberCard;

        public DestinoViewHolder(@NonNull View itemView) {
            super(itemView);

            pictureCard = itemView.findViewById(R.id.pictureCard);
            userNameCard = itemView.findViewById(R.id.userNameCard);
            localizacionFoto = itemView.findViewById(R.id.localizacionFoto);
            likeNumberCard = itemView.findViewById(R.id.likeNumberCard);
        }
    }


}
