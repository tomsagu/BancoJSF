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
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Alexander
 */
@Named(value = "movimientosManagedBean")
@RequestScoped
public class MovimientosManagedBean implements Serializable {
    
    @EJB
    private MovimientoFacade movimientoFacade;

    @EJB
    private UsuarioFacade usuarioFacade;
    
    @Inject
    private LoginBean login;
    
    List<Movimiento> listaMovimientos;

    public List<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setListaMovimientos(List<Movimiento> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }
    
    
    
    /**
     * Creates a new instance of MovimientosManagedBean
     */
    public MovimientosManagedBean() {
    }
    
    @PostConstruct
    public void init(){
        listaMovimientos = this.movimientoFacade.BuscarMovimientoPorDni(login.usuario.getDni());
    }
    
}
