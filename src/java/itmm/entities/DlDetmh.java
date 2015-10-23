package itmm.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Temporal;

@Entity
@Table(name = "DL_DETMH")
@NamedQueries({
    @NamedQuery(name = "DlDetmh.findAll", query = "SELECT d FROM DlDetmh d")
})
public class DlDetmh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "DOCUMENTO", referencedColumnName = "DOCUMENTO")
    @ManyToOne
    private DlEncamh documento;

    @Column(name = "FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    @Column(name = "HORA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date hora;

    @Column(name = "NMUESTRA_ID")
    private String nmuestraId;

    @JoinColumn(name = "ANALISIS_ID", referencedColumnName = "ANALISIS_ID")
    @ManyToOne
    private ScAnalisis analisisId;

    @Column(name = "VALOR")
    private double valor;

    @Column(name = "MINIMO")
    private double minimo;

    @Column(name = "MAXIMO")
    private double maximo;

    public DlEncamh getDocumento() {
        return documento;
    }

    public void setDocumento(DlEncamh documento) {
        this.documento = documento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getNmuestraId() {
        return nmuestraId;
    }

    public void setNmuestraId(String nmuestraId) {
        this.nmuestraId = nmuestraId;
    }

    public ScAnalisis getAnalisisId() {
        return analisisId;
    }

    public void setAnalisisId(ScAnalisis analisisId) {
        this.analisisId = analisisId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
