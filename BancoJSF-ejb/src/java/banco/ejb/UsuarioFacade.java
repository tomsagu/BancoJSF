/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.ejb;

import banco.entity.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "BancoJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario buscarPorDniYPassword(String dni, String password) {
        Query q = this.em.createQuery("select u from Usuario u where u.dni = :d and u.contrasena = :p");
        q.setParameter("d", dni);
        q.setParameter("p", password);

        List<Usuario> lista = q.getResultList();
        if (lista == null || lista.size() == 0) {
            return null;
        } else {
            return lista.get(0);
        }
    }
    
    public Usuario findByDni(String dni) {
        Query q = this.em.createNamedQuery("Usuario.findByDni");
        q.setParameter("dni",dni);
        List<Usuario> lista = q.getResultList();
        if (lista == null || lista.size() == 0) {
            return null;
        } else {
            return lista.get(0);
        }
    }
    
    public List<Usuario> buscarUsuarios(Integer empleado) {
        Query q = this.em.createQuery("select u from Usuario u where u.empleado = :e");
        q.setParameter("e", empleado);
        List<Usuario> lista = q.getResultList();
        if (lista == null || lista.size() == 0) {
            return null;
        } else {
            return lista;
        }
    }
}
