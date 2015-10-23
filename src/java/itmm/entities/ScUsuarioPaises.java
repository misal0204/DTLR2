package itmm.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@Table(name = "SC_USUARIOPAISES")
@NamedQueries({
    @NamedQuery(name = "ScUsuarioPaises.findAll", query = "SELECT s FROM ScUsuarioPaises s")
})
public class ScUsuarioPaises implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne
    private ScUsuarios usuarioId;

    @JoinColumn(name = "PAIS_ID", referencedColumnName = "PAIS_ID")
    @ManyToOne
    private ScPaises paidId;

    public ScUsuarios getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(ScUsuarios usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ScPaises getPaidId() {
        return paidId;
    }

    public void setPaidId(ScPaises paidId) {
        this.paidId = paidId;
    }

}
