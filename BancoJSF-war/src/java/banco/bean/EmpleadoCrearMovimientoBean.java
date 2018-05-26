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
    private UsuarioFacade usuarioFacade;

    @EJB
    private MovimientoFacade movimientoFacade;
    
    @Inject
    private MovimientoBean movimientoBean;
    
     protected Movimiento movimiento;
     protected String cantidad;
    
    
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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
    
    public String doGuardar() {
        movimiento.setCantidad(Double.parseDouble(cantidad));
        
        
        
        if (movimiento.getIdMovimiento() == null) {
            this.movimientoFacade.create(movimiento);
        } else {
            this.movimientoFacade.edit(movimiento);            
        }
        
        if(movimiento.getTipo().equals("ingreso")){
                Usuario x = this.usuarioFacade.find(movimiento.getUsuarioidUsuario());
                x.setSaldo(x.getSaldo()+ movimiento.getCantidad());
                this.usuarioFacade.edit(x);
            }else if(movimiento.getTipo().equals("debito")){
               Usuario x = this.usuarioFacade.find(movimiento.getUsuarioidUsuario());
                x.setSaldo(x.getSaldo()- movimiento.getCantidad());
                this.usuarioFacade.edit(x); 
            }else{
                Usuario x = this.usuarioFacade.find(movimiento.getUsuarioidUsuario());
                x.setSaldo(x.getSaldo()-movimiento.getCantidad());
                this.usuarioFacade.edit(x);
                Usuario y = this.usuarioFacade.findByDni(movimiento.getEntidad());
                y.setSaldo(y.getSaldo()+movimiento.getCantidad());
                this.usuarioFacade.edit(y);
            }
        
        this.movimientoBean.init();
        
        return "empleado_Movimiento";
    }
    
    
     @PostConstruct
    public void init () {
        if (this.movimientoBean.getIdMovimientoSeleccionado() != -1) { // Editar
            this.movimiento = this.movimientoFacade.find(this.empleadoBean.getIdMovimientoSeleccionado());
            this.movimientoBean.setIdMovimientoSeleccionado(-1);
        } else { // Nuevo cliente
            movimiento = new Movimiento();
        }
    }
}
