/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class Venta implements Serializable {

    private long id;
    private String nombre;
    private String telefono;
    private String direccion;
    private String puntodereferencia;
    private double precio;

    public static Venta load(ResultSet rs) throws SQLException {
        Venta venta = new Venta();
        venta.setId(rs.getInt(1));
        venta.setNombre(rs.getString(2));
        venta.setDireccion(rs.getString(3));
        venta.setPuntodereferencia(rs.getString(4));
        venta.setTelefono(rs.getString(5));

        return venta;
    }

    public Venta() {
    }

    public Venta(int id, String nombre, String telefono, String direccion, String puntodereferencia, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.puntodereferencia = puntodereferencia;
        this.precio = precio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPuntodereferencia() {
        return puntodereferencia;
    }

    public void setPuntodereferencia(String puntodereferencia) {
        this.puntodereferencia = puntodereferencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion + ", puntodereferencia=" + puntodereferencia + ", precio=" + precio + '}';
    }

}
