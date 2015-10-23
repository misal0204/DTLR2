package itmm.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "SC_ROLFORMAS")
@NamedQueries({
    @NamedQuery(name = "ScRolFormas.findAll", query = "SELECT s FROM ScRolFormas s")
})

public class ScRolFormas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "ROL_ID", referencedColumnName = "ROL_ID")
    @ManyToOne
    private ScRoles rolId;

    @JoinColumn(name = "MODULO_ID", referencedColumnName = "MODULO_ID")
    @ManyToOne
    private ScModulos moduloId;

    @JoinColumn(name = "MENU_ID", referencedColumnName = "MENU_ID")
    @ManyToOne
    private ScMenus menuId;

    @JoinColumn(name = "FORMA_ID", referencedColumnName = "FORMA_ID")
    @ManyToOne
    private ScFormas formaId;

    public ScRoles getRolId() {
        return rolId;
    }

    public void setRolId(ScRoles rolId) {
        this.rolId = rolId;
    }

    public ScModulos getModuloId() {
        return moduloId;
    }

    public void setModuloId(ScModulos moduloId) {
        this.moduloId = moduloId;
    }

    public ScMenus getMenuId() {
        return menuId;
    }

    public void setMenuId(ScMenus menuId) {
        this.menuId = menuId;
    }

    public ScFormas getFormaId() {
        return formaId;
    }

    public void setFormaId(ScFormas formaId) {
        this.formaId = formaId;
    }

}
