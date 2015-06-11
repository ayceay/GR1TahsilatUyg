/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKurum;

/**
 *
 * @author Administrator
 */
@Stateless
public class TahsilatKurumFacade extends AbstractFacade<TahsilatKurum> {
    @PersistenceContext(unitName = "ptt_GR1TahsilatUyg")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TahsilatKurumFacade() {
        super(TahsilatKurum.class);
    }
    
}
