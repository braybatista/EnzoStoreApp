package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.conexion.Conexion;
import com.controlador.ControladorPrenda;
import com.controlador.FaceUtil;
import com.modelo.Prenda;
import com.modelo.Usuario;
import com.modelo.Venta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class DAOVenta {

    PreparedStatement ps;
    ResultSet rs;

    public void registrarPedido(Venta v , Usuario usuario) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = null;

        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("INSERT INTO ventas (nombreyapellido, direccion, punto_referencia, telefono, idusario) VALUES (?,?,?,?,?)");

            ps.setString(1, v.getNombreYapellido());
            ps.setString(2, v.getDireccion());
            ps.setString(3, v.getPuntodereferencia());
            ps.setString(4, v.getTelefono());
            ps.setString(5, usuario.getId());
            
            int rss = ps.executeUpdate();

            if (rss > 0) {
                FaceUtil.addInfoMessage("registro exitoso");
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext externalContext = context.getExternalContext();
                externalContext.redirect("InicioDeSesion.xhtml");
            } else {
                FaceUtil.addErrorMessage("error al registrarse");
            }
            con.close();

        } catch (SQLException | IOException s) {
            FaceUtil.addErrorMessage("error en la base de datos al guardar registro " + s);
        }

    }

    public void mostrarPedido(Venta venta, List<Venta> registrodeventas) {

        PreparedStatement ps;
        ResultSet rs;
        Connection con = null;
        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("SELECT * FROM pedidos WHERE nombreyapellido = ? AND direccion = ? AND punto_referencia = ? AND telefono = ?");
            ps.setString(1, venta.getNombreYapellido());
            ps.setString(2, venta.getDireccion());
            ps.setString(3, venta.getPuntodereferencia());
            ps.setString(4, venta.getTelefono());

            rs = ps.executeQuery();

            while (rs.next()) {
                registrodeventas.add(Venta.load(rs));
            }
            con.close();

        } catch (SQLException s) {
            FaceUtil.addErrorMessage("error en la base de datos al iniciar sesion " + s);

        }

    }
    
    public List<Prenda> mostrarListaPedidosBD() {

        Connection con = null;
        ArrayList<Prenda> listapedidos = new ArrayList<>();
        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("SELECT * FROM prendas");
            rs = ps.executeQuery();
            while (rs.next()) {
                listapedidos.add(Prenda.loadCarrito(rs));
            }
            con.close();

        } catch (SQLException s) {
            FaceUtil.addErrorMessage("error en la base de datos al iniciar sesion " + s);

        }

        return listapedidos;
    }
    
    

//    public Venta leerIDBD(Venta v) throws Exception {
//        Venta venta = null;
//        Connection con = null;
//        try {
//            con = Conexion.getConection();
//            ps = con.prepareStatement("SELECT nombreyapellido, direccion, punto_referencia, telefono FROM pedidos WHERE id = ?");
//            ps.setInt(1, v.getId());
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                venta = new Venta();
//                venta.setId(rs.getInt("id"));
//                venta.setNombreYapellido(rs.getString("nombreyapellido"));
//                venta.setDireccion(rs.getString("direccion"));
//                venta.setPuntodereferencia(rs.getString("punto_referencia"));
//                venta.setTelefono(rs.getString("telefono"));
//            }
//        } catch (Exception e) {
//            throw e;
//
//        } finally {
//            con.close();
//        }
//        return venta;
//    }     
    

   

    public void generarTicketTXT(Venta v, ControladorPrenda p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
