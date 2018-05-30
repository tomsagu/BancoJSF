/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bean;

import banco.ejb.UsuarioFacade;
import banco.entity.Usuario;
import java.util.List;
import java.util.Random;
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
    protected String contrasenaConf;
    protected String message = "";
    
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
    
    public String getContrasenaConf() {
        return contrasenaConf;
    }

    public void setContrasenaConf(String contrasenaConf) {
        this.contrasenaConf = contrasenaConf;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
           contrasenaConf = this.usuario.getContrasena();
       } else { //Crear
           usuario = new Usuario();
       }
    }
    
    public String doGuardar() {
        if (usuario.getContrasena().equals(contrasenaConf)) {
            short estado = (short)(alta?1:0);
            usuario.setEstado(estado);
            usuario.setEmpleado((short)0);

            if (usuario.getIdUsuario() != null) {
                this.usuarioFacade.edit(usuario);
            } else {  
                usuario.setSaldo(0.0);
                
                // Generacion de numero de cuenta aleatorio de 8 digitos
                Random rnd = new Random();
                boolean valido = false;
                List<Integer> cuentas = this.usuarioFacade.findAllCuentas();
                int numero;
                while(!valido) {
                    numero = 10000000 + rnd.nextInt(90000000);
                    if (!cuentas.contains(numero)) {
                        valido = true;
                        usuario.setCuenta(numero);
                    }
                }
                
                this.usuarioFacade.create(usuario);
            }
            this.empleadoBean.setUsuarioSeleccionado(null);
            this.empleadoBean.init();

            return "empleado_Usuario";
        } else {
            message = "Las contraseñas no coinciden";
            return (null);
        }
    }
    
    public String doCancelar(){
        this.empleadoBean.setUsuarioSeleccionado(null);
        this.empleadoBean.init();
        
        return "empleado_Usuario";
    }
}