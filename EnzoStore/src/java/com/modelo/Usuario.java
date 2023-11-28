package com.modelo;

import java.io.File;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class Usuario implements Serializable {

    
    private String id;
    private String nombre;
    private String correo;
    private String contrasenia;
    private String tipoContacto;

    public Usuario() {
    }

    public Usuario(String correo, String contrasenia, String tipoContacto) {
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.tipoContacto = tipoContacto;
    }

    public Usuario(String nombre, String id, String correo, String contrasenia, String tipoContacto) {
        this.nombre = nombre;
        this.id = id;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.tipoContacto = tipoContacto;
    }

    public static Usuario load(ResultSet rs) throws SQLException {
        Usuario usu = new Usuario();
        usu.setId(rs.getString(1));
        usu.setNombre(rs.getString(2));
        usu.setCorreo(rs.getString(3));
        usu.setContrasenia(rs.getString(4));
        usu.setTipoContacto(rs.getString(5));

        return usu;
    }

    public String imagenPerfil() {
        String img = "";
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = servletContext.getRealPath("/image.png").replace("image.png", "Imagenes\\Prendas\\");
        String imagen = path + this.getNombre() + ".png";
        File f = new File(imagen);
        if (f.length() > 0) {
            img = this.getNombre().strip() + ".png";
        } else {
            img = "iperfil.png";
        }
        return img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(String tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", id=" + id + ", correo=" + correo + ", contrasenia=" + contrasenia + ", tipoContacto=" + tipoContacto + '}';
    }
}
