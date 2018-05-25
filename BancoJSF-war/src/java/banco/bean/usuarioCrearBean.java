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
    private UsuarioBean usuarioBean; //Bean que controla el listado de usuario
    
    @EJB
    private UsuarioFacade usuarioFacade;

    protected Usuario usuario;
    
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

    public String doGuardar() {
        if (usuario.getIdUsuario() == null) {
            this.usuarioFacade.create(usuario);
        } else {
            this.usuarioFacade.edit(usuario);
        }
        
        this.usuarioBean.init();
        
        return "index";
    }
    
    @PostConstruct
    public void init() {
       if (this.usuarioBean.getIdUsuarioSeleccionado() != -1) {
           this.usuario = this.usuarioFacade.find(this.usuarioBean.getIdUsuarioSeleccionado());
           this.usuarioBean.setIdUsuarioSeleccionado = -1;
       } else {
           usuario = new Usuario();
       }
    }
}