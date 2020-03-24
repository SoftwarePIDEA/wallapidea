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
import wallapidea.entity.Usuario;

/**
 *
 * @author Pablo
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "wallapideaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
        public Usuario findByNombre (String nombre) {
        Query q;
        Usuario usuario;
        
        // Las "Named Query" son consultas predefinidas que se ubican antes de la declaración
        // de la clase entidad, en este caso, "Administrador":
        // @NamedQuery(name = "Administrador.findByEmail", query = "SELECT a FROM Administrador a WHERE a.email = :email")
        q = this.getEntityManager().createNamedQuery("Usuario.findByNombre");
        q.setParameter("nombre", nombre); // Los parámetros son aquellas cadenas de caracteres que van precedidas de los dos puntos.
        usuario = (Usuario) q.getSingleResult();
        return usuario;
    }
        
}
