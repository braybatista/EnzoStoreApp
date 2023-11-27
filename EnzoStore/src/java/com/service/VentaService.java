package com.service;

import com.DAO.DAOVenta;
import com.DAO.ManejadorBaseDatos;
import com.controlador.ControladorPrenda;
import com.controlador.ControladorVenta;
import com.modelo.Prenda;
import com.modelo.Usuario;
import com.modelo.Venta;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.faces.context.FacesContext;

public class VentaService {

    private DAOVenta daoventa = new DAOVenta();
    private List<Venta> listaVentas = new ArrayList<>();
    private ManejadorBaseDatos manejador = new ManejadorBaseDatos();

    public List<Prenda> mostrarListaVenta() {
        return daoventa.mostrarListaPedidosBD();
    }

    public void conectar() throws Exception {
        manejador.conectar();
    }

    // Otros métodos y atributos existentes...
    public void registrarVenta(Venta v) throws Exception {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Usuario usuario = (Usuario) facesContext.getExternalContext().getSessionMap().get("user");
        String sentencia = "insert into ventas values(null,'" + v.getNombreYapellido() + "','" + v.getDireccion() + "','" + v.getPuntodereferencia() + "','" + v.getTelefono() + "','" + usuario.getId() + "');";

        Venta objVent = manejador.insertarVenta(sentencia);
        List<Prenda> productos = (List<Prenda>) facesContext.getExternalContext().getSessionMap().get("carrito");

        // Obtener el total del carrito desde la sesión de JSF
        ControladorVenta controladorVenta = (ControladorVenta) facesContext.getExternalContext().getSessionMap().get("controladorVenta");
        String totalCarrito = String.valueOf(productos.stream().mapToDouble(Prenda::getPrecio).sum());
        
        Map<Integer, Integer> dataFormatted = getProductsFromList(productos);
        
        for(Entry<Integer, Integer> iterator: dataFormatted.entrySet()){
            System.out.println("Key = " + iterator.getKey() + ", Value = " + iterator.getValue());
            String sentenciaDetalle = "INSERT INTO detalle_pedido VALUES(null, '" + usuario.getId() + "','" + objVent.getId() + "','" + iterator.getKey() + "','" + iterator.getValue() + "');";
            long idDetalle = manejador.insertarDetallePedido(sentenciaDetalle);
            
            String informacionRegistro = 
                "ID del pedido: " + idDetalle + "\n"
                + "Nombre y Apellido: " + v.getNombreYapellido() + "\n"
                + "Dirección: " + v.getDireccion() + "\n"
                + "Punto de Referencia: " + v.getPuntodereferencia() + "\n"
                + "Teléfono: " + v.getTelefono() + "\n"
                + "Fecha y Hora: " + obtenerFechaHoraActual() + "\n"
                + "Producto: " + iterator.getKey() + "\n"
                + "Cantidad: " + iterator.getValue() + "\n"
                + "Total del Carrito: $" + totalCarrito + "\n\n";

            escribirEnArchivo("C:\\Users\\USUARIO\\OneDrive\\Escritorio\\EnzoStoreRegistroVentas.txt", informacionRegistro);
        }
    }

    private static Map<Integer, Integer> getProductsFromList(List<Prenda> productos) {
    	Map<Integer, Integer> dataFormatted = new HashMap<>();
    	HashSet<Prenda> data = new HashSet<>(productos);
    	
    	data.stream().forEach(user -> {
            Integer count = productos.stream().filter(iterator -> iterator.getId() == user.getId()).collect(Collectors.toList()).size();
            dataFormatted.put(user.getId(), count);
    	});

        return dataFormatted;
    }

    private void escribirEnArchivo(String rutaArchivo, String contenido) {
        try {
            FileWriter fileWriter = new FileWriter(rutaArchivo, true);
            fileWriter.write(contenido);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String obtenerFechaHoraActual() {
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date fechaHora = new Date();
        return formatoFechaHora.format(fechaHora);
    }

    public void registrarPedido(Venta v, Usuario u) {
        daoventa.registrarPedido(v, u);
    }

    public List<Venta> mostrarPedidos(Venta v) {
        List<Venta> mostrar = new ArrayList<>();
        daoventa.mostrarPedido(v, mostrar);
        return mostrar;
    }

    public void generarTicket(Venta v, ControladorPrenda p) {
        daoventa.generarTicketTXT(v, p);
    }
}
