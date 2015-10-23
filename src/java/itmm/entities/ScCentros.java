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
@Table(name = "SC_CENTROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScCentros.findAll", query = "SELECT s FROM ScCentros s"),
    @NamedQuery(name = "ScCentros.findByCentroId", query = "SELECT s FROM ScCentros s WHERE s.centroId = :centroId"),
    @NamedQuery(name = "ScCentros.findByNombre", query = "SELECT s FROM ScCentros s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "ScCentros.findByDescripcion", query = "SELECT s FROM ScCentros s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "ScCentros.findByDireccion", query = "SELECT s FROM ScCentros s WHERE s.direccion = :direccion")})
public class ScCentros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CENTRO_ID")
    private String centroId;
    @Size(max = 40)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @JoinTable(name = "SC_MATERIALCENTROS", joinColumns = {
        @JoinColumn(name = "CENTRO_ID", referencedColumnName = "CENTRO_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID")})
    @ManyToMany
    private List<ScMateriales> scMaterialesList;
    @JoinTable(name = "SC_CENTROPAISES", joinColumns = {
        @JoinColumn(name = "CENTRO_ID", referencedColumnName = "CENTRO_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PAIS_ID", referencedColumnName = "PAIS_ID")})
    @ManyToMany
    private List<ScPaises> scPaisesList;
    @ManyToMany(mappedBy = "scCentrosList")
    private List<ScMolinos> scMolinosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "centroId")
    private List<DlEncamh> dlEncamhList;

    public ScCentros() {
    }

    public ScCentros(String centroId) {
        this.centroId = centroId;
    }

    public String getCentroId() {
        return centroId;
    }

    public void setCentroId(String centroId) {
        this.centroId = centroId;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public List<ScMateriales> getScMaterialesList() {
        return scMaterialesList;
    }

    public void setScMaterialesList(List<ScMateriales> scMaterialesList) {
        this.scMaterialesList = scMaterialesList;
    }

    @XmlTransient
    public List<ScPaises> getScPaisesList() {
        return scPaisesList;
    }

    public void setScPaisesList(List<ScPaises> scPaisesList) {
        this.scPaisesList = scPaisesList;
    }

    @XmlTransient
    public List<ScMolinos> getScMolinosList() {
        return scMolinosList;
    }

    public void setScMolinosList(List<ScMolinos> scMolinosList) {
        this.scMolinosList = scMolinosList;
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
        hash += (centroId != null ? centroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScCentros)) {
            return false;
        }
        ScCentros other = (ScCentros) object;
        if ((this.centroId == null && other.centroId != null) || (this.centroId != null && !this.centroId.equals(other.centroId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScCentros[ centroId=" + centroId + " ]";
    }
    
}
