package com.DAO; // coloca la clase compilada en tpvlibre/conexiones

import com.modelo.Venta;
import java.sql.*;  // libreria para trabajar con bases de datos desde java

/*author John Carlos Arrieta Arrieta */
public class ManejadorBaseDatos {

    private static Connection conexion; // Abtrae una conexion a la base de datos
    private String usuario = "root"; // usuario con permisos para conectarse a BD
    private String password = "toor"; // contrase�a del usuario que se puede conectar
    private String driver = "com.mysql.cj.jdbc.Driver"; // Clase del Driver de jConnector
    //cadena de conxion a la base de datos por defecto
    private String basedatos = "jdbc:mysql://localhost/bdenzostore";
    private static ManejadorBaseDatos instancia; // patron de dise�o singleton

    /* Patron de dise�o singleton que impide que existan al mismo tiemp dos o mas 
     instancias del obejeto ManejadorBaseDatos dentro del sistema   */
// cosntructor por defecto, le da valores por defecto a los atributos de la clase
    public static ManejadorBaseDatos getInstancia() {
        if (ManejadorBaseDatos.instancia == null) {
            ManejadorBaseDatos.instancia = new ManejadorBaseDatos();
        }
        return instancia;
    }
// metodo que establece una conexin a la base de datos

    public void conectar() throws Exception {
        if (this.getConexion() != null) {
            if (!this.getConexion().isClosed()) {
                return; // si hay una coexion previa no se conecta
            }
        }
        try {
            Class.forName(this.getDriver()); // obtine una istancia de la clase Diver
// establece la conexion con el Diver jconector y este a su vez con la base de datos
            this.setConexion(DriverManager.getConnection(this.getBasedatos(), this.getUsuario(), this.getPassword()));
        } catch (ClassNotFoundException er1) { // si ocurre un error crea un Log 
            throw new Exception("Error al Conectarse a la Base de Datos");
        } catch (SQLException er2) { // si ocurre un error crea un Log 
            throw new Exception("Error al Conectarse a la Base de Datos");
        }
    }

    // metodo para ejecutar sentencias del tipo INSERT, UPDATE o DELETE
    public Venta insertarVenta(String sentencia_sql) throws SQLException, Exception {
        
        Venta objvent = new Venta();
        // optiene un obejto para ejecutar las sentencias SQL sobre la BD
        Statement sentencia = this.getConexion().createStatement();
        try {
            int res = sentencia.executeUpdate(sentencia_sql, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet generatedKeys = sentencia.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                objvent.setId(generatedKeys.getLong(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
            sentencia.close();// cierra la conexion
            return objvent;
        } catch (SQLException er) { // si ocurre un error crea un Log
            throw new Exception("Error al Modificar la Base de Datos " + er.getMessage());
        }
    }
    
    // metodo para ejecutar sentencias del tipo INSERT, UPDATE o DELETE
    public long insertarDetallePedido(String sentencia_sql) throws SQLException, Exception {
        
        long idAuto = 0;
        // optiene un obejto para ejecutar las sentencias SQL sobre la BD
        Statement sentencia = this.getConexion().createStatement();
        try {
            int res = sentencia.executeUpdate(sentencia_sql, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet generatedKeys = sentencia.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                idAuto = generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
            sentencia.close();// cierra la conexion
            return idAuto;
        } catch (SQLException er) { // si ocurre un error crea un Log
            throw new Exception("Error al Modificar la Base de Datos " + er.getMessage());
        }
    }

    // metodo para ejecutar sentencias del tipo SELECT
    public ResultSet consultar(String sentencia_sql) throws Exception {
        try {
            // optiene un obejto para ejecutar las sentencias SQL sobre la BD
            Statement sentencia = this.getConexion().createStatement();
            // ejecuta la sentencia y devuelve el conjunto con los registros encontrados 
            ResultSet respuesta = sentencia.executeQuery(sentencia_sql);

            return respuesta;
        } catch (SQLException er3) {
            throw new Exception("Error al Consultar en la Base de Datos");
        }
    }

    public void desconectar(ResultSet respuesta) {
        Statement sentencia = null;
        try {
            if (respuesta != null) {
                sentencia = respuesta.getStatement();
                if (respuesta != null) {
                    respuesta.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            }
            if (this.getConexion() != null) {
                this.getConexion().close();
            }
        } catch (Exception er4) {
            respuesta = null;
            sentencia = null;
            this.setConexion(null);
        } finally {
            respuesta = null;
            sentencia = null;
            this.setConexion(null);
        }
    }

    public void limpiarResultados(ResultSet respuesta) {
        Statement sentencia = null;
        try {
            sentencia = respuesta.getStatement();
            if (respuesta != null) {
                respuesta.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
        } catch (Exception er4) {
            respuesta = null;
            sentencia = null;

        } finally {
            respuesta = null;
            sentencia = null;
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public String getDriver() {
        return driver;
    }

    public String getPassword() {
        return password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setBasedatos(String basedatos) {
        this.basedatos = basedatos;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public ManejadorBaseDatos() {

    }

    public String getBasedatos() {
        return basedatos;
    }

    public static void setInstancia(ManejadorBaseDatos aInstancia) {
        instancia = aInstancia;
    }

}
