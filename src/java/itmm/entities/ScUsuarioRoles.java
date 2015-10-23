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
@Table(name = "SC_USUARIOROLES")
@NamedQueries({
    @NamedQuery(name = "ScUsuarioRoles.findAll", query = "SELECT s FROM ScUsuarioRoles s")
})
public class ScUsuarioRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne
    private ScUsuarios usuarioId;

    @JoinColumn(name = "ROL_ID", referencedColumnName = "ROL_ID")
    @ManyToOne
    private ScRoles rolId;

    @Column(name = "FECHA_INICIO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "FECHA_FIN")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;

    public ScUsuarios getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(ScUsuarios usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ScRoles getRolId() {
        return rolId;
    }

    public void setRolId(ScRoles rolId) {
        this.rolId = rolId;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

}
