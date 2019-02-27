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
import android.widget.Toast;

import com.android.gss.guillegram.R;
import com.android.gss.guillegram.model.api.beans.Destino;
import com.android.gss.guillegram.model.api.beans.Usuario;
import com.android.gss.guillegram.model.api.controllerI.ApiServiceI;
import com.android.gss.guillegram.util.ApiUtils;
import com.android.gss.guillegram.util.AppData;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PictureDetailActivity extends AppCompatActivity {
    private Destino destino;
    private ImageView imageHeader;
    private TextView userNameDetail;
    private TextView titleImage;
    private TextView textcontent_image_detail;
    private FloatingActionButton fab;
    private ApiServiceI apiServiceI;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        showToolbar("", true);

        if (getIntent().hasExtra(("destino"))) {

            destino = (Destino) getIntent().getExtras().getSerializable("destino");
            apiServiceI = ApiUtils.getAPIService();
            imageHeader = findViewById(R.id.imageHeader);
            userNameDetail = findViewById(R.id.userNameDetail);
            titleImage = findViewById(R.id.titleImage);
            textcontent_image_detail = findViewById(R.id.textcontent_image_detail);
            fab = findViewById(R.id.fab);

            Glide.with(this).load(destino.getImagen()).centerCrop().into(imageHeader);
            userNameDetail.setText(destino.getUsuario().getNombre());
            titleImage.setText(destino.getNombre());
            textcontent_image_detail.setText(destino.getDescripcion());

            fab = findViewById(R.id.fab);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (AppData.getUsuario() != null) {
                        usuario = AppData.getUsuario();
                        cambiarFavorito(v, usuario, destino);
                    } else {
                        Toast.makeText(getBaseContext(), "Debes logearte para marcar favorito", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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

    public void cambiarFavorito(final View view, Usuario usuario, final Destino destino) {

        apiServiceI.updateFavoritosDestinos(usuario.getId(), destino).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Integer l = response.body();
                    if (l.equals(-1)) {
                        Toast.makeText(getBaseContext(), "Destino borrado", Toast.LENGTH_SHORT).show();
                        fab.setBackgroundResource(R.drawable.heart_outline);
                    } else {
                        Toast.makeText(getBaseContext(), "Destino añadido", Toast.LENGTH_SHORT).show();
                        fab.setBackgroundResource(R.drawable.heart_full);
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

}
