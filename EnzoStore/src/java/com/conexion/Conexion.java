/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.conexion;

import com.controlador.FaceUtil;
import com.utilidades.Constants;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author User
 */
public class Conexion {

    public void doit() {
        Properties prop = new Properties();
        try (InputStream inputStream = Conexion.class.getResourceAsStream("/resources.properties")) {
            System.out.println("hello " + Conexion.class.getResourceAsStream("").toString());
            prop.load(inputStream);
            System.out.println("hello " + prop.values());
            System.out.println("hello " + prop.getProperty("x", "default"));

        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

    }

    public static Connection getConection() {
        Connection con = null;

        try {
            Class.forName(Constants.DRIVER);
            con = (Connection) DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
            FaceUtil.addErrorMessage(null, "conexion exitosa");
        } catch (HeadlessException | ClassNotFoundException | SQLException s) {
            FaceUtil.addErrorMessage("error en conexion " + s);
        }
        return con;
    }

}
