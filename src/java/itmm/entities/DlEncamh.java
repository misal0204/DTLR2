/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Misael
 */
@Entity
@Table(name = "DL_ENCAMH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DlEncamh.findAll", query = "SELECT d FROM DlEncamh d"),
    @NamedQuery(name = "DlEncamh.findByDocumento", query = "SELECT d FROM DlEncamh d WHERE d.documento = :documento"),
    @NamedQuery(name = "DlEncamh.findByLoteId", query = "SELECT d FROM DlEncamh d WHERE d.loteId = :loteId"),
    @NamedQuery(name = "DlEncamh.findByFecha", query = "SELECT d FROM DlEncamh d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "DlEncamh.findByFechaProd", query = "SELECT d FROM DlEncamh d WHERE d.fechaProd = :fechaProd")})
public class DlEncamh implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "DOCUMENTO")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "LOTE_ID")
    private String loteId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PROD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProd;
    @JoinColumn(name = "CENTRO_ID", referencedColumnName = "CENTRO_ID")
    @ManyToOne(optional = false)
    private ScCentros centroId;
    @JoinColumn(name = "ESTADO", referencedColumnName = "ESTADO_ID")
    @ManyToOne
    private ScEstados estado;
    @JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID")
    @ManyToOne
    private ScMateriales materialId;
    @JoinColumn(name = "MOLINO_ID", referencedColumnName = "MOLINO_ID")
    @ManyToOne(optional = false)
    private ScMolinos molinoId;
    @JoinColumn(name = "PAIS_ID", referencedColumnName = "PAIS_ID")
    @ManyToOne(optional = false)
    private ScPaises paisId;
    @JoinColumn(name = "MUESTRA_ID", referencedColumnName = "MUESTRA_ID")
    @ManyToOne
    private ScTipomuestras muestraId;

    public DlEncamh() {
    }

    public DlEncamh(String documento) {
        this.documento = documento;
    }

    public DlEncamh(String documento, String loteId, Date fecha, Date fechaProd) {
        this.documento = documento;
        this.loteId = loteId;
        this.fecha = fecha;
        this.fechaProd = fechaProd;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getLoteId() {
        return loteId;
    }

    public void setLoteId(String loteId) {
        this.loteId = loteId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaProd() {
        return fechaProd;
    }

    public void setFechaProd(Date fechaProd) {
        this.fechaProd = fechaProd;
    }

    public ScCentros getCentroId() {
        return centroId;
    }

    public void setCentroId(ScCentros centroId) {
        this.centroId = centroId;
    }

    public ScEstados getEstado() {
        return estado;
    }

    public void setEstado(ScEstados estado) {
        this.estado = estado;
    }

    public ScMateriales getMaterialId() {
        return materialId;
    }

    public void setMaterialId(ScMateriales materialId) {
        this.materialId = materialId;
    }

    public ScMolinos getMolinoId() {
        return molinoId;
    }

    public void setMolinoId(ScMolinos molinoId) {
        this.molinoId = molinoId;
    }

    public ScPaises getPaisId() {
        return paisId;
    }

    public void setPaisId(ScPaises paisId) {
        this.paisId = paisId;
    }

    public ScTipomuestras getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(ScTipomuestras muestraId) {
        this.muestraId = muestraId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documento != null ? documento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DlEncamh)) {
            return false;
        }
        DlEncamh other = (DlEncamh) object;
        if ((this.documento == null && other.documento != null) || (this.documento != null && !this.documento.equals(other.documento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.DlEncamh[ documento=" + documento + " ]";
    }
    
}
