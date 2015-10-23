package itmm.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
@Table(name = "SC_PAISESAUDITA")
@NamedQueries({
    @NamedQuery(name = "ScPaisesAudita.findAll", query = "SELECT s FROM ScPaisesAudita s")
})
public class ScPaisesAudita implements Serializable {

    @Id
    @Column(name = "FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    @JoinColumn(name = "PAIS_ID", referencedColumnName = "PAIS_ID")
    @ManyToOne
    private ScPaises paisId;

    @Column(name = "ACCION")
    private String accion;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "VAL_NUEVO")
    private String val_nuevo;
    @Column(name = "VAL_ANTERIOR")
    private String val_anterior;
    @Column(name = "CAMPO")
    private String campo;
    @Column(name = "USUARIO")
    private String usuario;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ScPaises getPaisId() {
        return paisId;
    }

    public void setPaisId(ScPaises paisId) {
        this.paisId = paisId;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVal_nuevo() {
        return val_nuevo;
    }

    public void setVal_nuevo(String val_nuevo) {
        this.val_nuevo = val_nuevo;
    }

    public String getVal_anterior() {
        return val_anterior;
    }

    public void setVal_anterior(String val_anterior) {
        this.val_anterior = val_anterior;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
