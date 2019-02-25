package com.android.gss.guillegram.util;

import com.android.gss.guillegram.model.api.beans.Destino;
import com.android.gss.guillegram.model.api.beans.Usuario;

import java.util.List;

public class AppData {

    private static Usuario usuario;
    private static List<Destino> listadoDestinos;
    private static Destino destinoSeleccionado;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        AppData.usuario = usuario;
    }

    public static List<Destino> getListadoDestinos() {
        return listadoDestinos;
    }

    public static void setListadoDestinos(List<Destino> listadoDestinos) {
        AppData.listadoDestinos = listadoDestinos;
    }

    public static Destino getDestinoSeleccionado() {
        return destinoSeleccionado;
    }

    public static void setDestinoSeleccionado(Destino destinoSeleccionado) {
        AppData.destinoSeleccionado = destinoSeleccionado;
    }
}
