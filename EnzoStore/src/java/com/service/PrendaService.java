package com.service;

import com.DAO.DAOPrenda;
import com.modelo.Prenda;
import java.util.List;
public class PrendaService {
    private DAOPrenda daoprenda = new DAOPrenda();
    
    public void almacenar(Prenda p){
        daoprenda.registrarPrendaBD(p);
    
    }
    
    public List<Prenda> mostrarListaPrenda(){
        
        return daoprenda.mostrarListaPrendaBD();
    }
    
    public Prenda leerIDPrenda(Prenda prendaLeer) throws Exception{
        return daoprenda.leerIDBD(prendaLeer);
    
    
    }
    
    public void modificarPrenda(Prenda prendaAModificar) throws Exception{
        daoprenda.modificarPrendaBD(prendaAModificar);
        
    
    }
    
    public void eliminarPrenda(int id){
        daoprenda.eliminarPrendaBD(id);
    }
    
}
