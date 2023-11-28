package com.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.conexion.Conexion;
import com.controlador.ControladorPrenda;
import com.modelo.Venta;
import java.io.Serializable;
import java.sql.Statement;

public class DAOVenta implements Serializable {

    PreparedStatement ps;
    ResultSet rs;
   
    public Venta insertarVenta(String sentencia_sql) throws SQLException, Exception {

        Venta objvent = new Venta();
        
        try {
            Statement sentencia = Conexion.getConection().createStatement();
            sentencia.executeUpdate(sentencia_sql, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet generatedKeys = sentencia.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    objvent.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            sentencia.close();// cierra la conexion
            return objvent;
        } catch (SQLException er) { // si ocurre un error crea un Log
            throw new Exception("Error al Modificar la Base de Datos " + er.getMessage());
        }
    }

    public void generarTicketTXT(Venta v, ControladorPrenda p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
