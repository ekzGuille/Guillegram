package com.android.gss.guillegram.util;

import com.android.gss.guillegram.model.api.beans.Usuario;

public class AppData {

    private static Usuario usuario;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        AppData.usuario = usuario;
    }

}
