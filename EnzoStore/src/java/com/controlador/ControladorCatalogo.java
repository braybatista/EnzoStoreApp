package com.controlador;

import com.modelo.Prenda;
import com.service.PrendaService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ControladorCatalogo implements Serializable {

    private List<Prenda> carrito = new ArrayList<>();
    private List<Prenda> historial = new ArrayList<>();
    private Prenda productoSeleccionado;
    private int paginaActual;
    private final int productosPorPagina = 10;
    private List<Prenda> productos;

    public ControladorCatalogo() {
        // Cargar todos los productos al iniciar la aplicación
        productos = cargarProductos();
    }

    public List<Prenda> getProductos() {
        return productos;
    }

    private List<Prenda> cargarProductos() {
        PrendaService prendaService = new PrendaService();
        return prendaService.mostrarListaPrenda();
    }

    public List<Prenda> getCarrito() {
        return carrito;
    }

    public List<Prenda> getHistorial() {
        return historial;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public void paginaAnterior() {
        if (paginaActual > 1) {
            paginaActual--;
        }
    }

    public void agregarAlCarrito(Prenda prenda, boolean esAdministrador) {
        // Verificar si el usuario tiene permisos de administrador
        if (esAdministrador) {
            carrito.add(prenda);
        }
    }

    // ... Otros métodos existentes ...
    // Método para agregar un producto al carrito desde el diálogo de detalles
    public void agregarAlCarritoDesdeDetalle(boolean esAdministrador) {
        if (productoSeleccionado != null && esAdministrador) {
            carrito.add(productoSeleccionado);
        }
    }

    // Método para eliminar un producto del carrito
    public void eliminarDelCarrito(Prenda producto) {
        if (producto != null) {
            carrito.remove(producto);
        }
    }

    public Prenda getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Prenda productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public void setProductos(List<Prenda> productos) {
        this.productos = productos;
    }
    
    
}