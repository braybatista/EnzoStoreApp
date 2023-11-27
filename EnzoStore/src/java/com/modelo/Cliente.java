/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

/**
 *
 * @author User
 */
public class Cliente extends Usuario{
    private String direccion;

    public Cliente() {
    }

    public Cliente(String direccion) {
        this.direccion = direccion;
    }

    public Cliente(String direccion, String nombre, String apellido, String id,String correo, String contraseña, String tipo_contacto) {
        super(id, nombre, apellido, correo, contraseña, tipo_contacto);
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
    
    
}
