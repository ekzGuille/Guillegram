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
import android.widget.Toast;

import com.android.gss.guillegram.R;
import com.android.gss.guillegram.adapter.DestinosAdapterRecyclerView;
import com.android.gss.guillegram.model.api.beans.Destino;
import com.android.gss.guillegram.model.api.beans.Usuario;
import com.android.gss.guillegram.model.api.controllerI.ApiServiceI;
import com.android.gss.guillegram.util.ApiUtils;
import com.android.gss.guillegram.util.AppData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritoFragment extends Fragment {

    private ApiServiceI apiServiceI;
    private Usuario usuario;

    public FavoritoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorito, container, false);

        usuario = AppData.getUsuario();

        apiServiceI = ApiUtils.getAPIService();

        getFavoritosUsuario(view, usuario.getId());

        showToolbar("Mis favoritos", false, view);

        return view;
    }


    private void getFavoritosUsuario(final View view, int id) {
        apiServiceI.getUsuario(id).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //Usuario usuario = response.body();
                    //List<Destino> l = usuario.getDestinosFav();
                    System.out.println(response.body().toString());

//                    AppData.setListadoDestinosFavoritos(l);
//                    RecyclerView destinosRecycler = view.findViewById(R.id.pictureFavRecycler);
//
//                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//                    destinosRecycler.setLayoutManager(linearLayoutManager);
//
//                    DestinosAdapterRecyclerView destinosAdapterRecyclerView = new DestinosAdapterRecyclerView(getActivity(), l, R.layout.cardview_destino);
//                    destinosRecycler.setAdapter(destinosAdapterRecyclerView);
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Error obteniendo favoritos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getActivity().getBaseContext(), "Error obteniendo favoritos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showToolbar(String title, boolean btnUp, View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(btnUp);
    }

}
