/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.conexion.Conexion;
import com.controlador.FaceUtil;
import com.modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author User
 */
public class DAOUsuario {

    public void registrarUsuario(Usuario usuario) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = null;

        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("INSERT INTO usuarios (nombres, apellidos, correo, contraseña, tipo_contacto) VALUES (?,?,?,?,?)");

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContraseña());
            ps.setString(5, usuario.getTipo_contacto());

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

    public Usuario consultar(String correoInicioSesion, String contraseñaInicioSesion) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = null;

        Usuario usuario = null;
        try {
            con = Conexion.getConection();

            ps = con.prepareStatement("SELECT * FROM usuarios WHERE correo = ? AND contraseña = ?");
            ps.setString(1, correoInicioSesion);
            ps.setString(2, contraseñaInicioSesion);
            rs = ps.executeQuery();

            if (rs.next()) {

                usuario = new Usuario();
                usuario = Usuario.load(rs);

            }
            con.close();
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("user", usuario);
        } catch (Exception n) {
            n.getMessage();

        }
        return usuario;

    }

}
