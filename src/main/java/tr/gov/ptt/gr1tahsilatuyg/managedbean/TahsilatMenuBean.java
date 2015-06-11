/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.managedbean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatMenu;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class TahsilatMenuBean {
    
    private MenuModel simpleMenuModel = new DefaultMenuModel();
   
    @ManagedProperty(value="#{tahsilatKisiBean}")
    private TahsilatKisiBean kisibean;

    

    public void setKisibean(TahsilatKisiBean kisibean) {
        this.kisibean = kisibean;
    }
    @PostConstruct
    public void init()
    {
     
        List<TahsilatMenu> menuListesi = kisibean.getKisi().getTahsilatMenuList();
        
        if(menuListesi != null)
        {
        DefaultSubMenu subMenu = new DefaultSubMenu();
        subMenu.setLabel("Kullanıcı İşlemleri");
        
        DefaultMenuItem menuItem ;
        
        for (TahsilatMenu menuListesi1 : menuListesi) {
            
            menuItem = new DefaultMenuItem();
             menuItem.setValue(menuListesi1.getBaslik());
            menuItem.setCommand(menuListesi1.getLink());           
            subMenu.addElement(menuItem);
        }
        
        /*menuItem.setValue("Tahsilat Giriş");
        menuItem.setUrl("tahsilatGiris.xhtml");
        //simpleMenuModel.addElement(menuItem);
        subMenu.addElement(menuItem);
        
        
        menuItem = new DefaultMenuItem();
        menuItem.setValue("Tahsilat Sonuç");
        menuItem.setUrl("tahsilatSonuc.xhtml");
        //simpleMenuModel.addElement(menuItem);
        subMenu.addElement(menuItem);
        */
        simpleMenuModel.addElement(subMenu);
        /*
        subMenu = new DefaultSubMenu();
        subMenu.setLabel("Yönetici İşlemleri");
        
        menuItem = new DefaultMenuItem();
        menuItem.setValue("Tahsilat Yönetici");
        menuItem.setUrl("tahsilatYonetici.xhtml");
        //simpleMenuModel.addElement(menuItem);
        subMenu.addElement(menuItem);
        
        
        menuItem = new DefaultMenuItem();
        menuItem.setValue("Tahsilat Rapor");
        menuItem.setUrl("tahsilatRapor.xhtml");
        //simpleMenuModel.addElement(menuItem);
        subMenu.addElement(menuItem);
        
        simpleMenuModel.addElement(subMenu);*/
        }
        else {
            DefaultSubMenu subMenu = new DefaultSubMenu();
            subMenu.setLabel("Giriş İşlemleri");

            DefaultMenuItem menuItem = new DefaultMenuItem();
            menuItem.setValue("Giriş");
            menuItem.setUrl("giris.xhtml?faces-redirect=true");
            //simpleMenuModel.addElement(menuItem);
            subMenu.addElement(menuItem);
            simpleMenuModel.addElement(subMenu);
        }  
        
    }
    public TahsilatMenuBean() {
    }
    
    public MenuModel getSimpleMenuModel() {
        return simpleMenuModel;
    }
    
    
    
}
