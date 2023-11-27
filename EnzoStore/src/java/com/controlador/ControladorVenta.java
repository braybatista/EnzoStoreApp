package com.controlador;

import com.modelo.Prenda;
import com.modelo.Venta;
import com.service.VentaService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ControladorVenta {

    private Venta pedido = new Venta();
    private List<Venta> listaventa = new ArrayList<>();
    private List<Prenda> listapedidos = new ArrayList<>();
    private VentaService service = new VentaService();
    private ControladorPrenda prendaTicket = new ControladorPrenda();
    private ControladorCarritoBean controladorCarritoBean = new ControladorCarritoBean();
    
    public void pasarACatalogoCliente() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.getSessionMap().remove("carrito");
            controladorCarritoBean.limpiarCarrito();
            externalContext.redirect("CatalogoCliente.xhtml");
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void registrarPedido() throws Exception {
        service.conectar();
        service.registrarVenta(pedido);
        pasarACatalogoCliente();
    }

    public void mostrarPedidos() {
        mostrarListaPedidos();
    }

    public void mostrarListaPedidos() {
        listapedidos = service.mostrarListaVenta();

    }

    public void generarTicketDeComprar() {
        service.generarTicket(pedido, prendaTicket);
    }

    public List<Venta> getListaventa() {
        return listaventa;
    }

    public void setListaventa(List<Venta> listaventa) {
        this.listaventa = listaventa;
    }

    public Venta getPedido() {
        return pedido;
    }

    public void setPedido(Venta pedido) {
        this.pedido = pedido;
    }

    public List<Prenda> getListapedidos() {
        return listapedidos;
    }

    public void setListapedidos(List<Prenda> listapedidos) {
        this.listapedidos = listapedidos;
    }
    
    /**
     * @return the obtenerTotalCarrito
     */
    public double obtenerTotalCarrito() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        List<Prenda> productos = (List<Prenda>) facesContext.getExternalContext().getSessionMap().get("carrito");
        return productos.stream().mapToDouble(Prenda::getPrecio).sum();
    }


}
