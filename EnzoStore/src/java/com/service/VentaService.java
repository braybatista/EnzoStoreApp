package com.service;

import com.DAO.DAODetallePedido;
import com.DAO.DAOVenta;
import com.controlador.ControladorPrenda;
import com.controlador.FaceUtil;
import com.modelo.Prenda;
import com.modelo.Usuario;
import com.modelo.Venta;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.faces.context.FacesContext;

public class VentaService implements Serializable {

    private DAOVenta daoVenta;
    private DAODetallePedido daoDetallePedido;
    
    public VentaService() {
        this.daoVenta = new DAOVenta();
        this.daoDetallePedido = new DAODetallePedido();
    }

    // Otros métodos y atributos existentes...
    public void registrarVenta(Venta v) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Usuario usuario = (Usuario) facesContext.getExternalContext().getSessionMap().get("user");
            List<Prenda> productos = (List<Prenda>) facesContext.getExternalContext().getSessionMap().get("carrito");
            Map<Integer, Map<String, Object>> dataFormatted = getProductsFromList(productos);
            String prendas = getJoiningNamesProducts(dataFormatted);
            String valorTotal = String.valueOf(productos.stream().mapToDouble(Prenda::getPrecio).sum());
            int cantidadProductos = productos.size();
            String sentencia = "insert into ventas values(null,'" + v.getNombre() + "','" + v.getDireccion() + "','" + v.getPuntodereferencia() + "','" + v.getTelefono() + "','" + usuario.getId() + "','" +  prendas + "','" + cantidadProductos + "','" + valorTotal + "');";
            Venta objVent = daoVenta.insertarVenta(sentencia);
            
            for (Entry<Integer, Map<String, Object>> iterator : dataFormatted.entrySet()) {
                String sentenciaDetalle = "INSERT INTO detalle_pedido_prenda VALUES("
                        + "null, '" 
                        + objVent.getId() + "','" 
                        + iterator.getKey() + "','" 
                        + iterator.getValue().get("cantidad") + "');";
                daoDetallePedido.insertarDetallePedido(sentenciaDetalle);
            }
            
            String informacionRegistro
                        = "factura: " + objVent.getId() + "\n"
                        + "Nombre y Apellido: " + v.getNombre() + "\n"
                        + "Direccion: " + v.getDireccion() + "\n"
                        + "Punto de Referencia: " + v.getPuntodereferencia() + "\n"
                        + "Telefono: " + v.getTelefono() + "\n"
                        + "Fecha y Hora: " + obtenerFechaHoraActual() + "\n"
                        + "Productos: " + prendas + "\n"
                        + "Cantidad: " + cantidadProductos + "\n"
                        + "Total del Carrito: $" + valorTotal + "\n\n";

            escribirEnArchivo("C:\\Users\\USUARIO\\OneDrive\\Escritorio\\EnzoStore\\RegistroVentas.txt", informacionRegistro);
            FaceUtil.addInfoMessage("pedido realizado conexito");
                
        } catch (Exception ex) {
            Logger.getLogger(VentaService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Map<Integer, Map<String, Object>> getProductsFromList(List<Prenda> productos) {
        Map<Integer, Map<String, Object>> dataFormatted = new HashMap<>();
        HashSet<Prenda> data = new HashSet<>(productos);

        data.stream().forEach(user -> {
            Map<String, Object> dataMap = new HashMap<>();
            Integer count = productos.stream().filter(iterator -> iterator.getId() == user.getId()).collect(Collectors.toList()).size();
            dataMap.put("id", user.getId());
            dataMap.put("nombre", user.getNombre());
            dataMap.put("cantidad", count);
            dataMap.put("valor", user.getPrecio());
            dataFormatted.put(user.getId(), dataMap);
        });

        return dataFormatted;
    }
    
    private static String getJoiningNamesProducts(Map<Integer, Map<String, Object>> dataFormatted) {
    	return dataFormatted.entrySet()
                .stream()
                .map(e -> String.valueOf(e.getValue().get("id") + " - " + e.getValue().get("nombre")+ " - " + e.getValue().get("cantidad")))
                .collect(Collectors.joining(";")); 
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

    public void generarTicket(Venta v, ControladorPrenda p) {
        daoVenta.generarTicketTXT(v, p);
    }
}
