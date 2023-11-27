/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.conexion.Conexion;
import com.controlador.FaceUtil;
import com.modelo.Usuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class DAOUsuario implements Serializable {

    public void registrarUsuario(Usuario usuario) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = null;

        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("INSERT INTO usuarios (nombre, correo, contraseña, tipocontacto) VALUES (?,?,?,?)");

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getContrasenia());
            ps.setString(4, usuario.getTipoContacto());

            int rss = ps.executeUpdate();

            if (rss > 0) {
                FaceUtil.addInfoMessage("registro exitoso");
            } else {
                FaceUtil.addErrorMessage("error al registrarse");
            }
            con.close();

        } catch (SQLException s) {
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
        } catch (SQLException s) {
            s.getMessage();
        }
        return usuario;

    }

}
