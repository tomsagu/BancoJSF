/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bean;

import banco.ejb.UsuarioFacade;
import banco.entity.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Tomas
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{

    @EJB
    private UsuarioFacade usuarioFacade;
    
    protected Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Creates a new instance of loginBean
     */
    public LoginBean() {

    }
    
    @PostConstruct
    public void init(){
       usuario = new Usuario();
    }
    
    public String doLogin(){
                
        Usuario logueado = this.usuarioFacade.buscarPorDniYPassword(usuario.getDni(), usuario.getContrasena());
        
        if(logueado != null){
            usuario = logueado;
        }else{
            //Lanzar error
        }
        
        if(usuario.getEmpleado() == 1){
            return "empleado_Usuario";
        }else{
            return "usuario_DatosPersonales";
        }
    }
    
    public String borrarSesion(){
        usuario = new Usuario();
        return "login";
    }
    
}
