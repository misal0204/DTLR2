/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Misael
 */
@Entity
@Table(name = "SC_USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScUsuarios.findAll", query = "SELECT s FROM ScUsuarios s"),
    @NamedQuery(name = "ScUsuarios.findByUsuarioId", query = "SELECT s FROM ScUsuarios s WHERE s.usuarioId = :usuarioId"),
    @NamedQuery(name = "ScUsuarios.findByNombre", query = "SELECT s FROM ScUsuarios s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "ScUsuarios.findByCorreo", query = "SELECT s FROM ScUsuarios s WHERE s.correo = :correo"),
    @NamedQuery(name = "ScUsuarios.findByExtension", query = "SELECT s FROM ScUsuarios s WHERE s.extension = :extension"),
    @NamedQuery(name = "ScUsuarios.findByTelefono", query = "SELECT s FROM ScUsuarios s WHERE s.telefono = :telefono"),
    @NamedQuery(name = "ScUsuarios.findByCargo", query = "SELECT s FROM ScUsuarios s WHERE s.cargo = :cargo"),
    @NamedQuery(name = "ScUsuarios.findByTipo", query = "SELECT s FROM ScUsuarios s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "ScUsuarios.findByFechaUltLogon", query = "SELECT s FROM ScUsuarios s WHERE s.fechaUltLogon = :fechaUltLogon"),
    @NamedQuery(name = "ScUsuarios.findByFechaCamCla", query = "SELECT s FROM ScUsuarios s WHERE s.fechaCamCla = :fechaCamCla"),
    @NamedQuery(name = "ScUsuarios.findByVigenciaCla", query = "SELECT s FROM ScUsuarios s WHERE s.vigenciaCla = :vigenciaCla"),
    @NamedQuery(name = "ScUsuarios.findByUsuarioSap", query = "SELECT s FROM ScUsuarios s WHERE s.usuarioSap = :usuarioSap")})
public class ScUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "USUARIO_ID")
    private String usuarioId;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 30)
    @Column(name = "CORREO")
    private String correo;
    @Size(max = 5)
    @Column(name = "EXTENSION")
    private String extension;
    @Size(max = 15)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 60)
    @Column(name = "CARGO")
    private String cargo;
    @Column(name = "TIPO")
    private Character tipo;
    @Column(name = "FECHA_ULT_LOGON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltLogon;
    @Column(name = "FECHA_CAM_CLA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCamCla;
    @Column(name = "VIGENCIA_CLA")
    private BigInteger vigenciaCla;
    @Size(max = 20)
    @Column(name = "USUARIO_SAP")
    private String usuarioSap;
    @JoinTable(name = "SC_USUARIOPAISES", joinColumns = {
        @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PAIS_ID", referencedColumnName = "PAIS_ID")})
    @ManyToMany
    private List<ScPaises> scPaisesList;
    @JoinColumn(name = "DEPTO_ID", referencedColumnName = "DEPTO_ID")
    @ManyToOne
    private ScDeptos deptoId;
    @JoinColumn(name = "ESTADO", referencedColumnName = "ESTADO_ID")
    @ManyToOne
    private ScEstados estado;

    public ScUsuarios() {
    }

    public ScUsuarios(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public Date getFechaUltLogon() {
        return fechaUltLogon;
    }

    public void setFechaUltLogon(Date fechaUltLogon) {
        this.fechaUltLogon = fechaUltLogon;
    }

    public Date getFechaCamCla() {
        return fechaCamCla;
    }

    public void setFechaCamCla(Date fechaCamCla) {
        this.fechaCamCla = fechaCamCla;
    }

    public BigInteger getVigenciaCla() {
        return vigenciaCla;
    }

    public void setVigenciaCla(BigInteger vigenciaCla) {
        this.vigenciaCla = vigenciaCla;
    }

    public String getUsuarioSap() {
        return usuarioSap;
    }

    public void setUsuarioSap(String usuarioSap) {
        this.usuarioSap = usuarioSap;
    }

    @XmlTransient
    public List<ScPaises> getScPaisesList() {
        return scPaisesList;
    }

    public void setScPaisesList(List<ScPaises> scPaisesList) {
        this.scPaisesList = scPaisesList;
    }

    public ScDeptos getDeptoId() {
        return deptoId;
    }

    public void setDeptoId(ScDeptos deptoId) {
        this.deptoId = deptoId;
    }

    public ScEstados getEstado() {
        return estado;
    }

    public void setEstado(ScEstados estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScUsuarios)) {
            return false;
        }
        ScUsuarios other = (ScUsuarios) object;
        if ((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScUsuarios[ usuarioId=" + usuarioId + " ]";
    }
    
}
