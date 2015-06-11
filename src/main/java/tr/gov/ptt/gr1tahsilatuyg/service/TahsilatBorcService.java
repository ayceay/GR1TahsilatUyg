/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKurum;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatKurumFacade;

/**
 *
 * @author Administrator
 */

@Stateless
public class TahsilatBorcService {
    @EJB
    private TahsilatKurumFacade tahsilatKurumFacade;
    public List<String> kurumAdlariGetir(String query)
    {
        List<TahsilatKurum> kurumListesi = tahsilatKurumFacade.findAll();
        List<String> kurumListesiStartWithQuery = new ArrayList<String>();
        for (TahsilatKurum kurumListesi1 : kurumListesi) {
            
            if(kurumListesi1.getAd().toUpperCase().startsWith(query.toUpperCase()))
            {
                kurumListesiStartWithQuery.add(kurumListesi1.getAd());
            }
        }
        return kurumListesiStartWithQuery;
    }
    
}
