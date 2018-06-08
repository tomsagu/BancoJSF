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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private EmpleadoBean empleadoBean;
    @Inject
    private LoginBean loginBean;
    
   protected Movimiento movimiento;
   protected String fecha;
   private String message;
   private String saldocliente;
   
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSaldocliente() {
        Usuario x = movimiento.getUsuarioidUsuario();
        return String.valueOf(x.getSaldo());
    }
 
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

    
    @PostConstruct
    public void init () {
        if (this.empleadoBean.getIdMovimientoSeleccionado() != -1) { // Editar
            this.movimiento = this.movimientoFacade.find(this.empleadoBean.getIdMovimientoSeleccionado());
            
             Date date= this.movimiento.getFecha();
            
             LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
             int year  = localDate.getYear();
             int month = localDate.getMonthValue();
             int day   = localDate.getDayOfMonth();
            
             this.fecha = year+"-"+month+"-"+day;
            
        } else { // Nuevo Movimiento
            movimiento = new Movimiento();
            movimiento.setUsuarioidUsuario(empleadoBean.getUsuarioSeleccionado());
            movimiento.setUsuarioidUsuario1(loginBean.getUsuario());
        }
    }
    
    public String doGuardar() {
          Usuario xy = movimiento.getUsuarioidUsuario();
          double restante=0;
          if(movimiento.getTipo().equals("debito") || movimiento.getTipo().equals("transferencia")){
                 restante=xy.getSaldo()- movimiento.getCantidad(); 
          }
       
            if(restante>=0){
            if (movimiento.getIdMovimiento() == null) {
            
            String fechaAMD = fecha.substring(0, 10);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse(fechaAMD);
            } catch (ParseException ex) {
               // Logger.getLogger(Empleado_CrearMovimientoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.movimiento.setFecha(date);
            this.movimientoFacade.create(movimiento);
        } else {
            this.movimientoFacade.edit(movimiento);            
        }
        
            if(movimiento.getTipo().equals("ingreso")){
                Usuario x = movimiento.getUsuarioidUsuario();
                x.setSaldo(x.getSaldo()+ movimiento.getCantidad());
                this.usuarioFacade.edit(x);
            }else if(movimiento.getTipo().equals("debito")){
               Usuario x = movimiento.getUsuarioidUsuario();
                x.setSaldo(x.getSaldo()- movimiento.getCantidad());
                this.usuarioFacade.edit(x); 
            }else{
                Usuario x = movimiento.getUsuarioidUsuario();
                x.setSaldo(x.getSaldo()-movimiento.getCantidad());
                this.usuarioFacade.edit(x);
                Usuario y = this.usuarioFacade.findByDni(movimiento.getEntidad());
                y.setSaldo(y.getSaldo()+movimiento.getCantidad());
                this.usuarioFacade.edit(y);
            }
            
        this.empleadoBean.setIdMovimientoSeleccionado(-1);
        this.empleadoBean.setListaMovimientos(this.movimientoFacade.buscarPorIdUsuario(this.empleadoBean.getUsuarioSeleccionado().getIdUsuario()));
        Usuario seleccionado = this.empleadoBean.getUsuarioSeleccionado();
        this.empleadoBean.init();
        this.empleadoBean.setUsuarioSeleccionado(seleccionado);
        FacesMessage msg;
        msg = new FacesMessage("Movimiento Insertado con exito");
        FacesContext.getCurrentInstance().addMessage(null,msg);
        
        return "empleado_Movimiento";
       }else{
           this.message="No se ha podido realizar el movimiento tu saldo restante hubiera sido: " + restante;
           return "";
       }
    }
    
    public String doCancelar(){
        Usuario seleccionado = this.empleadoBean.getUsuarioSeleccionado();
        this.empleadoBean.init();
        this.empleadoBean.setIdMovimientoSeleccionado(-1);
        this.empleadoBean.setUsuarioSeleccionado(seleccionado);
        return "empleado_Movimiento";
    }
   
}
