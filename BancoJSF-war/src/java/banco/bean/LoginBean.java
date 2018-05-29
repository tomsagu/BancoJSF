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
    private boolean levelEditable;
    private boolean levelEditable1;
    private boolean levelEditable2;
    private String message = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isLevelEditable1() {
        return levelEditable1;
    }

    public void setLevelEditable1(boolean levelEditable1) {
        this.levelEditable1 = levelEditable1;
    }

    public boolean isLevelEditable2() {
        return levelEditable2;
    }

    public void setLevelEditable2(boolean levelEditable2) {
        this.levelEditable2 = levelEditable2;
    }

    public boolean isLevelEditable() {
        return levelEditable;
    }

    public void setLevelEditable(boolean levelEditable) {
        this.levelEditable = levelEditable;
    }

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
            message="Usuario o contraseña incorrecta";
            return (null);
        }
        
        if(usuario.getEmpleado() == 1){
            return "empleado_Usuario";
        }else{
            return "usuario_DatosPersonales";
            
        }
    }
    
    public String borrarSesion(){
        usuario = new Usuario();
        levelEditable = false;
        message="";
        return "login";
    }
    
    public String doEdit(){
        levelEditable = false;
        levelEditable1 = false;
        levelEditable2 = false;
        this.usuarioFacade.edit(this.usuario);
        return null;
    }
    
}
