/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service;

import com.DAO.DAOUsuario;
import com.modelo.Usuario;

/**
 *
 * @author User
 */
public class UsuarioService {
    DAOUsuario daoUsu = new DAOUsuario();
    
    public void registroUsuario(Usuario u){
        daoUsu.registrarUsuario(u);
    
    }
    
    
    public Usuario consultarUsuario(String c, String p){
        Usuario usu = daoUsu.consultar(c, p);
        return usu;
    }
}
