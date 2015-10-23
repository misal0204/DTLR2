package itmm.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "pmtGnB")
@ViewScoped
public class ParametrosGnBean implements Serializable {

    public ParametrosGnBean() {
    }

    public String irUsuarios() {
        return "listados/dt_list_user.xhtml?faces-redirect=true";
    }

    public String irMolinos() {
        return "listados/dt_list_molino.xhtml?faces-redirect=true";
    }

    public String irDeptos() {
        return "listados/dt_list_dpto.xhtml?faces-redirect=true";
    }

    public String irCentros() {
        return "listados/dt_list_centro.xhtml?faces-redirect=true";
    }

    public String irMuestras() {
        return "listados/dt_list_muestra.xhtml?faces-redirect=true";
    }

    public String irUndMedida() {
        return "listados/dt_list_undMedida.xhtml?faces-redirect=true";
    }
}
