package com.DAO;

import com.conexion.Conexion;
import com.controlador.FaceUtil;
import com.modelo.Prenda;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class DAOPrenda {

    PreparedStatement ps;
    ResultSet rs;

    public void registrarPrendaBD(Prenda prendaARegistrar) {
        Connection con = null;

        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("INSERT INTO prendas (nombre,colores,talla,descripcion,precio,estado) VALUES(?,?,?,?,?,?)");

            ps.setString(1, prendaARegistrar.getNombre());
            ps.setString(2, prendaARegistrar.getColor());
            ps.setString(3, prendaARegistrar.getTalla());
            ps.setString(4, prendaARegistrar.getDescripcion());
            ps.setDouble(5, prendaARegistrar.getPrecio());
            ps.setString(6, prendaARegistrar.getEstado());

            int rss = ps.executeUpdate();
            if (rss > 0) {
                FaceUtil.addErrorMessage("Registro de prendas exitoso");
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext externalContext = context.getExternalContext();
                externalContext.redirect("CatalogoVirtual.xhtml");
            } else {
                FaceUtil.addErrorMessage("Error al registrar la prenda");
            }
            con.close();
        } catch (SQLException | IOException e) {
            FaceUtil.addErrorMessage("ERROR AL GUARDAR ---> " + e);
        }

    }

    public Prenda leerIDBD(Prenda p) throws Exception {
        Prenda prenda = null;
        Connection con = null;
        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("SELECT id, nombre, colores, talla, descripcion, precio, estado FROM prendas WHERE id = ?");
            ps.setInt(1, p.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                prenda = new Prenda();
                prenda.setId(rs.getInt("id"));
                prenda.setNombre(rs.getString("nombre"));
                prenda.setColor(rs.getString("colores"));
                prenda.setTalla(rs.getString("talla"));
                prenda.setDescripcion(rs.getString("precio"));
                prenda.setEstado(rs.getString("estado"));
            }
        } catch (Exception e) {
            throw e;

        } finally {
            con.close();
        }

        return prenda;

    }

    public List<Prenda> mostrarListaPrendaBD() {

        Connection con = null;
        ArrayList<Prenda> listaprendas = new ArrayList<>();
        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("SELECT * FROM prendas");
            rs = ps.executeQuery();
            while (rs.next()) {
                listaprendas.add(Prenda.load(rs));
            }
            con.close();

        } catch (SQLException s) {
            FaceUtil.addErrorMessage("error en la base de datos al iniciar sesion " + s);

        }

        return listaprendas;
    }

    public void eliminarPrendaBD(int id) {
        Connection con = null;

        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("DELETE FROM prendas WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void modificarPrendaBD(Prenda prendaAModificar) throws Exception {
        Connection con = null;
        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("UPDATE prendas SET nombre = ?, colores = ?, talla = ?, descripcion = ?, precio = ?, estado = ? WHERE id = ?");
            ps.setString(1, prendaAModificar.getNombre());
            ps.setString(2, prendaAModificar.getColor());
            ps.setString(3, prendaAModificar.getTalla());
            ps.setString(4, prendaAModificar.getDescripcion());
            ps.setDouble(5, prendaAModificar.getPrecio());
            ps.setString(6, prendaAModificar.getEstado());
            ps.setInt(7, prendaAModificar.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally{
            con.close();
        
        }

    }

}
