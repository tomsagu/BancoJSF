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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author beaco
 */
@Named(value = "empleadoBean")
@RequestScoped
public class EmpleadoBean {

    @Inject
    private LoginBean loginBean;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private MovimientoFacade movimientoFacade;

    protected Usuario usuario;
    protected List<Usuario> listaUsuarios;
    protected List<Movimiento> listaMovimientos;
    private Usuario usuarioElegido;
    private Movimiento movimientoSeleccionado;

   
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

    public Usuario getUsuarioElegido() {
        return usuarioElegido;
    }

    public void setUsuarioElegido(Usuario usuarioElegido) {
        this.usuarioElegido = usuarioElegido;
    }

    public Movimiento getMovimientoSeleccionado() {
        return movimientoSeleccionado;
    }

    public void setMovimientoSeleccionado(Movimiento movimientoSeleccionado) {
        this.movimientoSeleccionado = movimientoSeleccionado;
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
    }
    public String doEditar(Integer id){
        if (id != null) { // Caso de uso editar
            usuarioElegido = this.usuarioFacade.find(id);
        }
        //Comprobar nombre
        return "nuevoEditarUsuario";
    }
    public String doMovimientos(Integer id){
        if (id != null) { // Caso de uso editar
            usuarioElegido = this.usuarioFacade.find(id);
        }
        listaMovimientos = this.movimientoFacade.buscarPorIdUsuario(id);
        return "empleado_Movimiento";
    }
    
    public String doEditarMovimiento(Integer idUsuario, Integer idMovimiento){
        //Comprobar el return
        if (idUsuario != null) { // Caso de uso editar
            usuarioElegido = this.usuarioFacade.find(idUsuario);
        }
        movimientoSeleccionado = this.movimientoFacade.find(idMovimiento);
        return "empleado_nuevoEditarMovimiento";
    }
    
}
