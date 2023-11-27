/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class Venta {

    private long id;
    private String nombreYapellido;
    private String telefono;
    private String lugardeentrega;
    private String departamento;
    private String municipio;
    private String corregimiento;
    private String direccion;
    private String puntodereferencia;
    private double precio;

    public static Venta load(ResultSet rs) throws SQLException {
        Venta venta = new Venta();
        venta.setId(rs.getInt(1));
        venta.setNombreYapellido(rs.getString(2));
        venta.setDireccion(rs.getString(3));
        venta.setPuntodereferencia(rs.getString(4));
        venta.setTelefono(rs.getString(5));

        return venta;
    }

    public Venta() {
    }

    public Venta(int id, String nombreYapellido, String telefono, String direccion, String puntodereferencia, double precio) {
        this.id = id;
        this.nombreYapellido = nombreYapellido;
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

    public String getNombreYapellido() {
        return nombreYapellido;
    }

    public void setNombreYapellido(String nombreYapellido) {
        this.nombreYapellido = nombreYapellido;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCorregimiento() {
        return corregimiento;
    }

    public void setCorregimiento(String corregimiento) {
        this.corregimiento = corregimiento;
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

    public String getLugardeentrega() {
        return lugardeentrega;
    }

    public void setLugardeentrega(String lugardeentrega) {
        this.lugardeentrega = lugardeentrega;
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", nombreYapellido=" + nombreYapellido + ", telefono=" + telefono + ", lugardeentrega=" + lugardeentrega + ", departamento=" + departamento + ", municipio=" + municipio + ", corregimiento=" + corregimiento + ", direccion=" + direccion + ", puntodereferencia=" + puntodereferencia + ", precio=" + precio + '}';
    }

}
