package itmm.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "SC_MOLINOCENTROS")
@NamedQueries({
    @NamedQuery(name = "ScMolinoCentros.findAll", query = "SELECT s FROM ScMolinoCentros s"),
    @NamedQuery(name = "ScMolinoCentros.findByCentro", query = "SELECT s FROM ScMolinoCentros s WHERE s.centroId=:centroId"),
    @NamedQuery(name = "ScMolinoCentros.findByMolino", query = "SELECT s FROM ScMolinoCentros s WHERE s.molinoId=:molinoId")
})
public class ScMolinoCentros implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "CENTRO_ID", referencedColumnName = "CENTRO_ID")
    @ManyToOne
    private ScCentros centroId;

    @JoinColumn(name = "MOLINO_ID", referencedColumnName = "MOLINO_ID")
    @ManyToOne
    private ScMolinos molinoId;

    public ScCentros getCentroId() {
        return centroId;
    }

    public void setCentroId(ScCentros centroId) {
        this.centroId = centroId;
    }

    public ScMolinos getMolinoId() {
        return molinoId;
    }

    public void setMolinoId(ScMolinos molinoId) {
        this.molinoId = molinoId;
    }

}
