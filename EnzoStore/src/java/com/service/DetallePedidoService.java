package com.service;

import com.DAO.DAODetallePedido;
import com.modelo.DetalleVenta;
import java.io.Serializable;
import java.util.List;

public class DetallePedidoService implements Serializable {

    private DAODetallePedido daoObj;
    
    public DetallePedidoService() {
        this.daoObj = new DAODetallePedido();
    }

    public List<DetalleVenta> mostrarListaVenta() {
        return daoObj.mostrarListaVenta();
    }

}
