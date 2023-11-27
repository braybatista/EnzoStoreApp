/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public class DetallePedido {

    private int id;
    private int idusuario;
    private String nombreusuario;
    private int venta;
    private String prenda;
    private int cantidad;

    public DetallePedido() {
    }

    public DetallePedido(int id, int idusuario, String nombreusuario, int venta, String prenda, int cantidad) {
        this.id = id;
        this.idusuario = idusuario;
        this.nombreusuario = nombreusuario;
        this.venta = venta;
        this.prenda = prenda;
        this.cantidad = cantidad;
    }
    
    public static DetallePedido loadDetallePedido(ResultSet rs) throws SQLException{
        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setId(rs.getInt(1));
        detallePedido.setIdusuario(rs.getInt(2));
        detallePedido.setNombreusuario(rs.getString(3));
        detallePedido.setVenta(rs.getInt(4));
        detallePedido.setPrenda(rs.getString(5));
        detallePedido.setCantidad(rs.getInt(6));  
        return detallePedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public String getPrenda() {
        return prenda;
    }

    public void setPrenda(String prenda) {
        this.prenda = prenda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DetallePedido{" + "id=" + id + ", idusuario=" + idusuario + ", nombreusuario=" + nombreusuario + ", venta=" + venta + ", prenda=" + prenda + ", cantidad=" + cantidad + '}';
    }

}
