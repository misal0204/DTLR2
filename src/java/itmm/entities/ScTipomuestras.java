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
@Table(name = "SC_TIPOMUESTRAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScTipomuestras.findAll", query = "SELECT s FROM ScTipomuestras s"),
    @NamedQuery(name = "ScTipomuestras.findByMuestraId", query = "SELECT s FROM ScTipomuestras s WHERE s.muestraId = :muestraId"),
    @NamedQuery(name = "ScTipomuestras.findByNombre", query = "SELECT s FROM ScTipomuestras s WHERE s.nombre = :nombre")})
public class ScTipomuestras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "MUESTRA_ID")
    private String muestraId;
    @Size(max = 40)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinTable(name = "SC_MATERIALMUESTRAS", joinColumns = {
        @JoinColumn(name = "MUESTRA_ID", referencedColumnName = "MUESTRA_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID")})
    @ManyToMany
    private List<ScMateriales> scMaterialesList;
    @OneToMany(mappedBy = "muestraId")
    private List<DlEncamh> dlEncamhList;

    public ScTipomuestras() {
    }

    public ScTipomuestras(String muestraId) {
        this.muestraId = muestraId;
    }

    public String getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(String muestraId) {
        this.muestraId = muestraId;
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
        hash += (muestraId != null ? muestraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScTipomuestras)) {
            return false;
        }
        ScTipomuestras other = (ScTipomuestras) object;
        if ((this.muestraId == null && other.muestraId != null) || (this.muestraId != null && !this.muestraId.equals(other.muestraId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScTipomuestras[ muestraId=" + muestraId + " ]";
    }
    
}
