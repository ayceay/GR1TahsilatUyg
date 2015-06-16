/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;
import tr.gov.ptt.gr1tahsilatuyg.entity.Tahsilat;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatBorc;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatDetay;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKisi;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKurum;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatMuhasebe;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatBorcFacade;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatFacade;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatKisiFacade;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatKurumFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.SystemException;
/**
 *
 * @author Administrator
 */

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TahsilatBorcService {
    @EJB
    private TahsilatKurumFacade tahsilatKurumFacade;
    @EJB
    private TahsilatBorcFacade  tahsilatBorcFacade;
    @EJB
    private TahsilatKisiFacade tahsilatKisiFacade;
    @EJB
    private TahsilatFacade tahsilatFacade;
    @Resource
    UserTransaction userTransaction;
    
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
    
    public List<TahsilatBorc> findAllBorcViaKurumIdAndAboneNo(String p_kurumad,String p_aboneNo)
    {
        Integer kurumId = tahsilatKurumFacade.findKurumId(p_kurumad);
        List<TahsilatBorc> borcList = null;
        if(kurumId != null)
        {
            borcList = tahsilatBorcFacade.findAllBorcViaKurumIdAndAboneNo(kurumId, p_aboneNo);
        }
        
        return borcList;
    }
    
    public boolean seciliFaturalariOde(List<TahsilatBorc> p_seciliBorclar,TahsilatKisi p_kisi)
    {
        Tahsilat tahsilat;
        TahsilatDetay tahsilatDetay;
        TahsilatMuhasebe tahsilatMuhasebe1;
        TahsilatMuhasebe tahsilatMuhasebe2;
        boolean sonuc = false;
        try {
            
            
            
            
            
            for (TahsilatBorc borc : p_seciliBorclar) {
                
                tahsilat = new Tahsilat();
                tahsilat.setIslemTrh(new java.util.Date());
                tahsilat.setKisi(p_kisi);
                tahsilat.setKisiSiraNo(p_kisi.getSirano());
                tahsilat.setKurum(borc.getKurum());
                tahsilat.setTutar(borc.getFaturaTutar());
                
                tahsilatDetay = new TahsilatDetay();
                tahsilatDetay.setTahsilat(tahsilat);
                tahsilatDetay.setTutar(borc.getFaturaTutar());
                tahsilatDetay.setAboneNo(borc.getAboneNo());
                tahsilatDetay.setFaturaNo(borc.getFaturaNo());
                tahsilatDetay.setFaturaSonOdemeTrh(borc.getFaturaSonOdemeTrh());
                
                tahsilatMuhasebe1 = new TahsilatMuhasebe();
                tahsilatMuhasebe1.setTahsilat(tahsilat);
                tahsilatMuhasebe1.setHesapNo("4139-123456");
                tahsilatMuhasebe1.setTutar(borc.getFaturaTutar());
                
                tahsilatMuhasebe2 = new TahsilatMuhasebe();
                tahsilatMuhasebe2.setTahsilat(tahsilat);
                tahsilatMuhasebe2.setHesapNo("Kasa-123456");
                tahsilatMuhasebe2.setTutar(borc.getFaturaTutar());
                
                List<TahsilatDetay> detayListe = new ArrayList<TahsilatDetay>();
                detayListe.add(tahsilatDetay);
                
                List<TahsilatMuhasebe> muhListe
                        = new ArrayList<TahsilatMuhasebe>();
                muhListe.add(tahsilatMuhasebe1);
                muhListe.add(tahsilatMuhasebe2);
                
                tahsilat.setTahsilatDetayList(detayListe);
                tahsilat.setTahsilatMuhasebeList(muhListe);
                
                userTransaction.begin();
                   
                tahsilatFacade.create(tahsilat);
                
                
                System.out.println("Tahsilat No:" + tahsilat.getId());
                
                //int x = 7/0;
                
                p_kisi.setSirano(p_kisi.getSirano()+ 1);
                tahsilatKisiFacade.edit(p_kisi);
                
                borc.setFaturaDurum(1);
                tahsilatBorcFacade.edit(borc);
                
                userTransaction.commit();
                sonuc = true;
                
            }
        } 
        catch (Exception e) {
            sonuc = false;
            try {
                userTransaction.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(TahsilatBorcService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(TahsilatBorcService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(TahsilatBorcService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        finally
        {
            return sonuc;
        }
        
    }
    
    public List<Object[]> chartVerisiGetir()
    {
        return tahsilatBorcFacade.chartVerisiGetir();
    }
    
}
