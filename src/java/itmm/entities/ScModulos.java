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
@Table(name = "SC_MODULOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScModulos.findAll", query = "SELECT s FROM ScModulos s"),
    @NamedQuery(name = "ScModulos.findByModuloId", query = "SELECT s FROM ScModulos s WHERE s.moduloId = :moduloId"),
    @NamedQuery(name = "ScModulos.findByNombre", query = "SELECT s FROM ScModulos s WHERE s.nombre = :nombre")})
public class ScModulos implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MODULO_ID")
    private BigDecimal moduloId;
    @Size(max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "moduloId")
    private List<ScFormas> scFormasList;
    @OneToMany(mappedBy = "moduloId")
    private List<ScMenus> scMenusList;

    public ScModulos() {
    }

    public ScModulos(BigDecimal moduloId) {
        this.moduloId = moduloId;
    }

    public BigDecimal getModuloId() {
        return moduloId;
    }

    public void setModuloId(BigDecimal moduloId) {
        this.moduloId = moduloId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ScFormas> getScFormasList() {
        return scFormasList;
    }

    public void setScFormasList(List<ScFormas> scFormasList) {
        this.scFormasList = scFormasList;
    }

    @XmlTransient
    public List<ScMenus> getScMenusList() {
        return scMenusList;
    }

    public void setScMenusList(List<ScMenus> scMenusList) {
        this.scMenusList = scMenusList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moduloId != null ? moduloId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScModulos)) {
            return false;
        }
        ScModulos other = (ScModulos) object;
        if ((this.moduloId == null && other.moduloId != null) || (this.moduloId != null && !this.moduloId.equals(other.moduloId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScModulos[ moduloId=" + moduloId + " ]";
    }
    
}
