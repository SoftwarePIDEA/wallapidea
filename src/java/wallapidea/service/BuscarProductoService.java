/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.EJB;
import wallapidea.dao.ProductoFacade;
import wallapidea.entity.Palabraclave;
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
}
