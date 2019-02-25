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

import com.android.gss.guillegram.R;
import com.android.gss.guillegram.adapter.DestinosAdapterRecyclerView;
import com.android.gss.guillegram.adapter.PictureAdapterRecyclerView;
import com.android.gss.guillegram.model.Picture;
import com.android.gss.guillegram.model.api.beans.Destino;
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
public class HomeFragmentPictures extends Fragment {

    public HomeFragmentPictures() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.toolbar_title_home), false, view);

        RecyclerView picturesRecycler = view.findViewById(R.id.pictureRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(getActivity(), getPictures(), R.layout.cardview_picture)
        ;
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);
        return view;
    }

    public ArrayList<Picture> getPictures() {
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://i.imgur.com/eBF3WR7.jpg", "Federico", "2 días", "3 Me gusta"));
        pictures.add(new Picture("https://i.imgur.com/nElY8xo.jpg", "Romualdo", "4 días", "10 Me gusta"));
        pictures.add(new Picture("https://i.imgur.com/gNS5eeO.jpg", "Pascual", "1 días", "8 Me gusta"));
        pictures.add(new Picture("https://i.imgur.com/v3El8IJ.jpg", "Maite", "5 días", "6 Me gusta"));
        return pictures;
    }


    public void showToolbar(String title, boolean btnUp, View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(btnUp);
    }

}
