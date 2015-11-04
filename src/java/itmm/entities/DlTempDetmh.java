/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "DL_TEMPDETMH")
@NamedQueries({
    @NamedQuery(name = "DlTempDetmh.findAll", query = "SELECT d FROM DlTempDetmh d"),
    @NamedQuery(name = "DlTempDetmh.findDoc", query = "SELECT d FROM DlTempDetmh d WHERE d.documento=:doc"),
    @NamedQuery(name = "DlTempDetmh.analisisByTp", query = "SELECT dtd.valor FROM DlTempDetmh dtd, ScAnalisis an WHERE dtd.analisisId.analisisId = an.analisisId AND an.tpanalisisId= :tpanalisis AND dtd.documento= :doc AND dtd.nmuestra= :muestra"),
    @NamedQuery(name = "DlTempDetmh.analisisByTpF", query = "SELECT dtd.valor FROM DlTempDetmh dtd, ScAnalisis an WHERE dtd.analisisId.analisisId = an.analisisId AND an.tpanalisisId= :tpanalisis AND dtd.documento= :doc AND dtd.nmuestra= :muestra AND dtd.fecha= :fecha"),
    @NamedQuery(name = "DlTempDetmh.countByTp", query = "SELECT COUNT(dtd.valor) FROM DlTempDetmh dtd, ScAnalisis an WHERE dtd.analisisId.analisisId = an.analisisId AND an.tpanalisisId.tpanalisisId = :tpanalisis AND dtd.documento.documento = :doc AND dtd.nmuestra= :muestra"),
    @NamedQuery(name = "DlTempDetmh.findDate", query = "SELECT DISTINCT d.fecha FROM DlTempDetmh d ORDER BY d.fecha"),
})
public class DlTempDetmh implements Serializable {

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

    @Column(name = "NMUESTRA")
    private double nmuestra;

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

    public double getNmuestra() {
        return nmuestra;
    }

    public void setNmuestra(double nmuestra) {
        this.nmuestra = nmuestra;
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

    public int getAnalisis() {
        return 2;
    }
}
