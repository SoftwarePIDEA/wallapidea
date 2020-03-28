/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import wallapidea.entity.Producto;
import wallapidea.entity.Usuario;
import wallapidea.entity.Valoracion;

/**
 *
 * @author Pablo
 */
@Stateless
public class ValoracionFacade extends AbstractFacade<Valoracion> {

    @PersistenceContext(unitName = "wallapideaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ValoracionFacade() {
        super(Valoracion.class);
    }
      public Valoracion findByProductId (Integer productId) {
        Query q;
        Valoracion val=null;
        
        // Las "Named Query" son consultas predefinidas que se ubican antes de la declaración
        // de la clase entidad, en este caso, "Administrador":
        // @NamedQuery(name = "Administrador.findByEmail", query = "SELECT a FROM Administrador a WHERE a.email = :email")
        q = this.getEntityManager().createNamedQuery("Valoracion.findByProductId");
        q.setParameter("productId", productId); // Los parámetros son aquellas cadenas de caracteres que van precedidas de los dos puntos.
        try{
            val = (Valoracion) q.getSingleResult();
        }catch(Exception exc){
            System.out.println(exc.toString());
        }     
        return val;
    }
      
      public void deleteByProduct (Producto productId) {
        Query q;
        // Las "Named Query" son consultas predefinidas que se ubican antes de la declaración
        // de la clase entidad, en este caso, "Administrador":
        // @NamedQuery(name = "Administrador.findByEmail", query = "SELECT a FROM Administrador a WHERE a.email = :email")
        q = this.getEntityManager().createNamedQuery("Valoracion.deleteByProduct");
        q.setParameter("productId", productId); // Los parámetros son aquellas cadenas de caracteres que van precedidas de los dos puntos.
        q.executeUpdate();
    }
}
