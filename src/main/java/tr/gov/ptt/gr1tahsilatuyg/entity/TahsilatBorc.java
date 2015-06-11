/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "THS_BORC")
@NamedQueries({
    @NamedQuery(name = "TahsilatBorc.findAll", query = "SELECT t FROM TahsilatBorc t"),
    @NamedQuery(name = "TahsilatBorc.findById", query = "SELECT t FROM TahsilatBorc t WHERE t.id = :id"),
    @NamedQuery(name = "TahsilatBorc.findByAboneNo", query = "SELECT t FROM TahsilatBorc t WHERE t.aboneNo = :aboneNo"),
    @NamedQuery(name = "TahsilatBorc.findByAboneAd", query = "SELECT t FROM TahsilatBorc t WHERE t.aboneAd = :aboneAd"),
    @NamedQuery(name = "TahsilatBorc.findByAboneSoyad", query = "SELECT t FROM TahsilatBorc t WHERE t.aboneSoyad = :aboneSoyad"),
    @NamedQuery(name = "TahsilatBorc.findByFaturaNo", query = "SELECT t FROM TahsilatBorc t WHERE t.faturaNo = :faturaNo"),
    @NamedQuery(name = "TahsilatBorc.findByFaturaSonOdemeTrh", query = "SELECT t FROM TahsilatBorc t WHERE t.faturaSonOdemeTrh = :faturaSonOdemeTrh"),
    @NamedQuery(name = "TahsilatBorc.findByFaturaTutar", query = "SELECT t FROM TahsilatBorc t WHERE t.faturaTutar = :faturaTutar"),
    @NamedQuery(name = "TahsilatBorc.findByFaturaDurum", query = "SELECT t FROM TahsilatBorc t WHERE t.faturaDurum = :faturaDurum")})
public class TahsilatBorc implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name = "borcseq",sequenceName = "SEQ_THS_BORC",initialValue = 1,allocationSize = 1)
    @GeneratedValue(generator = "borcseq", strategy = GenerationType.SEQUENCE)
    private BigDecimal id;
    @Size(max = 100)
    @Column(name = "ABONE_NO")
    private String aboneNo;
    @Size(max = 30)
    @Column(name = "ABONE_AD")
    private String aboneAd;
    @Size(max = 30)
    @Column(name = "ABONE_SOYAD")
    private String aboneSoyad;
    @Size(max = 100)
    @Column(name = "FATURA_NO")
    private String faturaNo;
    @Column(name = "FATURA_SON_ODEME_TRH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date faturaSonOdemeTrh;
    @Column(name = "FATURA_TUTAR")
    private BigDecimal faturaTutar;
    @Column(name = "FATURA_DURUM")
    private Integer faturaDurum;
    @JoinColumn(name = "KURUM_ID", referencedColumnName = "ID")
    @ManyToOne
    private TahsilatKurum kurum;

    public TahsilatBorc() {
    }

    public TahsilatBorc(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAboneNo() {
        return aboneNo;
    }

    public void setAboneNo(String aboneNo) {
        this.aboneNo = aboneNo;
    }

    public String getAboneAd() {
        return aboneAd;
    }

    public void setAboneAd(String aboneAd) {
        this.aboneAd = aboneAd;
    }

    public String getAboneSoyad() {
        return aboneSoyad;
    }

    public void setAboneSoyad(String aboneSoyad) {
        this.aboneSoyad = aboneSoyad;
    }

    public String getFaturaNo() {
        return faturaNo;
    }

    public void setFaturaNo(String faturaNo) {
        this.faturaNo = faturaNo;
    }

    public Date getFaturaSonOdemeTrh() {
        return faturaSonOdemeTrh;
    }

    public void setFaturaSonOdemeTrh(Date faturaSonOdemeTrh) {
        this.faturaSonOdemeTrh = faturaSonOdemeTrh;
    }

    public BigDecimal getFaturaTutar() {
        return faturaTutar;
    }

    public void setFaturaTutar(BigDecimal faturaTutar) {
        this.faturaTutar = faturaTutar;
    }

    public Integer getFaturaDurum() {
        return faturaDurum;
    }

    public void setFaturaDurum(Integer faturaDurum) {
        this.faturaDurum = faturaDurum;
    }

    public TahsilatKurum getKurum() {
        return kurum;
    }

    public void setKurum(TahsilatKurum kurum) {
        this.kurum = kurum;
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
        if (!(object instanceof TahsilatBorc)) {
            return false;
        }
        TahsilatBorc other = (TahsilatBorc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatBorc[ id=" + id + " ]";
    }
    
}
