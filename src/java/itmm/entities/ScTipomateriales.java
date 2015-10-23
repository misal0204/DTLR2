/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "SC_TIPOMATERIALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScTipomateriales.findAll", query = "SELECT s FROM ScTipomateriales s"),
    @NamedQuery(name = "ScTipomateriales.findByTipomaterialId", query = "SELECT s FROM ScTipomateriales s WHERE s.tipomaterialId = :tipomaterialId"),
    @NamedQuery(name = "ScTipomateriales.findByNombre", query = "SELECT s FROM ScTipomateriales s WHERE s.nombre = :nombre")})
public class ScTipomateriales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "TIPOMATERIAL_ID")
    private String tipomaterialId;
    @Size(max = 40)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipomaterialId")
    private List<ScMateriales> scMaterialesList;

    public ScTipomateriales() {
    }

    public ScTipomateriales(String tipomaterialId) {
        this.tipomaterialId = tipomaterialId;
    }

    public String getTipomaterialId() {
        return tipomaterialId;
    }

    public void setTipomaterialId(String tipomaterialId) {
        this.tipomaterialId = tipomaterialId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ScMateriales> getScMaterialesList() {
        return scMaterialesList;
    }

    public void setScMaterialesList(List<ScMateriales> scMaterialesList) {
        this.scMaterialesList = scMaterialesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipomaterialId != null ? tipomaterialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScTipomateriales)) {
            return false;
        }
        ScTipomateriales other = (ScTipomateriales) object;
        if ((this.tipomaterialId == null && other.tipomaterialId != null) || (this.tipomaterialId != null && !this.tipomaterialId.equals(other.tipomaterialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScTipomateriales[ tipomaterialId=" + tipomaterialId + " ]";
    }
    
}
