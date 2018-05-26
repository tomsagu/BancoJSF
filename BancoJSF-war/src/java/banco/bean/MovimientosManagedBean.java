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
import javax.enterprise.context.Dependent;

/**
 *
 * @author Alexander
 */
@Named(value = "movimientosManagedBean")
@Dependent
public class MovimientosManagedBean implements Serializable {

    /**
     * Creates a new instance of MovimientosManagedBean
     */
    public MovimientosManagedBean() {
    }
    
    @PostConstruct
    public void init(){
    }
    
}
