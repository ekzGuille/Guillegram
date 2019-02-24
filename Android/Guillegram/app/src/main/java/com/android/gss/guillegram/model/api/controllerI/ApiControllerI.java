package com.android.gss.guillegram.model.api.controllerI;

import com.android.gss.guillegram.model.api.beans.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiControllerI {
//    https://code.tutsplus.com/es/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845

    @GET("usuarios/list")
    Call<List<Usuario>> getAllUsuarios();

    @GET("destinos/list")
    Call<List<Usuario>> getAllDestinos();

    @POST("usuarios/login")
    Call<Usuario> getLogin(@Field("username_mail") String username_mail, @Field("contrasena") String contrasena);

    @POST("usuarios/register")
    Call<Usuario> register(@Body Usuario u);
}
