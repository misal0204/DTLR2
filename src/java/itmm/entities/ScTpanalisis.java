/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Misael
 */
@Entity
@Table(name = "SC_TPANALISIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScTpanalisis.findAll", query = "SELECT s FROM ScTpanalisis s"),
    @NamedQuery(name = "ScTpanalisis.findByTpanalisisId", query = "SELECT s FROM ScTpanalisis s WHERE s.tpanalisisId = :tpanalisisId"),
    @NamedQuery(name = "ScTpanalisis.findByNombre", query = "SELECT s FROM ScTpanalisis s WHERE s.nombre = :nombre")})
public class ScTpanalisis implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TPANALISIS_ID")
    private BigDecimal tpanalisisId;
    @Size(max = 40)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "tpanalisisId")
    private List<ScAnalisis> scAnalisisList;

    public ScTpanalisis() {
    }

    public ScTpanalisis(BigDecimal tpanalisisId) {
        this.tpanalisisId = tpanalisisId;
    }

    public BigDecimal getTpanalisisId() {
        return tpanalisisId;
    }

    public void setTpanalisisId(BigDecimal tpanalisisId) {
        this.tpanalisisId = tpanalisisId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ScAnalisis> getScAnalisisList() {
        return scAnalisisList;
    }

    public void setScAnalisisList(List<ScAnalisis> scAnalisisList) {
        this.scAnalisisList = scAnalisisList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tpanalisisId != null ? tpanalisisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScTpanalisis)) {
            return false;
        }
        ScTpanalisis other = (ScTpanalisis) object;
        if ((this.tpanalisisId == null && other.tpanalisisId != null) || (this.tpanalisisId != null && !this.tpanalisisId.equals(other.tpanalisisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScTpanalisis[ tpanalisisId=" + tpanalisisId + " ]";
    }
    
}
