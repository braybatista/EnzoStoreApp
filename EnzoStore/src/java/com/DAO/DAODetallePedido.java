package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.conexion.Conexion;
import com.controlador.FaceUtil;
import com.modelo.DetalleVenta;
import com.modelo.Usuario;
import java.io.Serializable;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;

public class DAODetallePedido implements Serializable {
    
    Statement st;
    PreparedStatement ps;
    ResultSet rs;
    
    public long insertarDetallePedido(String sentencia_sql) throws SQLException, Exception {
        long idAuto = 0;
        Connection con;
        try {
            con = Conexion.getConection();
            st = con.createStatement();
            st.executeUpdate(sentencia_sql, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idAuto = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            st.close();// cierra la conexion
            return idAuto;
        } catch (SQLException er) { // si ocurre un error crea un Log
            throw new Exception("Error al Modificar la Base de Datos " + er.getMessage());
        }
    }

    public List<DetalleVenta> mostrarListaVenta() {
        Connection con;
        ArrayList<DetalleVenta> listapedidos = new ArrayList<>();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Usuario usuario = (Usuario) facesContext.getExternalContext().getSessionMap().get("user");
        try {
            con = Conexion.getConection();
            ps = con.prepareStatement("select * from obtener_detalles_pedido where idusuario = " + usuario.getId() + ";");
            rs = ps.executeQuery();
            while (rs.next()) {
                listapedidos.add(DetalleVenta.loadDetalleVenta(rs));
            }
            con.close();

        } catch (SQLException s) {
            FaceUtil.addErrorMessage("error en la base de datos al iniciar sesion " + s);
        }

        return listapedidos;
    }
}
