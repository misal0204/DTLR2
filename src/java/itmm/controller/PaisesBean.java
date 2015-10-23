/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.ScPaises;
import itmm.entities.ScTpanalisis;
import itmm.util.MyUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Misael
 */
@ManagedBean(name = "paisesB")
@SessionScoped
public class PaisesBean implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;

    private List<ScPaises> listPaises;
    private ScPaises selectedPaises;
    private ScPaises inPaises;

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

            TypedQuery<ScPaises> query
                    = em.createNamedQuery("ScPaises.findAll", ScPaises.class);
            List<ScPaises> results = query.getResultList();

            /*
             for (ScPaises p : results) {
             System.out.println(p.getNombre());
             }*/
        } catch (PersistenceException pe) {
            System.out.println("Mensaje error paises bean: " + pe.getMessage());
            System.out.println("Error paises DBConnection: " + pe.getMessage());
            em.close();
            emf.close();
        }
        em.getTransaction().commit();
        em.close();
    }

    public PaisesBean() {
    }
    
    public void insertPaises()
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        ScPaises scpaises= new ScPaises();
        
        scpaises.setPaisId(inPaises.getPaisId());
        scpaises.setNombre(inPaises.getNombre());
        
        em.persist(scpaises);
        em.getTransaction().commit();
        em.close();
    }
    
    public void updatePaises()
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        ScPaises paises= em.find(ScPaises.class, inPaises.getPaisId());
        
        paises.setNombre(inPaises.getNombre());
        
        em.persist(paises);
        em.getTransaction().commit();
        em.close();
    }
    
    public void deletePaises()
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        ScPaises paises = em.find(ScPaises.class, inPaises.getPaisId());
        
        em.remove(paises);
        em.getTransaction().commit();
        em.close();
    }
}
