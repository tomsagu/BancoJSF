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
public class UsuarioCrearBean {

    @Inject 
    private EmpleadoBean empleadoBean;
    
    @EJB
    private UsuarioFacade usuarioFacade;

    protected Usuario usuario;
    protected Boolean alta;
    
    /**
     * Creates a new instance of usuarioCrearBean
     */
    public UsuarioCrearBean() {
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
    
    @PostConstruct
    public void init() {
       if (this.empleadoBean.getUsuarioSeleccionado() != null) { //Editar
           this.usuario = this.empleadoBean.getUsuarioSeleccionado();
           if(this.usuario.getEstado() == 1){
               this.alta = true;
           }else{
               this.alta = false;
           }
           
       } else { //Crear
           usuario = new Usuario();
       }
    }
    
    public String doGuardar() {
        short estado = (short)(alta?1:0);
        usuario.setEstado(estado);
        usuario.setEmpleado((short)0);
        
        if (usuario.getIdUsuario() != null) {
            this.usuarioFacade.edit(usuario);
        } else {  
            this.usuarioFacade.create(usuario);
        }
        this.empleadoBean.setUsuarioSeleccionado(null);
        this.empleadoBean.init();
        
        return "empleado_Usuario";
    }
    
    public String doCancelar(){
        this.empleadoBean.setUsuarioSeleccionado(null);
        this.empleadoBean.init();
        
        return "empleado_Usuario";
    }
    

}
