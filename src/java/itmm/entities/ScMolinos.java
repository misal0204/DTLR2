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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "SC_MOLINOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScMolinos.findAll", query = "SELECT s FROM ScMolinos s"),
    @NamedQuery(name = "ScMolinos.findByMolinoId", query = "SELECT s FROM ScMolinos s WHERE s.molinoId = :molinoId"),
    @NamedQuery(name = "ScMolinos.findByNombre", query = "SELECT s FROM ScMolinos s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "ScMolinos.findByDescripcion", query = "SELECT s FROM ScMolinos s WHERE s.descripcion = :descripcion")})
public class ScMolinos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "MOLINO_ID")
    private String molinoId;
    @Size(max = 40)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinTable(name = "SC_MOLINOCENTROS", joinColumns = {
        @JoinColumn(name = "MOLINO_ID", referencedColumnName = "MOLINO_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "CENTRO_ID", referencedColumnName = "CENTRO_ID")})
    @ManyToMany
    private List<ScCentros> scCentrosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "molinoId")
    private List<DlEncamh> dlEncamhList;

    public ScMolinos() {
    }

    public ScMolinos(String molinoId) {
        this.molinoId = molinoId;
    }

    public String getMolinoId() {
        return molinoId;
    }

    public void setMolinoId(String molinoId) {
        this.molinoId = molinoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<ScCentros> getScCentrosList() {
        return scCentrosList;
    }

    public void setScCentrosList(List<ScCentros> scCentrosList) {
        this.scCentrosList = scCentrosList;
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
        hash += (molinoId != null ? molinoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMolinos)) {
            return false;
        }
        ScMolinos other = (ScMolinos) object;
        if ((this.molinoId == null && other.molinoId != null) || (this.molinoId != null && !this.molinoId.equals(other.molinoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScMolinos[ molinoId=" + molinoId + " ]";
    }
    
}
