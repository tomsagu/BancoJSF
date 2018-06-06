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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Named(value = "usuarioBean")
@RequestScoped
public class UsuarioBean {
    @Inject
    private LoginBean loginBean;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private MovimientoFacade movimientoFacade;
    
    private String ordenActualUsuario;
    private String criterioActualBusqueda;
    private String busquedaMovimiento;
    private String stringBusquedaMovimiento;
    private String stringBusqueda;
    private Map<String, List<Usuario>> usuariosMap  = new HashMap<>();
    private List<String> opcionesOrdenUsuario;
    protected Usuario usuario;

    public String getBusquedaMovimiento() {
        return busquedaMovimiento;
    }

    public void setBusquedaMovimiento(String busquedaMovimiento) {
        this.busquedaMovimiento = busquedaMovimiento;
    }

    public String getStringBusquedaMovimiento() {
        return stringBusquedaMovimiento;
    }

    public void setStringBusquedaMovimiento(String stringBusquedaMovimiento) {
        this.stringBusquedaMovimiento = stringBusquedaMovimiento;
    }

    public String getStringBusqueda() {
        return stringBusqueda;
    }

    public void setStringBusqueda(String stringBusqueda) {
        this.stringBusqueda = stringBusqueda;
    }

    public String getCriterioActualBusqueda() {
        return criterioActualBusqueda;
    }

    public void setCriterioActualBusqueda(String criterioActualBusqueda) {
        this.criterioActualBusqueda = criterioActualBusqueda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Map<String, List<Usuario>> getUsuariosMap() {
        return usuariosMap;
    }

    public void setUsuariosMap(Map<String, List<Usuario>> usuariosMap) {
        this.usuariosMap = usuariosMap;
    }

    public String getOrdenActualUsuario() {
        return ordenActualUsuario;
    }

    public void setOrdenActualUsuario(String ordenActualUsuario) {
        this.ordenActualUsuario = ordenActualUsuario;
    }

    public List<String> getOpcionesOrdenUsuario() {
        return opcionesOrdenUsuario;
    }

    public void setOpcionesOrdenUsuario(List<String> opcionesOrdenUsuario) {
        this.opcionesOrdenUsuario = opcionesOrdenUsuario;
    }
    
    public List<Usuario> getListaUsuariosOrden() {
        return (usuariosMap.get(ordenActualUsuario));
    }

    private String ordenActual;
    private Map<String, List<Movimiento>> movimientosMap  = new HashMap<>();;
    private List<String> opcionesOrden;
    

    public Map<String, List<Movimiento>> getMovimientosMap() {
        return movimientosMap;
    }

    public void setMovimientosMap(Map<String, List<Movimiento>> movimientosMap) {
        this.movimientosMap = movimientosMap;
    }

    public String getOrdenActual() {
        return ordenActual;
    }

    public void setOrdenActual(String ordenActual) {
        this.ordenActual = ordenActual;
    }

    public List<String> getOpcionesOrden() {
        return opcionesOrden;
    }

    public void setOpcionesOrden(List<String> opcionesOrden) {
        this.opcionesOrden = opcionesOrden;
    }

    public List<Movimiento> getListaMovimientosOrden() {
        return (movimientosMap.get(ordenActual));
    }
    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
        opcionesOrdenUsuario = new ArrayList<>();
        opcionesOrdenUsuario.add("DNI descendente");
        opcionesOrdenUsuario.add("DNI ascendente");
        opcionesOrdenUsuario.add("Número de cuenta descendente");
        opcionesOrdenUsuario.add("Número de cuenta ascendente");
        opcionesOrdenUsuario.add("Nombre descendente");
        opcionesOrdenUsuario.add("Nombre ascendente");
        opcionesOrdenUsuario.add("Apellidos descendente");
        opcionesOrdenUsuario.add("Apellidos ascendente");
        ordenActualUsuario = "DNI descendente";
        
        opcionesOrden = new ArrayList<>();
        opcionesOrden.add("Fecha descendente");
        opcionesOrden.add("Fecha ascendente");
        opcionesOrden.add("Cantidad descendente");
        opcionesOrden.add("Cantidad ascendente");
        ordenActual = "Fecha descendente";
    }
    
    @PostConstruct
    public void init(){
        usuario = this.loginBean.getUsuario();
        stringBusqueda = "";
        criterioActualBusqueda = "";
        stringBusquedaMovimiento = "";
        busquedaMovimiento = "";
        this.usuariosMap.put("DNI descendente", this.usuarioFacade.buscarPorDNIDescendente(0));
        this.usuariosMap.put("DNI ascendente", this.usuarioFacade.buscarPorDNIAscendente(0));
        this.usuariosMap.put("Número de cuenta descendente", this.usuarioFacade.buscarPorCuentaDescendente(0));
        this.usuariosMap.put("Número de cuenta ascendente", this.usuarioFacade.buscarPorCuentaAscendente(0));
        this.usuariosMap.put("Nombre descendente", this.usuarioFacade.buscarPorNombreDescendente(0));
        this.usuariosMap.put("Nombre ascendente", this.usuarioFacade.buscarPorNombreAscendente(0));
        this.usuariosMap.put("Apellidos descendente", this.usuarioFacade.buscarPorApellidosDescendente(0));
        this.usuariosMap.put("Apellidos ascendente", this.usuarioFacade.buscarPorApellidosAscendente(0));
       
        this.movimientosMap.put("Fecha descendente", this.movimientoFacade.buscarPorDNIFechaDescendente(usuario.getDni()));
        this.movimientosMap.put("Fecha ascendente", this.movimientoFacade.buscarPorDNIFechaAscendente(usuario.getDni()));
        this.movimientosMap.put("Cantidad descendente", this.movimientoFacade.buscarPorDNICantidadDescendente(usuario.getDni()));
        this.movimientosMap.put("Cantidad ascendente", this.movimientoFacade.buscarPorDNICantidadAscendente(usuario.getDni()));

    }
    
    public void doBuscar(){
        if(this.criterioActualBusqueda.equals("1")){
           this.usuariosMap.put("Nombre", this.usuarioFacade.buscarPorNombre(0, stringBusqueda));
           this.ordenActualUsuario = "Nombre";
        }else if(this.criterioActualBusqueda.equals("2")){
           this.usuariosMap.put("DNI", this.usuarioFacade.buscarPorDni(0, stringBusqueda));
           this.ordenActualUsuario = "DNI";
        }
    }
    
    public void doBuscarMovimiento(){
        if(this.busquedaMovimiento.equals("1")){
           this.movimientosMap.put("Concepto", this.movimientoFacade.buscarPorConcepto(stringBusquedaMovimiento, this.usuario.getDni()));
           this.ordenActual = "Concepto";
        }else if(this.busquedaMovimiento.equals("2")){
           this.movimientosMap.put("Tipo", this.movimientoFacade.buscarPorTipo(stringBusquedaMovimiento, this.usuario.getDni()));
           this.ordenActual = "Tipo";
        }
    }
}
