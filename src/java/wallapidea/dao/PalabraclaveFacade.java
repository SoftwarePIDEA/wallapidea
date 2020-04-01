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
import wallapidea.entity.Palabraclave;
import wallapidea.entity.Usuario;
import wallapidea.entity.Producto;

/**
 *
 * @author Pablo
 */
@Stateless
public class PalabraclaveFacade extends AbstractFacade<Palabraclave> {

    @PersistenceContext(unitName = "wallapideaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PalabraclaveFacade() {
        super(Palabraclave.class);
    }
    public boolean existsPalabra (String palabra){
        Query q;
       Palabraclave pc=null; //Inicializamos la posible palabra clave que haya en la BD
        
        // Las "Named Query" son consultas predefinidas que se ubican antes de la declaración
        // de la clase entidad, en este caso, "Palabraclave":
       
        q = this.getEntityManager().createNamedQuery("Palabraclave.existsPalabra"); //esto hace referencia a una named query que hay en la clase entidad de Palabraclave.java
        q.setParameter("palabra", palabra); // Los parámetros son aquellas cadenas de caracteres que van precedidas de los dos puntos.
        try{
            pc = (Palabraclave) q.getSingleResult(); //Vemos si hay alguna palabraclave en la BD con la palabra que tenemos por parametro
        }catch(Exception exc){
            System.out.println("ERROR: "+exc.toString()); //Esta excepcion saltará si no hay ninguna palabara clave, no va a dar mas problemas que un mensajito por pantalla
        }
        //Si no hay saltará una excepción y la variable pc seguirá siendo null, si hay palabara clave pc se actualizará a su valor de Palabra clave
        return pc != null; //Si es null-> no existe ninguna Palabraclave-> devuelve FALSE, si no -> existe una Palabaclave ya en la bd con esa palabra -> devuelve TRUE
        //Para llamar a este metodo se instancia en cualquier servlet un PalabraclaveFacade y se llama al metodo existsPalabra y se le pasa como argumento la palabra que queremos comprobar que existe.
    }
        public Palabraclave findByPalabra (String palabra){
        Query q;
        Palabraclave pc=null; //Inicializamos la posible palabra clave que haya en la BD
        
        // Las "Named Query" son consultas predefinidas que se ubican antes de la declaración
        // de la clase entidad, en este caso, "Palabraclave":
       
        q = this.getEntityManager().createNamedQuery("Palabraclave.existsPalabra"); //esto hace referencia a una named query que hay en la clase entidad de Palabraclave.java
        q.setParameter("palabra", palabra); // Los parámetros son aquellas cadenas de caracteres que van precedidas de los dos puntos.
        try{
            pc = (Palabraclave) q.getSingleResult(); //Vemos si hay alguna palabraclave en la BD con la palabra que tenemos por parametro
        }catch(Exception exc){
            System.out.println("ERROR: "+exc.toString()); //Esta excepcion saltará si no hay ninguna palabara clave, no va a dar mas problemas que un mensajito por pantalla
        }
        //Si no hay saltará una excepción y la variable pc seguirá siendo null, si hay palabara clave pc se actualizará a su valor de Palabra clave
        return pc; //Si es null-> no existe ninguna Palabraclave-> devuelve FALSE, si no -> existe una Palabaclave ya en la bd con esa palabra -> devuelve TRUE
        //Para llamar a este metodo se instancia en cualquier servlet un PalabraclaveFacade y se llama al metodo existsPalabra y se le pasa como argumento la palabra que queremos comprobar que existe.
    }
    
    public List<Integer> finAllByPalabraClave(){
        return null;
    }
        
        
     public void insertPalabraClave (String palabraClave) {
        EntityManager em ; 
        em = this.getEntityManager();
        Palabraclave pc = new Palabraclave();
        pc.setPalabra(palabraClave);
        em.persist(pc);
    }
     
     
    
    
     
    
    
}