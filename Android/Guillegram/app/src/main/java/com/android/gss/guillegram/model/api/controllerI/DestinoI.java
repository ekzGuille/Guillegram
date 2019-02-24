package com.android.gss.guillegram.model.api.controllerI;

import com.android.gss.guillegram.model.api.beans.Destino;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DestinoI {

    @GET("destinos/list")
    Call<List<Destino>> getDestinos();

}
