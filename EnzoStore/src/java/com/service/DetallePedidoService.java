package com.service;

import com.DAO.DAODetallePedido;
import com.DAO.ManejadorBaseDatos;
import com.modelo.DetallePedido;
import java.util.ArrayList;

import java.util.List;


public class DetallePedidoService {

    private DAODetallePedido daoObj = new DAODetallePedido();
    private List<DetallePedido> listaVentas = new ArrayList<>();
    private ManejadorBaseDatos manejador = new ManejadorBaseDatos();
    
    public void conectar() throws Exception {
        manejador.conectar();
    }

    public List<DetallePedido> mostrarListaVenta() {
        return daoObj.mostrarListaPedidosBD();
    }

}
