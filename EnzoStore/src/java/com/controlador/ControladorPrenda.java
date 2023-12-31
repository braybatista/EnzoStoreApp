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
        this.setPrenda(new Prenda());
    }

    public void crearPrenda() {
        try {
            if(prenda.getId() == 0){
                service.almacenar(prenda);
            } else{
                service.modificarPrenda(prenda);
            }
            mostrarListaPrendas();
            subirImagenPrenda();
            pasarARegistrodePrendas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    public void modificar() throws Exception {
//        try {
//            service.modificarPrenda(prenda);
//            obtenerListaPrendas();
//            subirImagenPrenda();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void mostrarListaPrendas() {
        this.prenda = new Prenda();
        obtenerListaPrendas();
    }

    public void obtenerListaPrendas() {
        listaprenda = service.mostrarListaPrenda();
    }

    public void validarPrenda(int id) throws Exception {
        Prenda temp;
        try {
            temp = service.validarPrendaService(id);

            if (temp != null) {
                this.prenda = temp;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(int id) {
        service.eliminarPrenda(id);
        this.prenda = new Prenda();
        mostrarListaPrendas();
    }

    public void subirImagenPrenda() {

        try {
            ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String path = sc.getRealPath("/image.png");
            path = path.replace("image.png", "Imagenes\\Prendas\\");
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
