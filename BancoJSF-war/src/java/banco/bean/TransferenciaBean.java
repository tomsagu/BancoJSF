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
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Alexander
 */
@Named(value = "transferenciaBean")
@RequestScoped
public class TransferenciaBean {
    @Inject
    private LoginBean login;  
    @EJB
    private MovimientoFacade movimientoFacade;

    @EJB
    private UsuarioFacade usuarioFacade;
    
    // variables
    Usuario usuario;
    Movimiento movimiento;
    String cantidad;
    double cantidadDouble;
    private String message = "";

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    /**
     * Creates a new instance of TransferenciaBean
     */
    public TransferenciaBean() {
    }
    
    public String doTransferencia() {
        usuario = login.getUsuario();
        cantidadDouble = Double.parseDouble(cantidad);
        Usuario busqueda = this.usuarioFacade.findByDni(movimiento.getEntidad());
        Double saldoUsuario = usuario.getSaldo();
       
        if((busqueda == null || busqueda.equals(usuario)) && (cantidadDouble > saldoUsuario) || (cantidadDouble < 0)){ // mirar si existe el beneficiario
            message = "Usuario y cantidad incorrecto";
            return(null);
        } else if (busqueda.equals(usuario)) {
            message = "No se puede mandar al mismo usuario";
            return(null);
        } else if(busqueda == null) {
            message = "Usuario desconocido";
            return(null);
        } else if ((cantidadDouble > saldoUsuario) || (cantidadDouble < 0)) {
            message = "Cantidad incorrecto";
            return(null);
        } else {
            String entidad = movimiento.getEntidad();
            Usuario ben = this.usuarioFacade.findByDni(entidad);
            //rellenar todos los campos de movimiento
            movimiento.setCantidad(cantidadDouble); //cantidad
            movimiento.setUsuarioidUsuario(usuario); //usuario
            movimiento.setUsuarioidUsuario1(usuario); //supervisor
            movimiento.setFecha(new Date()); //fecha
            movimiento.setTipo("transferencia"); //tipo

            //crear movimiento
            this.movimientoFacade.create(movimiento);

            //modificar slado usuarios
            usuario.setSaldo(usuario.getSaldo() - cantidadDouble);
            ben.setSaldo(ben.getSaldo() + cantidadDouble);

            //editar usuarios
            this.usuarioFacade.edit(usuario);
            this.usuarioFacade.edit(ben);
            return "usuario_Movimientos";
        }
    }
    
    @PostConstruct
    public void init (){
        movimiento = new Movimiento();
    }
    
}

