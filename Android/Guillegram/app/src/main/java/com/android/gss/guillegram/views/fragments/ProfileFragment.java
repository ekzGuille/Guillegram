package com.android.gss.guillegram.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.gss.guillegram.R;
import com.android.gss.guillegram.adapter.DestinosAdapterRecyclerView;
import com.android.gss.guillegram.adapter.PictureAdapterRecyclerView;
import com.android.gss.guillegram.model.Picture;
import com.android.gss.guillegram.model.api.beans.Destino;
import com.android.gss.guillegram.model.api.beans.Usuario;
import com.android.gss.guillegram.model.api.controllerI.ApiServiceI;
import com.android.gss.guillegram.util.ApiUtils;
import com.android.gss.guillegram.util.AppData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView usernameProfile;
    private Usuario usuario;
    private ApiServiceI apiServiceI;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        showToolbar("", false, view);

        usuario = AppData.getUsuario();
        usernameProfile = view.findViewById(R.id.usernameProfile);
        usernameProfile.setText(usuario.getNombre());

        apiServiceI = ApiUtils.getAPIService();

        getFotosUsuario(view, usuario.getId());
        return view;
    }


    public void showToolbar(String title, boolean btnUp, View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(btnUp);
    }

    private void getFotosUsuario(final View view, int id) {

        apiServiceI.getDestinosPublicados(id).enqueue(new Callback<List<Destino>>() {
            @Override
            public void onResponse(Call<List<Destino>> call, Response<List<Destino>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Destino> l = response.body();
                    AppData.setListadoDestinosPerfil(l);
                    RecyclerView destinosRecycler = view.findViewById(R.id.pictureprofileRecycler);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                    destinosRecycler.setLayoutManager(linearLayoutManager);

                    DestinosAdapterRecyclerView destinosAdapterRecyclerView = new DestinosAdapterRecyclerView(getActivity(), l, R.layout.cardview_destino);
                    destinosRecycler.setAdapter(destinosAdapterRecyclerView);
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Error obteniendo información del perfil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Destino>> call, Throwable t) {
                Toast.makeText(getActivity().getBaseContext(), "Error obteniendo información del perfil", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
