/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.ejb.EJB;
import wallapidea.dao.ProductoFacade;
import wallapidea.entity.Producto;

/**
 *
 * @author aleja
 */

@Stateless
public class BuscarProductoService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private ProductoFacade productoFacade;
    
    public List<Producto> getAll(int user_id){
        return this.productoFacade.findAllExceptUserProduct(user_id);
    }
    
    //busca todos los productos que coinciden con las palabras claves
    //y no son productos subidos por el usuario
    public List<Producto> findByKeys(String keys, int user_id){
        return this.productoFacade.findByKey(keys, user_id);
    }
    public List<Producto> findByTitle(String title, int user_id){
        return this.productoFacade.findByTitle(title, user_id);
    }
    
    public List<Producto> findByDesc(String desc, int user_id){
        return this.productoFacade.findByDescripcion(desc, user_id);
    }
    public List<Producto> findByCatId(int catid, int user_id){
        return this.productoFacade.findByCategoria(catid, user_id);
    }
    
    public List<Producto> findByKeysOrTitle(String search, int user_id){
        List<Producto> lista = this.findByKeys(search, user_id);
        List<Producto> lista2 = this.findByTitle(search, user_id);  
        for (Producto x : lista){
            if(!lista2.contains(x)){
                lista2.add(x);
            }
        }
        lista2 = lista2.stream().distinct().collect(Collectors.toList());
        return lista2;
    }
    public List<Producto> findByCatAndKeysOrTitleOrDesc(String search, int cat_id ,int user_id){
        List<Producto> lista = this.findByCatId(cat_id, user_id);
        List<Producto> lista2 = this.findByKeysOrTitleOrDesc(search, user_id);
        List<Producto> lista3 = new LinkedList<>();
        for (Producto x : lista){
            if(lista2.contains(x)){
                lista3.add(x);
            }
        }
        //lista2 = lista2.stream().distinct().collect(Collectors.toList());
        return lista3;
    }
    public List<Producto> findByDescOrTitle(String search, int user_id){
        List<Producto> lista = this.findByDesc(search, user_id);
        List<Producto> lista2 = this.findByTitle(search, user_id);  
        for (Producto x : lista){
            if(!lista2.contains(x)){
                lista2.add(x);
            }
        }
        lista2 = lista2.stream().distinct().collect(Collectors.toList());
        return lista2;
    }
        public List<Producto> findByKeysOrTitleOrDesc(String search, int user_id){
        List<Producto> lista = this.findByKeysOrTitle(search, user_id);
        List<Producto> lista2 = this.findByDesc(search, user_id);  
        for (Producto x : lista){
            if(!lista2.contains(x)){
                lista2.add(x);
            }
        }
        lista2 = lista2.stream().distinct().collect(Collectors.toList());
        return lista2;
    }
        
    public List<Producto> getRecentProducts(int user_id){
        return this.productoFacade.getRecentProducts(user_id);
    }
    
    public List<Producto> getProductByDate(int user_id, String fecha){
        List<Producto> productos = this.productoFacade.findAllExceptUserProduct(user_id);
        List<Producto> seleccionados = new ArrayList<Producto>();
        
        for(Producto p : productos){
            String f = new SimpleDateFormat("yyyy-MM-dd").format(p.getFechayhora());
            if(f.equals(fecha)){
                seleccionados.add(p);
            }
        }
        
        return seleccionados;
    }
}
