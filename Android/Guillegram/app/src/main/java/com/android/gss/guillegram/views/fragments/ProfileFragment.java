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

import com.android.gss.guillegram.R;
import com.android.gss.guillegram.adapter.PictureAdapterRecyclerView;
import com.android.gss.guillegram.model.Picture;
import com.android.gss.guillegram.model.api.beans.Usuario;
import com.android.gss.guillegram.util.AppData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView usernameProfile;
    private Usuario usuario;

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
        usernameProfile.setText(usuario.getNombreUsuario());

        RecyclerView picturesRecycler = view.findViewById(R.id.pictureprofileRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(getActivity(), getPictures(), R.layout.cardview_picture);
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }



    public void showToolbar(String title, boolean btnUp, View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(btnUp);
    }


    public ArrayList<Picture> getPictures() {
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://i.imgur.com/eBF3WR7.jpg", "Federico", "2 días", "3 Me gusta"));
        pictures.add(new Picture("https://i.imgur.com/nElY8xo.jpg", "Romualdo", "4 días", "10 Me gusta"));
        pictures.add(new Picture("https://i.imgur.com/gNS5eeO.jpg", "Pascual", "1 días", "8 Me gusta"));
        pictures.add(new Picture("https://i.imgur.com/v3El8IJ.jpg", "Maite", "5 días", "6 Me gusta"));
        return pictures;
    }
}
