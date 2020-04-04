/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import wallapidea.entity.Categoria;
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
    
     public void updateByProduct (Integer id , Categoria categoria , String titulo , String descripcion , Double precio , String foto , Double valoracionmedia) {
        Query q;
        // Las "Named Query" son consultas predefinidas que se ubican antes de la declaración
        // de la clase entidad, en este caso, "Administrador":
        // @NamedQuery(name = "Administrador.findByEmail", query = "SELECT a FROM Administrador a WHERE a.email = :email")
        q = this.getEntityManager().createQuery("UPDATE Producto p set p.catId = :categoria , p.titulo = :titulo , p.descripcion = :descripcion , "
                + "  p.precio = :precio ,  p.foto = :foto ,  p.valoracionmedia = :valoracionmedia  WHERE p.productId = :id " );
        q.setParameter("categoria", categoria); // Los parámetros son aquellas cadenas de caracteres que van precedidas de los dos puntos.
        q.setParameter("titulo", titulo);
        q.setParameter("descripcion", descripcion);
        q.setParameter("precio", precio);
        q.setParameter("foto", foto);
        q.setParameter("valoracionmedia", valoracionmedia);
        q.setParameter("id", id);
        q.executeUpdate();
                
                
    }
     
    //busca todos los productos que coinciden con las palabras claves
    //y no son productos subidos por el usuario
    public List<Producto> findByKey(String key, int user_id){
        Query q;

        q = this.getEntityManager().createQuery("SELECT p " 
                                                + "FROM Producto p, Palabraclave pc "
                                                + "WHERE p IN (pc.productoList) AND "
                                                + "UPPER(pc.palabra) LIKE :palabra AND "
                                                + "p.usuarioId.usuarioId <> :user_id");
        q.setParameter("palabra", "%" + key.toUpperCase() + "%");
        q.setParameter("user_id", user_id);
        
        return q.getResultList();
    }
     public List<Producto> findByTitle(String title, int user_id){
        Query q;

        q = this.getEntityManager().createQuery("SELECT p " 
                                                + "FROM Producto p "                                                
                                                + "WHERE UPPER(p.titulo) LIKE :titulo AND "
                                                + "p.usuarioId.usuarioId <> :user_id");
        q.setParameter("titulo", "%" + title.toUpperCase() + "%");
        q.setParameter("user_id", user_id);
        
        return q.getResultList();
    }
    
     public List<Producto> findByDescripcion(String desc, int user_id){
        Query q;

        q = this.getEntityManager().createQuery("SELECT p " 
                                                + "FROM Producto p "                                                
                                                + "WHERE UPPER(p.descripcion) LIKE :desc AND "
                                                + "p.usuarioId.usuarioId <> :user_id");
        q.setParameter("desc", "%" + desc.toUpperCase() + "%");
        q.setParameter("user_id", user_id);
        
        return q.getResultList();
    }
     
    public List<Producto> findAllExceptUserProduct(int user_id){
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT P FROM Producto p WHERE p.usuarioId.usuarioId <> :user_id");
        
        q.setParameter("user_id", user_id);
        
        return q.getResultList();
    }
    
    
    
}
