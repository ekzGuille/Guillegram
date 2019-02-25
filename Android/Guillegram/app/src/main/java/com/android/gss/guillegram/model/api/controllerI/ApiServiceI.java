package com.android.gss.guillegram.model.api.controllerI;

import com.android.gss.guillegram.model.api.beans.Destino;
import com.android.gss.guillegram.model.api.beans.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServiceI {
//    https://code.tutsplus.com/es/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845

    @GET("usuarios/list")
    Call<List<Usuario>> getAllUsuarios();

    @GET("destinos/list")
    Call<List<Destino>> getAllDestinos();

    @GET("usuarios/login/{username_mail}/{contrasena}")
    Call<Usuario> getLogin(@Path("username_mail") String username_mail, @Path("contrasena") String contrasena);

    @POST("usuarios/register")
    @FormUrlEncoded
    Call<Usuario> register(@Body Usuario u);
}
