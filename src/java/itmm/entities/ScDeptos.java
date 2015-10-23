/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.entities;

import java.io.Serializable;
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
@Table(name = "SC_DEPTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScDeptos.findAll", query = "SELECT s FROM ScDeptos s"),
    @NamedQuery(name = "ScDeptos.findByDeptoId", query = "SELECT s FROM ScDeptos s WHERE s.deptoId = :deptoId"),
    @NamedQuery(name = "ScDeptos.findByNombre", query = "SELECT s FROM ScDeptos s WHERE s.nombre = :nombre")})
public class ScDeptos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "DEPTO_ID")
    private String deptoId;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "deptoId")
    private List<ScUsuarios> scUsuariosList;

    public ScDeptos() {
    }

    public ScDeptos(String deptoId) {
        this.deptoId = deptoId;
    }

    public String getDeptoId() {
        return deptoId;
    }

    public void setDeptoId(String deptoId) {
        this.deptoId = deptoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ScUsuarios> getScUsuariosList() {
        return scUsuariosList;
    }

    public void setScUsuariosList(List<ScUsuarios> scUsuariosList) {
        this.scUsuariosList = scUsuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptoId != null ? deptoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScDeptos)) {
            return false;
        }
        ScDeptos other = (ScDeptos) object;
        if ((this.deptoId == null && other.deptoId != null) || (this.deptoId != null && !this.deptoId.equals(other.deptoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScDeptos[ deptoId=" + deptoId + " ]";
    }
    
}
