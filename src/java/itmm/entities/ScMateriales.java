/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "SC_MATERIALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScMateriales.findAll", query = "SELECT s FROM ScMateriales s"),
    @NamedQuery(name = "ScMateriales.findByMaterialId", query = "SELECT s FROM ScMateriales s WHERE s.materialId = :materialId"),
    @NamedQuery(name = "ScMateriales.findByNombre", query = "SELECT s FROM ScMateriales s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "ScMateriales.findByFrecuencia", query = "SELECT s FROM ScMateriales s WHERE s.frecuencia = :frecuencia"),
    @NamedQuery(name = "ScMateriales.findByNomuestras", query = "SELECT s FROM ScMateriales s WHERE s.nomuestras = :nomuestras"),
    @NamedQuery(name = "ScMateriales.findByIntervalo", query = "SELECT s FROM ScMateriales s WHERE s.intervalo = :intervalo"),
    @NamedQuery(name = "ScMateriales.findByHinicio", query = "SELECT s FROM ScMateriales s WHERE s.hinicio = :hinicio")})
public class ScMateriales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "MATERIAL_ID")
    private String materialId;
    @Size(max = 40)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "FRECUENCIA")
    private Character frecuencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOMUESTRAS")
    private BigInteger nomuestras;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERVALO")
    private BigInteger intervalo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HINICIO")
    private BigInteger hinicio;
    @ManyToMany(mappedBy = "scMaterialesList")
    private List<ScTipomuestras> scTipomuestrasList;
    @ManyToMany(mappedBy = "scMaterialesList")
    private List<ScCentros> scCentrosList;
    @OneToMany(mappedBy = "materialId")
    private List<DlEncamh> dlEncamhList;
    @JoinColumn(name = "TIPOMATERIAL_ID", referencedColumnName = "TIPOMATERIAL_ID")
    @ManyToOne(optional = false)
    private ScTipomateriales tipomaterialId;
    @JoinColumn(name = "UNIDAD_ID", referencedColumnName = "UNIDAD_ID")
    @ManyToOne(optional = false)
    private ScUndmedidas unidadId;

    public ScMateriales() {
    }

    public ScMateriales(String materialId) {
        this.materialId = materialId;
    }

    public ScMateriales(String materialId, BigInteger nomuestras, BigInteger intervalo, BigInteger hinicio) {
        this.materialId = materialId;
        this.nomuestras = nomuestras;
        this.intervalo = intervalo;
        this.hinicio = hinicio;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Character frecuencia) {
        this.frecuencia = frecuencia;
    }

    public BigInteger getNomuestras() {
        return nomuestras;
    }

    public void setNomuestras(BigInteger nomuestras) {
        this.nomuestras = nomuestras;
    }

    public BigInteger getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(BigInteger intervalo) {
        this.intervalo = intervalo;
    }

    public BigInteger getHinicio() {
        return hinicio;
    }

    public void setHinicio(BigInteger hinicio) {
        this.hinicio = hinicio;
    }

    @XmlTransient
    public List<ScTipomuestras> getScTipomuestrasList() {
        return scTipomuestrasList;
    }

    public void setScTipomuestrasList(List<ScTipomuestras> scTipomuestrasList) {
        this.scTipomuestrasList = scTipomuestrasList;
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

    public ScTipomateriales getTipomaterialId() {
        return tipomaterialId;
    }

    public void setTipomaterialId(ScTipomateriales tipomaterialId) {
        this.tipomaterialId = tipomaterialId;
    }

    public ScUndmedidas getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(ScUndmedidas unidadId) {
        this.unidadId = unidadId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialId != null ? materialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMateriales)) {
            return false;
        }
        ScMateriales other = (ScMateriales) object;
        if ((this.materialId == null && other.materialId != null) || (this.materialId != null && !this.materialId.equals(other.materialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScMateriales[ materialId=" + materialId + " ]";
    }
    
}
