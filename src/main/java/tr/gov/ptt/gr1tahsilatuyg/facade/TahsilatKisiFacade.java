/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKisi;

/**
 *
 * @author Administrator
 */
@Stateless
public class TahsilatKisiFacade extends AbstractFacade<TahsilatKisi> {
    @PersistenceContext(unitName = "ptt_GR1TahsilatUyg")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TahsilatKisiFacade() {
        super(TahsilatKisi.class);
    }
    
    public TahsilatKisi giriseYetkilimi(TahsilatKisi p_kisi) 
    {
         
        try{
          TahsilatKisi  kisi =  (TahsilatKisi)em.createNamedQuery("TahsilatKisi.giriseYetkilimi").setParameter("kullaniciAd", p_kisi.getKullaniciAd())
                .setParameter("sifre", p_kisi.getSifre()).getSingleResult();
            
            return kisi;
        }
        catch(NoResultException exp)
        {
            return null;
        }
        
    }
    
    
}
