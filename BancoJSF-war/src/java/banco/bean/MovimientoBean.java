/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bean;

import banco.ejb.MovimientoFacade;
import banco.entity.Movimiento;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author JorgeL
 */
@Named(value = "movimientoBean")
@RequestScoped
public class MovimientoBean {

    @EJB
    private MovimientoFacade movimientoFacade;
    
    private Integer idMovimientoSeleccionado = -1;
    private List<Movimiento> listaMovimientos;
    
    public Integer getIdMovimientoSeleccionado() {
        return idMovimientoSeleccionado;
    }

    public void setIdCustomerSeleccionado(Integer idMovimientoSeleccionado) {
        this.idMovimientoSeleccionado = idMovimientoSeleccionado;
    }
  
    /**
     * Creates a new instance of movimientoBean
     */
    public MovimientoBean() {
    }
    
      @PostConstruct
    public void init () {
        this.listaMovimientos = this.movimientoFacade.findAll();
    }    
}
