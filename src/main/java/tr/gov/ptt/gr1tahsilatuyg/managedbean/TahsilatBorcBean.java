/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.managedbean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatBorc;
import tr.gov.ptt.gr1tahsilatuyg.service.TahsilatBorcService;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class TahsilatBorcBean {

   
    
    private TahsilatBorc tahsilatBorc;
    private List<TahsilatBorc> borcListesi;
    private List<TahsilatBorc> filteredBorcListesi;
    @EJB
    private TahsilatBorcService tahsilatBorcService;
    private BigDecimal toplam;
    private BigDecimal alinan;
    private BigDecimal paraustu;
     
     public TahsilatBorcBean() {
         
         tahsilatBorc = new TahsilatBorc();
         borcListesi = new ArrayList<TahsilatBorc>();
         toplam = new BigDecimal(0);
         alinan = new BigDecimal(0);
         paraustu = new BigDecimal(0);
    }

    public List<TahsilatBorc> getBorcListesi() {
        return borcListesi;
    }

    public void setBorcListesi(List<TahsilatBorc> borcListesi) {
        this.borcListesi = borcListesi;
    }

     
     
    public TahsilatBorc getTahsilatBorc() {
        return tahsilatBorc;
    }

    public void setTahsilatBorc(TahsilatBorc tahsilatBorc) {
        this.tahsilatBorc = tahsilatBorc;
    }
    
    public String faturaSorgula()
    {
        borcListesi = tahsilatBorcService.findAllBorcViaKurumIdAndAboneNo(tahsilatBorc.getKurum().getAd(),
                tahsilatBorc.getAboneNo());
        
        return "tahsilatListele.xhtml?faces-redirect=true";
    }
    
     public List<String> kurumAdlariGetir(String query)
    {
        return tahsilatBorcService.kurumAdlariGetir(query);
    }

    public List<TahsilatBorc> getFilteredBorcListesi() {
        return filteredBorcListesi;
    }

    public void setFilteredBorcListesi(List<TahsilatBorc> filteredBorcListesi) {
        this.filteredBorcListesi = filteredBorcListesi;
    }

    public BigDecimal getToplam() {
        return toplam;
    }

    public void setToplam(BigDecimal toplam) {
        this.toplam = toplam;
    }

    public BigDecimal getAlinan() {
        return alinan;
    }

    public void setAlinan(BigDecimal alinan) {
        this.alinan = alinan;
    }

    public BigDecimal getParaustu() {
        return paraustu;
    }

    public void setParaustu(BigDecimal paraustu) {
        this.paraustu = paraustu;
    }
    
    public void hesapla()
    {
        toplam = new BigDecimal(0.0);
        
        for (TahsilatBorc borcListesi1 : filteredBorcListesi) {
           toplam = toplam.add(borcListesi1.getFaturaTutar());
        }
        paraUstuHesapla();
    }
    
    public void paraUstuHesapla()
    {
        paraustu = toplam.subtract(alinan);
    }
    
}
