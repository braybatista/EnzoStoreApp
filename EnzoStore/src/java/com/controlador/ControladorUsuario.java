package com.controlador;

import com.modelo.Usuario;
import com.service.UsuarioService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ControladorUsuario {

    private Usuario usuario = new Usuario();
    private Usuario usuarioInicioSesion = new Usuario();
    private UsuarioService service = new UsuarioService();

    @ManagedProperty("#{controladorPrenda}")
    private ControladorPrenda conprendas = new ControladorPrenda();

    public ControladorUsuario() {
        usuario = new Usuario();
    }

    public void registrarUsuario() {
        service.registroUsuario(usuario);
        cerrar();
    }

    public void iniciosesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        usuario = service.consultarUsuario(usuario.getCorreo(), usuario.getContrasenia());
        context.getExternalContext().getSessionMap().put("user", usuario);
        try {
            switch (usuario.getTipoContacto()) {
                case "Administrador":
                    externalContext.redirect("CatalogoVirtual.xhtml");
                    conprendas.mostrarListaPrendas();

                case "Cliente":
                    externalContext.redirect("CatalogoCliente.xhtml");
                    conprendas.mostrarListaPrendas();

                default:
                    System.out.println("El usuario no existe");
                    navegarAPaginaRegistro();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public ControladorPrenda getConprendas() {
        return conprendas;
    }

    public void setConprendas(ControladorPrenda conprendas) {
        this.conprendas = conprendas;
    }

    public void navegarAPaginaRegistro() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.redirect("RegistroDeUsuario.xhtml");
    }
    
    public void navegarACatalogoAdmin() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.redirect("CatalogoVirtual.xhtml");
    }

    public String navegarAPaginaInicioDeSesion() {
        return "InicioDeSesion?faces-redirect=true";
    }

    public void cerrar() {
        try {
            usuario = new Usuario();
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.getSessionMap().clear();
            externalContext.redirect("InicioDeSesion.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario getUsuarioInicioSesion() {
        return usuarioInicioSesion;
    }

    public void setUsuarioInicioSesion(Usuario usuarioInicioSesion) {
        this.usuarioInicioSesion = usuarioInicioSesion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
