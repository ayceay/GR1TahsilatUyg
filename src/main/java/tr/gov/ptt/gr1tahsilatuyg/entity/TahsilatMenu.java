/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "THS_MENU")
@NamedQueries({
    @NamedQuery(name = "TahsilatMenu.findAll", query = "SELECT t FROM TahsilatMenu t"),
    @NamedQuery(name = "TahsilatMenu.findById", query = "SELECT t FROM TahsilatMenu t WHERE t.id = :id"),
    @NamedQuery(name = "TahsilatMenu.findByBaslik", query = "SELECT t FROM TahsilatMenu t WHERE t.baslik = :baslik"),
    @NamedQuery(name = "TahsilatMenu.findByLink", query = "SELECT t FROM TahsilatMenu t WHERE t.link = :link")})
public class TahsilatMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 50)
    @Column(name = "BASLIK")
    private String baslik;
    @Size(max = 500)
    @Column(name = "LINK")
    private String link;
    @ManyToMany(mappedBy = "tahsilatMenuList")
    private List<TahsilatKisi> tahsilatKisiList;

    public TahsilatMenu() {
    }

    public TahsilatMenu(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<TahsilatKisi> getTahsilatKisiList() {
        return tahsilatKisiList;
    }

    public void setTahsilatKisiList(List<TahsilatKisi> tahsilatKisiList) {
        this.tahsilatKisiList = tahsilatKisiList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TahsilatMenu)) {
            return false;
        }
        TahsilatMenu other = (TahsilatMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatMenu[ id=" + id + " ]";
    }
    
}
