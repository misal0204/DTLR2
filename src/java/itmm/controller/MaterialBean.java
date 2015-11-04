/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.ScAnalisis;
import itmm.entities.ScMaterialAnalisis;
import itmm.entities.ScMateriales;
import itmm.entities.ScTipomateriales;
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
    private List<ScMaterialAnalisis> material_analisis;
    private List<ScAnalisis> analisis_exists;

    private ScMateriales selectedMaterial;
    private String selectedTpMaterial;
    private String selectedMateriales;
    private String selectdMat;

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

    public List<ScTipomateriales> tpMateriales() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<ScTipomateriales> query
                = em.createNamedQuery("ScTipomateriales.findAll", ScTipomateriales.class);
        List<ScTipomateriales> results = query.getResultList();

        em.close();
        return results;
    }

    public List<ScMateriales> findMaterial(String tpmaterial) {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScTipomateriales tmaterial = em.find(ScTipomateriales.class, tpmaterial);

        TypedQuery<ScMateriales> query
                = em.createNamedQuery("ScMateriales.findByMaterialTp", ScMateriales.class);
        query.setParameter("tpmaterialId", tmaterial);
        List<ScMateriales> results = query.getResultList();

        em.close();
        return results;
    }

    public List<ScMaterialAnalisis> findAnalisis(String material) {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScMateriales tmaterial = em.find(ScMateriales.class, material);

        TypedQuery<ScMaterialAnalisis> query
                = em.createNamedQuery("ScMaterialAnalisis.findByMaterialTp", ScMaterialAnalisis.class);
        query.setParameter("tpmaterialId", tmaterial);
        List<ScMaterialAnalisis> results = query.getResultList();

        em.close();
        return results;
    }

    public List<ScMaterialAnalisis> findAnalisisWithMaterial() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScMateriales tmaterial = em.find(ScMateriales.class, selectedMateriales);

        TypedQuery<ScMaterialAnalisis> query
                = em.createNamedQuery("ScMaterialAnalisis.readAnalisisWhereMaterial", ScMaterialAnalisis.class);
        query.setParameter("materialid", tmaterial);
        List<ScMaterialAnalisis> results = query.getResultList();
        material_analisis = results;
        /*
         for (ScMaterialAnalisis ma : results) {
         System.out.println("analisis: " + ma.getAnalisisId().getNombre());
         }*/
        em.close();
        return results;
    }

    public List<ScAnalisis> existsAnalisisWithMaterial() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScMateriales tmaterial = em.find(ScMateriales.class, selectedMateriales);

        TypedQuery<ScAnalisis> query
                = em.createNamedQuery("ScMaterialAnalisis.existsAnalisisWhereMaterial", ScAnalisis.class);
        query.setParameter("materialid", tmaterial);
        List<ScAnalisis> results = query.getResultList();
        analisis_exists = results;

        for (ScAnalisis ma : results) {
            System.out.println("analisis: " + ma.getNombre());
        }
        em.close();
        return results;
    }

    public String crearMaterial() {
        return "crud/materialCrud.xhtml?faces-redirect=true";
    }

    public String asginar_AnalisisMaterial() {
        return "crud/asignar_analisis_material.xhtml?faces-redirect=true";
    }

    public String verAnalisis() {

        return "listados/dt_asignar_anamat.xhtml?faces-redirect=true";
    }

    public String findTipoMaterial(String a) {
        return "";
    }

    public List<ScAnalisis> getAnalisis_exists() {
        return analisis_exists;
    }

    public String getSelectdMat() {
        return selectdMat;
    }

    public void setSelectdMat(String selectdMat) {
        this.selectdMat = selectdMat;
    }

    public String getSelectedMateriales() {
        return selectedMateriales;
    }

    public void setSelectedMateriales(String selectedMateriales) {
        this.selectedMateriales = selectedMateriales;
    }

    public String getSelectedTpMaterial() {
        return selectedTpMaterial;
    }

    public void setSelectedTpMaterial(String selectedTpMaterial) {
        this.selectedTpMaterial = selectedTpMaterial;
    }

    public ScMateriales getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(ScMateriales selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }

    public List<ScMaterialAnalisis> getMaterial_analisis() {
        return material_analisis;
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
