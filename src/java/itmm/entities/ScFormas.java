/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Misael
 */
@Entity
@Table(name = "SC_FORMAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScFormas.findAll", query = "SELECT s FROM ScFormas s"),
    @NamedQuery(name = "ScFormas.findByFormaId", query = "SELECT s FROM ScFormas s WHERE s.formaId = :formaId"),
    @NamedQuery(name = "ScFormas.findByNombre", query = "SELECT s FROM ScFormas s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "ScFormas.findByNombrePag", query = "SELECT s FROM ScFormas s WHERE s.nombrePag = :nombrePag"),
    @NamedQuery(name = "ScFormas.findByMenu", query = "SELECT s FROM ScFormas s WHERE s.menuId = :menuId")})
public class ScFormas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FORMA_ID")
    private BigDecimal formaId;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 50)
    @Column(name = "NOMBRE_PAG")
    private String nombrePag;
    @JoinColumn(name = "MENU_ID", referencedColumnName = "MENU_ID")
    @ManyToOne
    private ScMenus menuId;
    @JoinColumn(name = "MODULO_ID", referencedColumnName = "MODULO_ID")
    @ManyToOne
    private ScModulos moduloId;

    public ScFormas() {
    }

    public ScFormas(BigDecimal formaId) {
        this.formaId = formaId;
    }

    public BigDecimal getFormaId() {
        return formaId;
    }

    public void setFormaId(BigDecimal formaId) {
        this.formaId = formaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombrePag() {
        return nombrePag;
    }

    public void setNombrePag(String nombrePag) {
        this.nombrePag = nombrePag;
    }

    public ScMenus getMenuId() {
        return menuId;
    }

    public void setMenuId(ScMenus menuId) {
        this.menuId = menuId;
    }

    public ScModulos getModuloId() {
        return moduloId;
    }

    public void setModuloId(ScModulos moduloId) {
        this.moduloId = moduloId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formaId != null ? formaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScFormas)) {
            return false;
        }
        ScFormas other = (ScFormas) object;
        if ((this.formaId == null && other.formaId != null) || (this.formaId != null && !this.formaId.equals(other.formaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScFormas[ formaId=" + formaId + " ]";
    }

}
