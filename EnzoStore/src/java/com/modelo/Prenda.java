package com.modelo;

import java.io.File;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class Prenda implements Serializable {

    private int id;
    private String nombre;
    private String color;
    private String talla;
    private String descripcion;
    private Double precio;
    private String estado;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static Prenda load(ResultSet rs) throws SQLException {
        Prenda prenda = new Prenda();
        prenda.setId(rs.getInt(1));
        prenda.setNombre(rs.getString(2));
        prenda.setColor(rs.getString(3));
        prenda.setTalla(rs.getString(4));
        prenda.setDescripcion(rs.getString(5));
        prenda.setPrecio(rs.getDouble(6));
        prenda.setEstado(rs.getString(7));
        return prenda;
    }

    public static Prenda loadCarrito(ResultSet rs) throws SQLException {
        Prenda prenda = new Prenda();
        prenda.setId(rs.getInt(1));
        prenda.setNombre(rs.getString(2));
        prenda.setColor(rs.getString(3));
        prenda.setTalla(rs.getString(4));
        prenda.setPrecio(rs.getDouble(6));
        return prenda;
    }

    public String imagenPerfil() {
        String img = "";
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = servletContext.getRealPath("/image.png").replace("image.png", "imagenes\\Prendas\\");
        //this.setId(Long.getLong("5"));
        String imagen = path + this.getNombre() + ".png";
        File f = new File(imagen);
        //System.out.println(""+f.toString()+" "+f.exists()+" "+f.length()+" ");
        if (f.length() > 0) {
            img = this.getNombre() + ".png";
        } else {
            img = "image.png";
        }
        return img;
    }

    public Prenda() {
    }

    public Prenda(int id, String nombre, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Prenda(int id, String nombre, String color, String talla, String descripcion, Double precio, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.talla = talla;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Prenda{" + "id=" + id + ", nombre=" + nombre + ", color=" + color + ", talla=" + talla + ", descripcion=" + descripcion + ", precio=" + precio + ", estado=" + estado + ", index=" + index + '}';
    }

}
