/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "THS_TAHSILAT")
@NamedQueries({
    @NamedQuery(name = "Tahsilat.findAll", query = "SELECT t FROM Tahsilat t"),
    @NamedQuery(name = "Tahsilat.findById", query = "SELECT t FROM Tahsilat t WHERE t.id = :id"),
    @NamedQuery(name = "Tahsilat.findByIslemTrh", query = "SELECT t FROM Tahsilat t WHERE t.islemTrh = :islemTrh"),
    @NamedQuery(name = "Tahsilat.findByKisiSiraNo", query = "SELECT t FROM Tahsilat t WHERE t.kisiSiraNo = :kisiSiraNo"),
    @NamedQuery(name = "Tahsilat.findByTutar", query = "SELECT t FROM Tahsilat t WHERE t.tutar = :tutar")})
public class Tahsilat implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "seqTahsilat",sequenceName = "SEQ_THS_TAHSILAT" ,allocationSize = 1)
    @GeneratedValue(generator = "seqTahsilat" , strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "ISLEM_TRH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date islemTrh;
    @Column(name = "KISI_SIRA_NO")
    private Integer kisiSiraNo;
    @Column(name = "TUTAR")
    private BigDecimal tutar;
    @JoinColumn(name = "KURUM_ID", referencedColumnName = "ID")
    @ManyToOne
    private TahsilatKurum kurum;
    @JoinColumn(name = "KISI_ID", referencedColumnName = "ID")
    @ManyToOne
    private TahsilatKisi kisi;
    @OneToMany(mappedBy = "tahsilat" ,cascade = CascadeType.ALL)
    private List<TahsilatDetay> tahsilatDetayList;
    @OneToMany(mappedBy = "tahsilat" ,  cascade = {CascadeType.PERSIST ,CascadeType.MERGE})
    private List<TahsilatMuhasebe> tahsilatMuhasebeList;

    public Tahsilat() {
    }

    public Tahsilat(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getIslemTrh() {
        return islemTrh;
    }

    public void setIslemTrh(Date islemTrh) {
        this.islemTrh = islemTrh;
    }

    public Integer getKisiSiraNo() {
        return kisiSiraNo;
    }

    public void setKisiSiraNo(Integer kisiSiraNo) {
        this.kisiSiraNo = kisiSiraNo;
    }

    public BigDecimal getTutar() {
        return tutar;
    }

    public void setTutar(BigDecimal tutar) {
        this.tutar = tutar;
    }

    public TahsilatKurum getKurum() {
        return kurum;
    }

    public void setKurum(TahsilatKurum kurum) {
        this.kurum = kurum;
    }

    public TahsilatKisi getKisi() {
        return kisi;
    }

    public void setKisi(TahsilatKisi kisi) {
        this.kisi = kisi;
    }

    public List<TahsilatDetay> getTahsilatDetayList() {
        return tahsilatDetayList;
    }

    public void setTahsilatDetayList(List<TahsilatDetay> tahsilatDetayList) {
        this.tahsilatDetayList = tahsilatDetayList;
    }

    public List<TahsilatMuhasebe> getTahsilatMuhasebeList() {
        return tahsilatMuhasebeList;
    }

    public void setTahsilatMuhasebeList(List<TahsilatMuhasebe> tahsilatMuhasebeList) {
        this.tahsilatMuhasebeList = tahsilatMuhasebeList;
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
        if (!(object instanceof Tahsilat)) {
            return false;
        }
        Tahsilat other = (Tahsilat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tr.gov.ptt.gr1tahsilatuyg.entity.Tahsilat[ id=" + id + " ]";
    }
    
}
