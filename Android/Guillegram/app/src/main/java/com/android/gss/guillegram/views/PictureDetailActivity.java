package com.android.gss.guillegram.views;

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.gss.guillegram.R;
import com.android.gss.guillegram.model.api.beans.Destino;
import com.bumptech.glide.Glide;

public class PictureDetailActivity extends AppCompatActivity {
    private Destino destino;
    private ImageView imageHeader;
    private TextView userNameDetail;
    private TextView titleImage;
    private TextView textcontent_image_detail;
    private FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        showToolbar("", true);

        if(getIntent().hasExtra(("destino"))){

            destino = (Destino) getIntent().getExtras().getSerializable("destino");

            imageHeader = findViewById(R.id.imageHeader);
            userNameDetail = findViewById(R.id.userNameDetail);
            titleImage = findViewById(R.id.titleImage);
            textcontent_image_detail = findViewById(R.id.textcontent_image_detail);
            fab = findViewById(R.id.fab);

            Glide.with(this).load(destino.getImagen()).centerCrop().into(imageHeader);
            userNameDetail.setText(destino.getUsuario().getNombre());
            titleImage.setText(destino.getNombre());
            textcontent_image_detail.setText(destino.getDescripcion());

        }

        /*
        No es necesaria hacer la comrpobación porque nuestra SDK elegida es mayor a LOLLIPOP
        por lo que los moviles que tengan versiones menores que LOLLIPOP no podran correr nuestra App
        */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade());
        }
    }


    public void showToolbar(String title, boolean btnUp) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(btnUp);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolBar);
    }

}
