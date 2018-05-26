/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.bean;

import banco.ejb.MovimientoFacade;
import banco.entity.Movimiento;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author JorgeL
 */
@Named(value = "empleadoCrearMovimientoBean") 
@RequestScoped
public class EmpleadoCrearMovimientoBean {

    @EJB
    private MovimientoFacade movimientoFacade;
    
    @Inject
    private MovimientoBean movimientoBean;
    
     protected Movimiento movimiento;
    
    
    
    /**
     * Creates a new instance of empleadoCrearMovimientoBean
     */
    public EmpleadoCrearMovimientoBean() {
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }
    
    public String doGuardar() {

        if (movimiento.getIdMovimiento() == null) {
            this.movimientoFacade.create(movimiento);
        } else {
            this.movimientoFacade.edit(movimiento);            
        }
        
        this.movimientoBean.init();
        
        return "empleado_Movimiento";
    }
    
    
     @PostConstruct
    public void init () {
        if (this.movimientoBean.getIdMovimientoSeleccionado() != -1) { // Editar
            this.movimiento = this.movimientoFacade.find(this.movimientoBean.getIdMovimientoSeleccionado());
            this.movimientoBean.setIdCustomerSeleccionado(-1);
        } else { // Nuevo cliente
            movimiento = new Movimiento();
        }
    }
}
