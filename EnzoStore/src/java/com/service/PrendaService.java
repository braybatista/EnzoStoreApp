package com.service;

import com.DAO.DAOPrenda;
import com.modelo.Prenda;
import java.io.Serializable;
import java.util.List;

public class PrendaService implements Serializable {
    private DAOPrenda daoprenda;

    public PrendaService() {
        this.daoprenda = new DAOPrenda();
    }
    
    public void almacenar(Prenda p){
        daoprenda.registrarPrendaBD(p);
    }
    
    public List<Prenda> mostrarListaPrenda(){
        return daoprenda.mostrarListaPrendaBD();
    }
    
    public Prenda validarPrendaService(int id) throws Exception{
        return daoprenda.validarPrendaDao(id);
    }
    
    public void modificarPrenda(Prenda prendaAModificar) throws Exception{
        daoprenda.modificarPrendaBD(prendaAModificar);
    }
    
    public void eliminarPrenda(int id){
        daoprenda.eliminarPrendaBD(id);
    }
}
