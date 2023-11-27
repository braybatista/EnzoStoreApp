package com.controlador;

import com.modelo.DetalleVenta;
import com.service.DetallePedidoService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ControladorDetallePedido {

    private List<DetalleVenta> listaDetallePedido = new ArrayList<>();
    private List<DetalleVenta> listapedidos = new ArrayList<>();
    private DetallePedidoService service = new DetallePedidoService();

    public ControladorDetallePedido() {
        mostrarListaVenta();
    }
    
    public void mostrarPedidos() {
        mostrarListaVenta();
    }

    private void mostrarListaVenta() {
        listapedidos = service.mostrarListaVenta();
    }

    public List<DetalleVenta> getListaDetallePedido() {
        return listaDetallePedido;
    }

    public void setListaDetallePedido(List<DetalleVenta> listaDetallePedido) {
        this.listaDetallePedido = listaDetallePedido;
    }

    public List<DetalleVenta> getListapedidos() {
        return listapedidos;
    }

    public void setListapedidos(List<DetalleVenta> listapedidos) {
        this.listapedidos = listapedidos;
    }

    
}
