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
@Table(name = "SC_UNDMEDIDAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScUndmedidas.findAll", query = "SELECT s FROM ScUndmedidas s"),
    @NamedQuery(name = "ScUndmedidas.findByUnidadId", query = "SELECT s FROM ScUndmedidas s WHERE s.unidadId = :unidadId"),
    @NamedQuery(name = "ScUndmedidas.findByNombre", query = "SELECT s FROM ScUndmedidas s WHERE s.nombre = :nombre")})
public class ScUndmedidas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "UNIDAD_ID")
    private String unidadId;
    @Size(max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadId")
    private List<ScMateriales> scMaterialesList;

    public ScUndmedidas() {
    }

    public ScUndmedidas(String unidadId) {
        this.unidadId = unidadId;
    }

    public String getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(String unidadId) {
        this.unidadId = unidadId;
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
        hash += (unidadId != null ? unidadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScUndmedidas)) {
            return false;
        }
        ScUndmedidas other = (ScUndmedidas) object;
        if ((this.unidadId == null && other.unidadId != null) || (this.unidadId != null && !this.unidadId.equals(other.unidadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScUndmedidas[ unidadId=" + unidadId + " ]";
    }
    
}
