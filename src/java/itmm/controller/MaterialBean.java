/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.ScMateriales;
import itmm.util.MyUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Misael
 */
@ManagedBean(name = "materialB")
@ViewScoped
public class MaterialBean implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;
    private List<ScMateriales> materiales;

    private ScMateriales selectedMaterial;
    private String schema;
    private String usuario;
    private String pass;

    @ManagedProperty("#{attBean}")
    private AuthenticationBean attb;

    @PostConstruct
    public void init() {
        schema = attb.getSchema();
        usuario = attb.getUsername();
        pass = attb.getPassword();

        String NPU;

        if (schema.equals("PRODUCTIVO")) {
            NPU = MyUtil.PERSISTENCE_UNIT_NAME_PRODUCTIVO;
        } else {
            NPU = MyUtil.PERSISTENCE_UNIT_NAME_CALIDAD;
        }

        Map property = DBConnection.Property(usuario, pass);

        try {
            emf = Persistence.createEntityManagerFactory(NPU, property);
            em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<ScMateriales> query
                    = em.createNamedQuery("ScMateriales.findAll", ScMateriales.class);
            List<ScMateriales> results = query.getResultList();

            materiales = results;
/*
            for (ScMateriales material : results) {
                System.out.println(material.getNombre());
            }
        */
        } catch (PersistenceException pe) {
            System.out.println("Mensaje error material bean: " + pe.getMessage());
            System.err.println("Error DBConnection: " + pe.getMessage());
            em.close();
            emf.close();
        }

        em.getTransaction().commit();
        em.close();
    }

    public MaterialBean() {
    }

    public String crearMaterial() {
        return "crud/materialCrud.xhtml?faces-redirect=true";
    }
    
    public String verAnalisis()
    {    
        
        return "listados/dt_asignar_anamat.xhtml?faces-redirect=true";
    }

    public String findTipoMaterial(String a) {
        return "";
    }

    public ScMateriales getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(ScMateriales selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }

    public List<ScMateriales> getMateriales() {
        return materiales;
    }

    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }
}
