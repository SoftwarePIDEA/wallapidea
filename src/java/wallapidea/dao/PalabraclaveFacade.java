/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import wallapidea.entity.Palabraclave;

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
    
}
