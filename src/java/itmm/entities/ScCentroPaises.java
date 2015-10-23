package itmm.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="SC_CENTROPAISES")
@NamedQueries({
    @NamedQuery(name="ScCentroPaises.findAll",query="SELECT s FROM ScCentroPaises s")
})
public class ScCentroPaises implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @JoinColumn(name="CENTRO_ID",referencedColumnName="CENTRO_ID")
    @ManyToOne
    private ScCentros centroId;
    
    @JoinColumn(name="PAIS_ID",referencedColumnName="PAIS_ID")
    @ManyToOne
    private ScPaises paisId;

    public ScCentros getCentroId() {
        return centroId;
    }

    public void setCentroId(ScCentros centroId) {
        this.centroId = centroId;
    }

    public ScPaises getPaisId() {
        return paisId;
    }

    public void setPaisId(ScPaises paisId) {
        this.paisId = paisId;
    }    
}
