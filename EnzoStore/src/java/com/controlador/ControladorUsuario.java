package com.controlador;


import com.modelo.Usuario;
import com.service.UsuarioService;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private PreparedStatement ps;
    private ResultSet rs;
    
     @ManagedProperty("#{controladorPrenda}")
    private ControladorPrenda conprendas = new ControladorPrenda();

//    @ManagedProperty("#{controladorAdministrador}")
//    private ControladorAdministrador adcon = new ControladorAdministrador();
//    
//    
//    @ManagedProperty("#{controladorCliente}")
//    private ControladorCliente clicon = new ControladorCliente();

    
    public ControladorUsuario() {
        usuario = new Usuario();
    }

    public void registrarUsuario() {
        service.registroUsuario(usuario);

    }

    public void iniciosesion() {
        usuario = service.consultarUsuario(usuario.getCorreo(), usuario.getContraseña());

        try {

            if (!usuario.getId().equals("")) {
                        FacesContext context = FacesContext.getCurrentInstance();
                        ExternalContext externalContext = context.getExternalContext();
                        
                switch (usuario.getTipo_contacto()) {
                    case "Administrador":
                        externalContext.redirect("CatalogoVirtual.xhtml");
                        
                        conprendas.mostrarListaPrendas();
                        
                    case "Cliente":
                        externalContext.redirect("CatalogoCliente.xhtml");
                        
        conprendas.mostrarListaPrendas();   

                    default:
                        System.out.println("El usuario no existe");
                }

                
            } else {
                System.out.println("Usuario nulo");

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

    public String navegarAPaginaInicioDeSesion() {
        return "InicioDeSesion?faces-redirect=true";
    }
    
    public void cerrar() throws IOException{
        usuario = new Usuario();
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.redirect("InicioDeSesion.xhtml");

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
