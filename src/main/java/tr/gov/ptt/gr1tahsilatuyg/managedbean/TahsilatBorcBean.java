/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.managedbean;

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
    @EJB
    private TahsilatBorcService tahsilatBorcService;
     public TahsilatBorcBean() {
         
         tahsilatBorc = new TahsilatBorc();
    }

    public TahsilatBorc getTahsilatBorc() {
        return tahsilatBorc;
    }

    public void setTahsilatBorc(TahsilatBorc tahsilatBorc) {
        this.tahsilatBorc = tahsilatBorc;
    }
    
    public String faturaSorgula()
    {
        return "tahsilatListele.xhtml?faces-redirect=true";
    }
    
     public List<String> kurumAdlariGetir(String query)
    {
        return tahsilatBorcService.kurumAdlariGetir(query);
    }
    
    
}
