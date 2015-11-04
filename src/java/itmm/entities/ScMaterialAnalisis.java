package itmm.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQuery;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.OUT;
import javax.persistence.StoredProcedureParameter;

@Entity
@Table(name = "SC_MATERIALANALISIS")
@NamedQueries({
    @NamedQuery(name = "ScMaterialAnalisis.findAll", query = "SELECT s FROM ScMaterialAnalisis s"),
    @NamedQuery(name = "ScMaterialAnalisis.findMaterial", query = "SELECT s FROM ScMaterialAnalisis s WHERE s.materialId = :materialId"),
    @NamedQuery(name = "ScMaterialAnalisis.distinct", query = "select DISTINCT s.materialId from ScMaterialAnalisis s"),
    @NamedQuery(name = "ScMaterialAnalisis.countAnalisis", query = "SELECT COUNT(DISTINCT ma.analisisId.analisisId) FROM ScMaterialAnalisis ma, ScAnalisis ana WHERE ana.tpanalisisId = :tpanalisis AND ma.materialId = :material AND ana.analisisId = ma.analisisId.analisisId"),
    @NamedQuery(name = "ScMaterialAnalisis.readAnalisis", query = "SELECT ana.nickName FROM ScMaterialAnalisis ma, ScAnalisis ana WHERE ma.analisisId.analisisId = ana.analisisId AND ana.tpanalisisId = :tpanalisis AND ma.materialId = :materialid"),
    @NamedQuery(name = "ScMaterialAnalisis.readMaximo", query = "SELECT ma.maximo FROM ScMaterialAnalisis ma, ScAnalisis ana WHERE ma.analisisId.analisisId = ana.analisisId AND ana.tpanalisisId = :tpanalisis AND ma.materialId = :materialid"),
    @NamedQuery(name = "ScMaterialAnalisis.readMinimo", query = "SELECT ma.minimo FROM ScMaterialAnalisis ma, ScAnalisis ana WHERE ma.analisisId.analisisId = ana.analisisId AND ana.tpanalisisId = :tpanalisis AND ma.materialId = :materialid"),
    @NamedQuery(name = "ScMaterialAnalisis.readNoMuestras", query = "SELECT ma.nomuestras FROM ScMateriales ma WHERE ma.materialId = :materialId"),
    @NamedQuery(name = "ScMaterialAnalisis.readAnalisisWhereMaterial", query = "SELECT s FROM ScMaterialAnalisis s WHERE s.materialId= :materialid"),
    @NamedQuery(name = "ScMaterialAnalisis.existsAnalisisWhereMaterial", query = "SELECT an FROM ScAnalisis an WHERE NOT EXISTS (SELECT ma FROM ScMaterialAnalisis ma WHERE ma.analisisId.analisisId = an.analisisId AND ma.materialId= :materialid)")
})
@NamedStoredProcedureQuery(
        name = "spcount",
        procedureName = "count_analisis",
        parameters = {
            @StoredProcedureParameter(mode = IN, type = BigDecimal.class, name = "tpanalisis"),
            @StoredProcedureParameter(mode = IN, type = String.class, name = "analisisid"),
            @StoredProcedureParameter(mode = OUT, type = String.class, name = "total_s")
        })
public class ScMaterialAnalisis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "ANALISIS_ID", referencedColumnName = "ANALISIS_ID")
    @ManyToOne
    private ScAnalisis analisisId;

    @JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID")
    @ManyToOne
    private ScMateriales materialId;

    @Column(name = "MINIMO")
    private double minimo;

    @Column(name = "MAXIMO")
    private double maximo;

    public ScAnalisis getAnalisisId() {
        return analisisId;
    }

    public void setAnalisisId(ScAnalisis analisisId) {
        this.analisisId = analisisId;
    }

    public ScMateriales getMaterialId() {
        return materialId;
    }

    public void setMaterialId(ScMateriales materialId) {
        this.materialId = materialId;
    }

    public double getMinimo() {
        return minimo;
    }

    public void setMinimo(double minimo) {
        this.minimo = minimo;
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMaximo(double maximo) {
        this.maximo = maximo;
    }

}
