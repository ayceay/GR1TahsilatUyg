/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tr.gov.ptt.gr1tahsilatuyg.entity.Tahsilat;

/**
 *
 * @author Administrator
 */
@Stateless
public class TahsilatFacade extends AbstractFacade<Tahsilat> {
    @PersistenceContext(unitName = "ptt_GR1TahsilatUyg")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TahsilatFacade() {
        super(Tahsilat.class);
    }
    
}
