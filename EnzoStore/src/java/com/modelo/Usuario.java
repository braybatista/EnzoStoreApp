package com.modelo;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author User
 */
//Dialog modal. Primefaces
public class Usuario {

    private String nombre;
    private String apellido;
    private String id;
    private String correo;
    private String contraseña;
    private String tipo_contacto;

    public Usuario() {

    }

    public Usuario(String correo, String contraseña, String tipo_contacto) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.tipo_contacto = tipo_contacto;
    }

    public Usuario(String nombre, String apellido, String id, String correo, String contraseña, String tipo_contacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public static Usuario load(ResultSet rs) throws SQLException {
        Usuario usu = new Usuario();
        usu.setId(rs.getString(1));
        usu.setNombre(rs.getString(2));
        usu.setApellido(rs.getString(3));
        usu.setCorreo(rs.getString(4));
        usu.setContraseña(rs.getString(5));
        usu.setTipo_contacto(rs.getString(6));

        return usu;
    }

    public String imagenPerfil() {
        String img = "";
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = servletContext.getRealPath("/image.png").replace("image.png", "Imagenes\\Prendas\\");
        //this.setId(Long.getLong("5"));
        String imagen = path + this.getNombre() + ".png";
        File f = new File(imagen);
        //System.out.println(""+f.toString()+" "+f.exists()+" "+f.length()+" ");
        if (f.length() > 0) {
            img = this.getNombre() + ".png";
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipo_contacto() {
        return tipo_contacto;
    }

    public void setTipo_contacto(String tipo_contacto) {
        this.tipo_contacto = tipo_contacto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.nombre);
        hash = 71 * hash + Objects.hashCode(this.apellido);
        hash = 71 * hash + Objects.hashCode(this.correo);
        hash = 71 * hash + Objects.hashCode(this.contraseña);
        hash = 71 * hash + Objects.hashCode(this.tipo_contacto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.contraseña, other.contraseña)) {
            return false;
        }
        return Objects.equals(this.tipo_contacto, other.tipo_contacto);
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.apellido; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    

}
