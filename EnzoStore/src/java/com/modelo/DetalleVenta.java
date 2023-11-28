package com.modelo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetalleVenta implements Serializable {

    private int id;
    private int idusuario;
    private String usuario;
    private String prendas;
    private int cantidad;
    private double total;

    public DetalleVenta() {
    }

    public DetalleVenta(int id, int idusuario, String usuario, int venta, String prendas, int cantidad, double total) {
        this.id = id;
        this.idusuario = idusuario;
        this.usuario = usuario;
        this.prendas = prendas;
        this.cantidad = cantidad;
        this.total = total;
    }

    public static DetalleVenta loadDetalleVenta(ResultSet rs) throws SQLException {
        DetalleVenta detallePedido = new DetalleVenta();
        detallePedido.setId(rs.getInt(1));
        detallePedido.setIdusuario(rs.getInt(2));
        detallePedido.setUsuario(rs.getString(3));
        detallePedido.setPrendas(rs.getString(4));
        detallePedido.setCantidad(rs.getInt(5));
        detallePedido.setTotal(rs.getInt(6));
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPrendas() {
        return prendas;
    }

    public void setPrendas(String prendas) {
        this.prendas = prendas;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "id=" + id + ", idusuario=" + idusuario + ", usuario=" + usuario + ", prendas=" + prendas + ", cantidad=" + cantidad + ", total=" + total + '}';
    }

}
