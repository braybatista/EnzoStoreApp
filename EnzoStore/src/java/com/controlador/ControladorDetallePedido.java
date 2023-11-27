package com.controlador;

import com.modelo.DetallePedido;
import com.service.DetallePedidoService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ControladorDetallePedido {

    private List<DetallePedido> listaDetallePedido = new ArrayList<>();
    private List<DetallePedido> listapedidos = new ArrayList<>();
    private DetallePedidoService service = new DetallePedidoService();

    public ControladorDetallePedido() {
        mostrarListaPedidos();
    }
    
    public void mostrarPedidos() {
        mostrarListaPedidos();
    }

    private void mostrarListaPedidos() {
        listapedidos = service.mostrarListaVenta();
    }

    public List<DetallePedido> getListaDetallePedido() {
        return listaDetallePedido;
    }

    public void setListaDetallePedido(List<DetallePedido> listaDetallePedido) {
        this.listaDetallePedido = listaDetallePedido;
    }

    public List<DetallePedido> getListapedidos() {
        return listapedidos;
    }

    public void setListapedidos(List<DetallePedido> listapedidos) {
        this.listapedidos = listapedidos;
    }

    
}
