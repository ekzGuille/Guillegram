package com.android.gss.guillegram.views;

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;

import com.android.gss.guillegram.R;

public class PictureDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        showToolbar("", true);
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
