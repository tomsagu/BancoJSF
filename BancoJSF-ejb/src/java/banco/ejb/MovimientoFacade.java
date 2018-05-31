/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.ejb;

import banco.entity.Movimiento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tomas
 */
@Stateless
public class MovimientoFacade extends AbstractFacade<Movimiento> {

    @PersistenceContext(unitName = "BancoJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoFacade() {
        super(Movimiento.class);
    }
    public List<Movimiento> buscarPorIdUsuario(Integer id) {
        Query q = this.em.createQuery("select m from Movimiento m where m.usuarioidUsuario.idUsuario = :i");
        q.setParameter("i", id);
        return q.getResultList();
    }
    
    public List<Movimiento> buscarMovimientoPorDni(String dni){
        Query q = this.em.createQuery("select m from Movimiento m where m.usuarioidUsuario.dni  = :id or m.entidad = :id order by m.fecha asc");
        q.setParameter("id", dni);
        
        List<Movimiento> lista = q.getResultList();
        
        return lista;
        
    }
    
    public List<Movimiento> buscarPorDNIFechaDescendente(String dni){
        Query q = this.em.createQuery("select m from Movimiento m where m.usuarioidUsuario.dni  = :id or m.entidad = :id order by m.fecha desc");
        q.setParameter("id", dni);
        
        List<Movimiento> lista = q.getResultList();
        
        return lista;
    }
     
    public List<Movimiento> buscarPorDNIFechaAscendente(String dni){
        Query q = this.em.createQuery("select m from Movimiento m where m.usuarioidUsuario.dni  = :id or m.entidad = :id order by m.fecha asc");
        q.setParameter("id", dni);
        
        List<Movimiento> lista = q.getResultList();
        
        return lista;
    }
    
    public List<Movimiento> buscarPorDNICantidadDescendente(String dni){
        Query q = this.em.createQuery("select m from Movimiento m where m.usuarioidUsuario.dni  = :id or m.entidad = :id order by m.cantidad desc");
        q.setParameter("id", dni);
        
        List<Movimiento> lista = q.getResultList();
        
        return lista;
    }
    
    public List<Movimiento> buscarPorDNICantidadAscendente(String dni){
        Query q = this.em.createQuery("select m from Movimiento m where m.usuarioidUsuario.dni  = :id or m.entidad = :id order by m.cantidad asc");
        q.setParameter("id", dni);
        
        List<Movimiento> lista = q.getResultList();
        
        return lista;
    }
    public List<Movimiento> buscarPorDNITipoDescendente(String dni){
        Query q = this.em.createQuery("select m from Movimiento m where m.usuarioidUsuario.dni  = :id or m.entidad = :id order by m.tipo desc");
        q.setParameter("id", dni);
        
        List<Movimiento> lista = q.getResultList();
        
        return lista;
    }
    
    public List<Movimiento> buscarPorDNITipoAscendente(String dni){
        Query q = this.em.createQuery("select m from Movimiento m where m.usuarioidUsuario.dni  = :id or m.entidad = :id order by m.tipo asc");
        q.setParameter("id", dni);
        
        List<Movimiento> lista = q.getResultList();
        
        return lista;
    }

   public List<Movimiento> buscarPorDNIEntidadDescendente(String dni){
        Query q = this.em.createQuery("select m from Movimiento m where m.usuarioidUsuario.dni  = :id or m.entidad = :id order by m.entidad desc");
        q.setParameter("id", dni);
        
        List<Movimiento> lista = q.getResultList();
        
        return lista;
    }
   
    public List<Movimiento> buscarPorDNIEntidadAscendente(String dni){
        Query q = this.em.createQuery("select m from Movimiento m where m.usuarioidUsuario.dni  = :id or m.entidad = :id order by m.entidad asc");
        q.setParameter("id", dni);
        
        List<Movimiento> lista = q.getResultList();
        
        return lista;
    }
    public List<Movimiento> buscarPorDNIEmpleadoDescendente(String dni){
        Query q = this.em.createQuery("select m from Movimiento m where m.usuarioidUsuario.dni  = :id or m.entidad = :id order by m.usuarioidUsuario1 desc");
        q.setParameter("id", dni);
        
        List<Movimiento> lista = q.getResultList();
        
        return lista;
    }
   
    public List<Movimiento> buscarPorDNIEmpleadoAscendente(String dni){
        Query q = this.em.createQuery("select m from Movimiento m where m.usuarioidUsuario.dni  = :id or m.entidad = :id order by m.usuarioidUsuario1 asc");
        q.setParameter("id", dni);
        
        List<Movimiento> lista = q.getResultList();
        
        return lista;
    }
}
