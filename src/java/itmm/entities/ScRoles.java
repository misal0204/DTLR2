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
@Table(name = "SC_ROLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScRoles.findAll", query = "SELECT s FROM ScRoles s"),
    @NamedQuery(name = "ScRoles.findByRolId", query = "SELECT s FROM ScRoles s WHERE s.rolId = :rolId"),
    @NamedQuery(name = "ScRoles.findByNombre", query = "SELECT s FROM ScRoles s WHERE s.nombre = :nombre")})
public class ScRoles implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROL_ID")
    private BigDecimal rolId;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;

    public ScRoles() {
    }

    public ScRoles(BigDecimal rolId) {
        this.rolId = rolId;
    }

    public BigDecimal getRolId() {
        return rolId;
    }

    public void setRolId(BigDecimal rolId) {
        this.rolId = rolId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScRoles)) {
            return false;
        }
        ScRoles other = (ScRoles) object;
        if ((this.rolId == null && other.rolId != null) || (this.rolId != null && !this.rolId.equals(other.rolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScRoles[ rolId=" + rolId + " ]";
    }
    
}
