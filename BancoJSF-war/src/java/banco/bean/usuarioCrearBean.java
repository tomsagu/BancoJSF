/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bean;

import banco.ejb.UsuarioFacade;
import banco.entity.Usuario;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author alex
 */

@Named(value = "usuarioCrearBean")
@RequestScoped
public class usuarioCrearBean {

    @Inject 
    private EmpleadoBean empleadoBean;
    
    @EJB
    private UsuarioFacade usuarioFacade;

    protected Usuario usuario;
    protected Boolean alta;
    
    /**
     * Creates a new instance of usuarioCrearBean
     */
    public usuarioCrearBean() {
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    
    public String doGuardar() {
        short estado = (short)(alta?1:0);
        usuario.setEstado(estado);
        
        if (usuario.getIdUsuario() == null) {
            this.usuarioFacade.create(usuario);
        } else {
            this.usuarioFacade.edit(usuario);
        }
        
        this.empleadoBean.init();
        
        return "empleado_Usuario";
    }
    
    @PostConstruct
    public void init() {
       if (this.empleadoBean.getUsuarioSeleccionado() != null) {
           this.usuario = this.empleadoBean.getUsuarioSeleccionado();
           this.empleadoBean.setUsuarioSeleccionado(null);
       } else {
           usuario = new Usuario();
       }
    }
}