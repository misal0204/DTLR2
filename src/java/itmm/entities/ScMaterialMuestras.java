package itmm.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "ScMaterialMuestras")
@NamedQueries({
    @NamedQuery(name = "ScMaterialMuestras.findAll", query = "SELECT s FROM ScMaterialMuestras s")
})
public class ScMaterialMuestras implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "MUESTRA_ID", referencedColumnName = "MUESTRA_ID")
    @ManyToOne
    private ScTipomuestras muestrasId;

    @JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID")
    @ManyToOne
    private ScMateriales materialId;

    public ScTipomuestras getMuestrasId() {
        return muestrasId;
    }

    public void setMuestrasId(ScTipomuestras muestrasId) {
        this.muestrasId = muestrasId;
    }

    public ScMateriales getMaterialId() {
        return materialId;
    }

    public void setMaterialId(ScMateriales materialId) {
        this.materialId = materialId;
    }

}
