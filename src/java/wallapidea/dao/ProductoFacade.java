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

/**
 *
 * @author Pablo
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "wallapideaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
     public void updateByProduct (Producto producto) {
        Query q;
        // Las "Named Query" son consultas predefinidas que se ubican antes de la declaración
        // de la clase entidad, en este caso, "Administrador":
        // @NamedQuery(name = "Administrador.findByEmail", query = "SELECT a FROM Administrador a WHERE a.email = :email")
        q = this.getEntityManager().createQuery("UPDATE Producto p set p.catId = "+ producto.getCatId().getCatId() + ", p.titulo = '" + producto.getTitulo() + "', p.descripcion = '"+producto.getDescripcion()+"', "
                + "  p.precio =  " + producto.getPrecio()  +  ",  p.foto =  '"+ producto.getFoto() +"',  p.valoracionmedia = "+producto.getValoracionmedia()+" WHERE p.productId = " + producto.getProductId());
        q.setParameter("producto", producto); // Los parámetros son aquellas cadenas de caracteres que van precedidas de los dos puntos.
        q.executeUpdate();
                
                
    }
     
     
    
    
    
}
