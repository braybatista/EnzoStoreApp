package com.controlador;

import com.modelo.Prenda;
import com.service.PrendaService;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import com.utilidades.ImageUtils;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.file.UploadedFile;

@ManagedBean
@SessionScoped

public class ControladorPrenda implements Serializable {

    private Prenda prenda = new Prenda();
    private List<Prenda> listaprenda = new ArrayList<>();
    private PrendaService service = new PrendaService();

    private UploadedFile iprenda;

    public ControladorPrenda() {
        setPrenda(new Prenda());

    }

    public void crearPrenda() {
        service.almacenar(prenda);
        mostrarListaPrendas();
        subirImagenPrenda();
    }

    public void mostrarListaPrendas() {
        obtenerListaPrendas();
    }

    public void obtenerListaPrendas() {
        listaprenda = service.mostrarListaPrenda();

    }

    public void leerID(Prenda prendaALeer) throws Exception {
        Prenda temp;
        try {
            temp = service.leerIDPrenda(prendaALeer);

            if (temp != null) {
                this.prenda = temp;
            }
        } catch (Exception e) {
            throw e;
        }

    }

    public void modificar() throws Exception {
        try {
            service.modificarPrenda(prenda);
            obtenerListaPrendas();
            subirImagenPrenda();
        } catch (Exception e) {
            throw e;
        }

    }

    public String eliminar(int id) {
        service.eliminarPrenda(id);
        prenda = new Prenda();
        mostrarListaPrendas();
        return null;
    }

    public void subirImagenPrenda() {

        try {
            ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String path = sc.getRealPath("/image.png");
            path = path.replace("image.png", "Imagenes\\Prendas\\");
            System.out.println(path);
            ImageUtils.copyFile(prenda.getNombre() + ".png", iprenda.getInputStream(), path);
        } catch (IOException e) {
            Logger.getLogger(ControladorPrenda.class.getName()).log(Level.SEVERE, null, e);

        }

    }

    public void setIprenda(UploadedFile iprenda) {
        this.iprenda = iprenda;
    }

    public UploadedFile getIprenda() {
        return iprenda;
    }

    public String pasarARegistrodePrendas() throws IOException {
        return "registroDePrenda?faces-redirect=true";
    }

    public void pasarARegistroDeUsuario() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.redirect("RegistroDeUsuario.xhtml");

    }

    public void pasarAZonaDeVentas() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect("VistaVentas.xhtml");
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void pasarAvistaCliente() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect("CatalogoCliente.xhtml");
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void pasarAInicioDeSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect("InicioDeSesion.xhtml");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public List<Prenda> getListaprenda() {
        return listaprenda;
    }

    public void setListaprenda(List<Prenda> listaprenda) {
        this.listaprenda = listaprenda;
    }

}
