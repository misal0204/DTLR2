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
import javax.persistence.ManyToMany;
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
@Table(name = "SC_PAISES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScPaises.findAll", query = "SELECT s FROM ScPaises s"),
    @NamedQuery(name = "ScPaises.findByPaisId", query = "SELECT s FROM ScPaises s WHERE s.paisId = :paisId"),
    @NamedQuery(name = "ScPaises.findByNombre", query = "SELECT s FROM ScPaises s WHERE s.nombre = :nombre")})
public class ScPaises implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "PAIS_ID")
    private String paisId;
    @Size(max = 40)
    @Column(name = "NOMBRE")
    private String nombre;
    @ManyToMany(mappedBy = "scPaisesList")
    private List<ScCentros> scCentrosList;
    @ManyToMany(mappedBy = "scPaisesList")
    private List<ScUsuarios> scUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paisId")
    private List<DlEncamh> dlEncamhList;

    public ScPaises() {
    }

    public ScPaises(String paisId) {
        this.paisId = paisId;
    }

    public String getPaisId() {
        return paisId;
    }

    public void setPaisId(String paisId) {
        this.paisId = paisId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ScCentros> getScCentrosList() {
        return scCentrosList;
    }

    public void setScCentrosList(List<ScCentros> scCentrosList) {
        this.scCentrosList = scCentrosList;
    }

    @XmlTransient
    public List<ScUsuarios> getScUsuariosList() {
        return scUsuariosList;
    }

    public void setScUsuariosList(List<ScUsuarios> scUsuariosList) {
        this.scUsuariosList = scUsuariosList;
    }

    @XmlTransient
    public List<DlEncamh> getDlEncamhList() {
        return dlEncamhList;
    }

    public void setDlEncamhList(List<DlEncamh> dlEncamhList) {
        this.dlEncamhList = dlEncamhList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paisId != null ? paisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScPaises)) {
            return false;
        }
        ScPaises other = (ScPaises) object;
        if ((this.paisId == null && other.paisId != null) || (this.paisId != null && !this.paisId.equals(other.paisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScPaises[ paisId=" + paisId + " ]";
    }
    
}
