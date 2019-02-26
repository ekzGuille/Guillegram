package com.android.gss.guillegram.views.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
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
public class HomeFragment extends Fragment {

    private List<Destino> lstDestinos;
    private ApiServiceI apiServiceI;

    DestinosAdapterRecyclerView destinosAdapterRecyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.toolbar_title_home), false, view);

        apiServiceI = ApiUtils.getAPIService();
        getDestinos(view);
        return view;
    }

    public void getDestinos(final View view) {


        apiServiceI.getAllDestinos().enqueue(new Callback<List<Destino>>() {

            @Override
            public void onResponse(Call<List<Destino>> call, Response<List<Destino>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Destino> l = response.body();

                    AppData.setListadoDestinos(l);
                    RecyclerView destinosRecycler = view.findViewById(R.id.pictureRecycler);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                    destinosRecycler.setLayoutManager(linearLayoutManager);

                    DestinosAdapterRecyclerView destinosAdapterRecyclerView = new DestinosAdapterRecyclerView(getActivity(), l, R.layout.cardview_destino);
                    destinosRecycler.setAdapter(destinosAdapterRecyclerView);
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Error obteniendo imágenes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Destino>> call, Throwable t) {
                Toast.makeText(getActivity().getBaseContext(), "Error obteniendo imágenes", Toast.LENGTH_SHORT).show();
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
