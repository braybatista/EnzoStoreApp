package com.controlador;

import com.modelo.Prenda;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@ManagedBean
@SessionScoped
public class ControladorCarritoBean implements Serializable {
    private ControladorDetallePedido controladorDetallePedido;
    private List<Prenda> carritoProductos = new ArrayList<>();
    private double totalCarrito = 0;
    
    public ControladorCarritoBean(){
        controladorDetallePedido = new ControladorDetallePedido();
    }

    public void agregarAlCarrito(Prenda prendaAMostrar) {
        Prenda prenda = new Prenda(prendaAMostrar);
        prenda.setIndex(carritoProductos.size()); // Asignar índice único
        carritoProductos.add(prenda);
        actualizarTotalCarrito();
        actualizarVista();
    }

    public void eliminarDelCarrito() {
        // Obtener el índice desde los parámetros de la solicitud
        String indexString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");
        if (indexString != null) {
            int index = Integer.parseInt(indexString);
            // Eliminar la prenda del carrito por su índice
            carritoProductos.remove(index);
            // Actualizar los índices restantes
            actualizarIndices();
            // Volver a calcular el total del carrito
            actualizarTotalCarrito();
            // Actualizar la vista de la DataTable en la página
            actualizarVista();
        }
    }

    private void actualizarIndices() {
        for (int i = 0; i < carritoProductos.size(); i++) {
            carritoProductos.get(i).setIndex(i);
        }
    }

    private void actualizarTotalCarrito() {
        double total = 0;
        for (Prenda prenda : carritoProductos) {
            total += prenda.getPrecio();
        }
        totalCarrito = total;
    }

    private void actualizarVista() {
        try {
            // Actualizar la vista usando PrimeFaces
            //PrimeFaces.current().ajax().update("carritoProductos", "carritoTotal");
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect("CatalogoCliente.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(ControladorCarritoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void realizarCompra() {
        try {
            // Lógica para realizar la compra, por ejemplo, almacenar en la base de datos.
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.getSessionMap().put("carrito", carritoProductos);
            externalContext.redirect("VistaVentas.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ControladorCarritoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiarCarrito() {
        carritoProductos.clear();
        setTotalCarrito(0);
        actualizarVista();
    }

    // Otros métodos y getters/setters según sea necesario
    public List<Prenda> getCarritoProductos() {
        return carritoProductos;
    }

    public double getTotalCarrito() {
        return totalCarrito;
    }

    public void setCarritoProductos(List<Prenda> carritoProductos) {
        this.carritoProductos = carritoProductos;
    }

    public void setTotalCarrito(double totalCarrito) {
        this.totalCarrito = totalCarrito;
    }

}
