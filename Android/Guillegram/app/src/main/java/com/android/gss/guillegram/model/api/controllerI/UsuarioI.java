package com.android.gss.guillegram.model.api.controllerI;

import com.android.gss.guillegram.model.api.beans.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsuarioI {
    @GET("usuarios/list")
    Call<List<Usuario>> getUsuarios();

}
