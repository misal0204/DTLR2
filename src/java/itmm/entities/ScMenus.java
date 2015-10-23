/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Misael
 */
@Entity
@Table(name = "SC_MENUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScMenus.findAll", query = "SELECT s FROM ScMenus s"),
    @NamedQuery(name = "ScMenus.findByMenuId", query = "SELECT s FROM ScMenus s WHERE s.menuId = :menuId"),
    @NamedQuery(name = "ScMenus.findByNombre", query = "SELECT s FROM ScMenus s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "ScMenus.findByModulo", query = "SELECT s FROM ScMenus s WHERE s.moduloId = :moduloId")})
public class ScMenus implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MENU_ID")
    private BigDecimal menuId;
    @Size(max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "menuId")
    private List<ScFormas> scFormasList;
    @JoinColumn(name = "MODULO_ID", referencedColumnName = "MODULO_ID")
    @ManyToOne
    private ScModulos moduloId;

    public ScMenus() {
    }

    public ScMenus(BigDecimal menuId) {
        this.menuId = menuId;
    }

    public BigDecimal getMenuId() {
        return menuId;
    }

    public void setMenuId(BigDecimal menuId) {
        this.menuId = menuId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ScFormas> getScFormasList() {
        return scFormasList;
    }

    public void setScFormasList(List<ScFormas> scFormasList) {
        this.scFormasList = scFormasList;
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
        hash += (menuId != null ? menuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMenus)) {
            return false;
        }
        ScMenus other = (ScMenus) object;
        if ((this.menuId == null && other.menuId != null) || (this.menuId != null && !this.menuId.equals(other.menuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScMenus[ menuId=" + menuId + " ]";
    }

}
