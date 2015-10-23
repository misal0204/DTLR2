package itmm.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table (name="SC_USUARIOSAUDITA")
@NamedQueries(
{
    @NamedQuery(name="ScUsuariosAudita.findAll", query="SELECT s FROM ScUsuariosAudita s")
})
public class ScUsuariosAudita implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @Id
    @Column(name="FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @JoinColumn (name="USUARIO_ID",referencedColumnName="USUARIO_ID")
    @ManyToOne
    private  ScUsuarios usuarioId;
    
    @Column(name="ACCION")
    private String accion;
    @Column(name="DESCRIPCION")
    private String descripcion;
    @Column(name="VAL_NUEVO")
    private String val_nuevo;
    @Column(name="VAL_ANTERIOR")
    private String val_anterior;
    @Column(name="CAMPO")
    private String campo;
    @Column(name="USUARIO")
    private String usuario;
    
    public ScUsuariosAudita(){}

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ScUsuarios getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(ScUsuarios usuarioId) {
        this.usuarioId = usuarioId;
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
