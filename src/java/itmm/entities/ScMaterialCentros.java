package itmm.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Id;

@Entity
@Table(name = "SC_MATERIALCENTROS")
@NamedQueries(
        {
            @NamedQuery(name = "ScMaterialCentros.findAll", query = "SELECT s FROM ScMaterialCentros s")
        })
public class ScMaterialCentros implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "CENTRO_ID", referencedColumnName = "CENTRO_ID")
    @ManyToOne
    private ScCentros centroId;

    @JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID")
    @ManyToOne
    private ScMateriales materialId;

    public ScCentros getCentroId() {
        return centroId;
    }

    public void setCentroId(ScCentros centroId) {
        this.centroId = centroId;
    }

    public ScMateriales getMaterialId() {
        return materialId;
    }

    public void setMaterialId(ScMateriales materialId) {
        this.materialId = materialId;
    }

}
