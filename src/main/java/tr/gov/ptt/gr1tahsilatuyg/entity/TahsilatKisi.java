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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "THS_KISI")
@NamedQueries({
    @NamedQuery(name = "TahsilatKisi.findAll", query = "SELECT t FROM TahsilatKisi t"),
    @NamedQuery(name = "TahsilatKisi.findById", query = "SELECT t FROM TahsilatKisi t WHERE t.id = :id"),
    @NamedQuery(name = "TahsilatKisi.findByAd", query = "SELECT t FROM TahsilatKisi t WHERE t.ad = :ad"),
    @NamedQuery(name = "TahsilatKisi.findBySoyad", query = "SELECT t FROM TahsilatKisi t WHERE t.soyad = :soyad"),
    @NamedQuery(name = "TahsilatKisi.findByKullaniciAd", query = "SELECT t FROM TahsilatKisi t WHERE t.kullaniciAd = :kullaniciAd"),
    @NamedQuery(name = "TahsilatKisi.findBySifre", query = "SELECT t FROM TahsilatKisi t WHERE t.sifre = :sifre"),
    @NamedQuery(name = "TahsilatKisi.findByRol", query = "SELECT t FROM TahsilatKisi t WHERE t.rol = :rol")})
public class TahsilatKisi implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 30)
    @Column(name = "AD")
    private String ad;
    @Size(max = 30)
    @Column(name = "SOYAD")
    private String soyad;
    @Size(max = 20)
    @Column(name = "KULLANICI_AD")
    private String kullaniciAd;
    @Size(max = 20)
    @Column(name = "SIFRE")
    private String sifre;
    @Size(max = 1)
    @Column(name = "ROL")
    private String rol;
    @JoinTable(name = "THS_KISI_MENU", joinColumns = {
        @JoinColumn(name = "KISI_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "MENU_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<TahsilatMenu> tahsilatMenuList;

    public TahsilatKisi() {
    }

    public TahsilatKisi(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getKullaniciAd() {
        return kullaniciAd;
    }

    public void setKullaniciAd(String kullaniciAd) {
        this.kullaniciAd = kullaniciAd;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<TahsilatMenu> getTahsilatMenuList() {
        return tahsilatMenuList;
    }

    public void setTahsilatMenuList(List<TahsilatMenu> tahsilatMenuList) {
        this.tahsilatMenuList = tahsilatMenuList;
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
        if (!(object instanceof TahsilatKisi)) {
            return false;
        }
        TahsilatKisi other = (TahsilatKisi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKisi[ id=" + id + " ]";
    }
    
}
