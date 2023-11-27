/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

/**
 *
 * @author User
 */
public class Administrador extends Usuario{
    int codigoacceso;

    public Administrador() {
    }

    public Administrador(int codigoacceso) {
        this.codigoacceso = codigoacceso;
    }

    public Administrador(int codigoacceso, String nombre, String apellido, String id, String correo, String contraseña, String tipo_contacto) {
       super(nombre, apellido, id, correo, contraseña, tipo_contacto);
        this.codigoacceso = codigoacceso;
    }

    public int getCodigoacceso() {
        return codigoacceso;
    }

    public void setCodigoacceso(int codigoacceso) {
        this.codigoacceso = codigoacceso;
    }
    
    
    
}
