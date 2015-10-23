package itmm.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "matAnBean")
@ViewScoped
public class MaterialAnalisisBean implements Serializable{

    private String idMaterial;
    
    public MaterialAnalisisBean() {
    }

    public String getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(String idMaterial) {
        this.idMaterial = idMaterial;
    }
}
