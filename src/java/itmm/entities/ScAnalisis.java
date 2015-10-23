/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Misael
 */
@Entity
@Table(name = "SC_ANALISIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScAnalisis.findAll", query = "SELECT s FROM ScAnalisis s"),
    @NamedQuery(name = "ScAnalisis.findByAnalisisId", query = "SELECT s FROM ScAnalisis s WHERE s.analisisId = :analisisId"),
    @NamedQuery(name = "ScAnalisis.findByNombre", query = "SELECT s FROM ScAnalisis s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "ScAnalisis.findByMetodo", query = "SELECT s FROM ScAnalisis s WHERE s.metodo = :metodo"),
    @NamedQuery(name = "ScAnalisis.findByNickName", query = "SELECT s FROM ScAnalisis s WHERE s.nickName = :nickName")})
public class ScAnalisis implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANALISIS_ID")
    private BigDecimal analisisId;
    @Size(max = 60)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 20)
    @Column(name = "METODO")
    private String metodo;
    @Size(max = 10)
    @Column(name = "NICK_NAME")
    private String nickName;
    @JoinColumn(name = "TPANALISIS_ID", referencedColumnName = "TPANALISIS_ID")
    @ManyToOne
    private ScTpanalisis tpanalisisId;

    public ScAnalisis() {
    }

    public ScAnalisis(BigDecimal analisisId) {
        this.analisisId = analisisId;
    }

    public BigDecimal getAnalisisId() {
        return analisisId;
    }

    public void setAnalisisId(BigDecimal analisisId) {
        this.analisisId = analisisId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public ScTpanalisis getTpanalisisId() {
        return tpanalisisId;
    }

    public void setTpanalisisId(ScTpanalisis tpanalisisId) {
        this.tpanalisisId = tpanalisisId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (analisisId != null ? analisisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScAnalisis)) {
            return false;
        }
        ScAnalisis other = (ScAnalisis) object;
        if ((this.analisisId == null && other.analisisId != null) || (this.analisisId != null && !this.analisisId.equals(other.analisisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScAnalisis[ analisisId=" + analisisId + " ]";
    }
    
}
