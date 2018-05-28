/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bean;

import banco.ejb.MovimientoFacade;
import banco.ejb.UsuarioFacade;
import banco.entity.Movimiento;
import banco.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author beaco
 */
@Named(value = "empleadoBean")
@SessionScoped
public class EmpleadoBean implements Serializable{

    @Inject
    private LoginBean loginBean;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private MovimientoFacade movimientoFacade;

    protected Usuario usuario;
    protected List<Usuario> listaUsuarios;
    protected List<Movimiento> listaMovimientos;
    private Usuario usuarioSeleccionado = null;
    private Integer idMovimientoSeleccionado = -1;

   
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
     public List<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setListaMovimientos(List<Movimiento> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public Integer getIdMovimientoSeleccionado() {
        return idMovimientoSeleccionado;
    }

    public void setIdMovimientoSeleccionado(Integer idMovimientoSeleccionado) {
        this.idMovimientoSeleccionado = idMovimientoSeleccionado;
    }
    
    
    /**
     * Creates a new instance of EmpleadoBean
     */
    public EmpleadoBean() {
    }
    
    @PostConstruct
    public void init(){
       usuario = this.loginBean.getUsuario();
       //Crea la lista solo con los usuarios (clientes del banco), no empleados
       this.listaUsuarios = this.usuarioFacade.buscarUsuarios(0);
       //this.usuarioSeleccionado = null;  
    }
    public String doEditar(Integer id){
        if (id != null) { // Caso de uso editar
            usuarioSeleccionado = this.usuarioFacade.find(id);
        }
        return "empleado_nuevoEditarUsuario";
    }
    public String doMovimientos(Integer id){
        if (id != null) { // Caso de uso editar
            usuarioSeleccionado = this.usuarioFacade.find(id);
        }
        listaMovimientos = this.movimientoFacade.buscarPorIdUsuario(id);
        return "empleado_Movimiento";
    }
    
    public String doEditarMovimiento(Integer idMovimiento){
        idMovimientoSeleccionado = idMovimiento;
        return "empleado_nuevoeditarMovimiento";
    }
    
    public String doUsuarios(){
        this.usuarioSeleccionado = null;  
        return "empleado_Usuario";
    }
    
}
