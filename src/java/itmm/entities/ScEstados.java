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
@Table(name = "SC_ESTADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScEstados.findAll", query = "SELECT s FROM ScEstados s"),
    @NamedQuery(name = "ScEstados.findByEstadoId", query = "SELECT s FROM ScEstados s WHERE s.estadoId = :estadoId"),
    @NamedQuery(name = "ScEstados.findByEstado", query = "SELECT s FROM ScEstados s WHERE s.estado = :estado"),
    @NamedQuery(name = "ScEstados.findByDescripcion", query = "SELECT s FROM ScEstados s WHERE s.descripcion = :descripcion")})
public class ScEstados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO_ID")
    private String estadoId;
    @Size(max = 20)
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 75)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "estado")
    private List<DlEncamh> dlEncamhList;
    @OneToMany(mappedBy = "estado")
    private List<ScUsuarios> scUsuariosList;

    public ScEstados() {
    }

    public ScEstados(String estadoId) {
        this.estadoId = estadoId;
    }

    public String getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(String estadoId) {
        this.estadoId = estadoId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<DlEncamh> getDlEncamhList() {
        return dlEncamhList;
    }

    public void setDlEncamhList(List<DlEncamh> dlEncamhList) {
        this.dlEncamhList = dlEncamhList;
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
        hash += (estadoId != null ? estadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScEstados)) {
            return false;
        }
        ScEstados other = (ScEstados) object;
        if ((this.estadoId == null && other.estadoId != null) || (this.estadoId != null && !this.estadoId.equals(other.estadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScEstados[ estadoId=" + estadoId + " ]";
    }
    
}
