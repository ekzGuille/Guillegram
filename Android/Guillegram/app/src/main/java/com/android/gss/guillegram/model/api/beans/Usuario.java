package com.android.gss.guillegram.model.api.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("nombreUsuario")
    @Expose
    private String nombreUsuario;
    @SerializedName("contrasena")
    @Expose
    private String contrasena;
    @SerializedName("usuarios")
    @Expose
    private List<Destino> usuarios;
    @SerializedName("destinosFav")
    @Expose
    private List<Destino> destinosFav;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Destino> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Destino> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Destino> getDestinosFav() {
        return destinosFav;
    }

    public void setDestinosFav(List<Destino> destinosFav) {
        this.destinosFav = destinosFav;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", usuarios=" + usuarios +
                ", destinosFav=" + destinosFav +
                '}';
    }
}
