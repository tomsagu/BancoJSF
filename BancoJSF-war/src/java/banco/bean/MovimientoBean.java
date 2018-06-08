/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bean;

import banco.ejb.MovimientoFacade;
import banco.entity.Movimiento;
import banco.entity.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author beaco
 */
@Named(value = "movimientoBean")
@RequestScoped
public class MovimientoBean {
    
    @Inject
    private EmpleadoBean empleadoBean;

    @EJB
    private MovimientoFacade movimientoFacade;
    
    private String ordenActual;
    private Map<String, List<Movimiento>> movimientosMap  = new HashMap<>();
    private List<String> opcionesOrden;
    protected Usuario usuarioSeleccionado;
    private String criterioActualBusqueda;
    private String stringBusqueda;

    public String getCriterioActualBusqueda() {
        return criterioActualBusqueda;
    }

    public void setCriterioActualBusqueda(String criterioActualBusqueda) {
        this.criterioActualBusqueda = criterioActualBusqueda;
    }

    public String getStringBusqueda() {
        return stringBusqueda;
    }

    public void setStringBusqueda(String stringBusqueda) {
        this.stringBusqueda = stringBusqueda;
    }

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
     
    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    /**
     * Creates a new instance of MovimientoBean
     */
    public MovimientoBean() {
        opcionesOrden = new ArrayList<>();
        opcionesOrden.add("Fecha descendente");
        opcionesOrden.add("Fecha ascendente");
        opcionesOrden.add("Cantidad descendente");
        opcionesOrden.add("Cantidad ascendente");
        opcionesOrden.add("Tipo descendente");
        opcionesOrden.add("Tipo ascendente");
        opcionesOrden.add("Entidad descendente");
        opcionesOrden.add("Entidad ascendente");
        opcionesOrden.add("Empleado descendente");
        opcionesOrden.add("Empleado ascendente");
        ordenActual = "Fecha descendente";
    }
    @PostConstruct
    public void init(){
        usuarioSeleccionado = this.empleadoBean.getUsuarioSeleccionado();
        stringBusqueda = "";
        criterioActualBusqueda = "";
        this.movimientosMap.put("Fecha descendente", this.movimientoFacade.buscarPorDNIFechaDescendente(usuarioSeleccionado.getDni()));
        this.movimientosMap.put("Fecha ascendente", this.movimientoFacade.buscarPorDNIFechaAscendente(usuarioSeleccionado.getDni()));
        this.movimientosMap.put("Cantidad descendente", this.movimientoFacade.buscarPorDNICantidadDescendente(usuarioSeleccionado.getDni()));
        this.movimientosMap.put("Cantidad ascendente", this.movimientoFacade.buscarPorDNICantidadAscendente(usuarioSeleccionado.getDni()));
        this.movimientosMap.put("Tipo descendente", this.movimientoFacade.buscarPorDNITipoDescendente(usuarioSeleccionado.getDni()));
        this.movimientosMap.put("Tipo ascendente", this.movimientoFacade.buscarPorDNITipoAscendente(usuarioSeleccionado.getDni()));
        this.movimientosMap.put("Entidad descendente", this.movimientoFacade.buscarPorDNIEntidadDescendente(usuarioSeleccionado.getDni()));
        this.movimientosMap.put("Entidad ascendente", this.movimientoFacade.buscarPorDNIEntidadAscendente(usuarioSeleccionado.getDni()));
        this.movimientosMap.put("Empleado descendente", this.movimientoFacade.buscarPorDNIEmpleadoDescendente(usuarioSeleccionado.getDni()));
        this.movimientosMap.put("Empleado ascendente", this.movimientoFacade.buscarPorDNIEmpleadoAscendente(usuarioSeleccionado.getDni()));
    }
    
    public String doBuscar(){
        if(this.criterioActualBusqueda.equals("1")){
           this.movimientosMap.put("Concepto", this.movimientoFacade.buscarPorConcepto(stringBusqueda,this.usuarioSeleccionado.getDni()));
           this.ordenActual = "Concepto";
        }else if(this.criterioActualBusqueda.equals("2")){
           this.movimientosMap.put("Tipo", this.movimientoFacade.buscarPorTipo(stringBusqueda,this.usuarioSeleccionado.getDni()));
           this.ordenActual = "Tipo";
        }
        return "empleado_Movimiento";
    }
}
