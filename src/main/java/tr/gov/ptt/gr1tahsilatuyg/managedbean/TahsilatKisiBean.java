/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKisi;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatMenu;
import tr.gov.ptt.gr1tahsilatuyg.service.TahsilatKisiService;
import tr.gov.ptt.gr1tahsilatuyg.util.JSFUtil;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped 
//@ViewScope Kendi üzerindeki veriyi anca başka sayfaya gittiğinde kaybeder
//@SessionScope session truncate olana kadar veriyi üzerinde tutar
//@RequestScope request bitene kadar üzerindeki veriyi tutar

//@stateless tek bir servis - her session bunu kullanabilir
//@statefull her session için ayrı ayrı servis oluşturur.100 session açılırsa 100 tane servis açılır.
//Alış veriş sitelerindeki sepet mantığı


public class TahsilatKisiBean {
    
    private TahsilatKisi kisi;
   
    
    @EJB
    private TahsilatKisiService tahsilatKisiService;
    
       public TahsilatKisiBean() {
           kisi = new TahsilatKisi();
          
        }

    public TahsilatKisi getKisi() {
        return kisi;
    }

    public void setKisi(TahsilatKisi kisi) {
        this.kisi = kisi;
    }
       
    
    public String girisKontrol()
    {
        TahsilatKisi sonucKisi = null;
        boolean islemSonuc = false;
        try{
             sonucKisi = tahsilatKisiService.giriseYetkilimi(kisi);
             islemSonuc = true;
             
        }
        catch(NoResultException exp)
        {
            System.out.println("GİRİŞ HATA --- " +exp.getMessage());
        }
        
        if(islemSonuc && sonucKisi != null)
        {
            System.out.println("MENÜ LİSTESİ************************************");
            for (int i = 0; i < sonucKisi.getTahsilatMenuList().size(); i++) {
                System.out.println(i+".Menü : "+sonucKisi.getTahsilatMenuList().get(i).getBaslik());
            }
            
            HttpSession session = JSFUtil.getSession();
            session.setAttribute("username", sonucKisi.getKullaniciAd());
            return "menu.xhtml?faces-redirect=true";
        }
        else
        {
            JSFUtil.hataMesajiEkle("Giriş Hatası", kisi.getKullaniciAd()+ " Kullanıcı ile yapılan giriş hatalı!");
            return "giris.xhtml?faces-redirect=true";
        }
        
        
    }
    
    
    
}
