package com.android.gss.guillegram.model.api.beans;

public class UsuarioDestinoFav {
    private int usuarios_fav_id_usuario;
    private int destinos_fav_id_destino;

    public int getUsuarios_fav_id_usuario() {
        return usuarios_fav_id_usuario;
    }

    public void setUsuarios_fav_id_usuario(int usuarios_fav_id_usuario) {
        this.usuarios_fav_id_usuario = usuarios_fav_id_usuario;
    }

    public int getDestinos_fav_id_destino() {
        return destinos_fav_id_destino;
    }

    public void setDestinos_fav_id_destino(int destinos_fav_id_destino) {
        this.destinos_fav_id_destino = destinos_fav_id_destino;
    }

    @Override
    public String toString() {
        return "UsuarioDestinoFav{" +
                "usuarios_fav_id_usuario=" + usuarios_fav_id_usuario +
                ", destinos_fav_id_destino=" + destinos_fav_id_destino +
                '}';
    }
}
