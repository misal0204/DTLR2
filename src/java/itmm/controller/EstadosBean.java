/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.ScEstados;
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
@ManagedBean
@ViewScoped
public class EstadosBean implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;

    private List<ScEstados> listEstados;
    private ScEstados selectedEstados;
    private ScEstados inEstados;

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

            TypedQuery<ScEstados> query
                    = em.createNamedQuery("ScEstados.findAll", ScEstados.class);
            List<ScEstados> results = query.getResultList();

            /*
             for (ScEstados e : results) {
             System.out.println(e.getNombre());
             }*/
        } catch (PersistenceException pe) {
            System.out.println("Mensaje error estados bean: " + pe.getMessage());
            System.out.println("Error estados DBConnection: " + pe.getMessage());
            em.close();
            emf.close();
        }
        em.getTransaction().commit();
        em.close();
    }

    public EstadosBean() {
    }

    public void insertEstados() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScEstados estados = new ScEstados();

        estados.setEstadoId(inEstados.getEstadoId());
        estados.setEstado(inEstados.getEstado());
        estados.setDescripcion(inEstados.getDescripcion());

        em.persist(estados);
        em.getTransaction().commit();
        em.close();
    }

    public void updateEstados() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScEstados estados = em.find(ScEstados.class, inEstados.getEstadoId());
        estados.setEstado(inEstados.getEstado());
        estados.setDescripcion(inEstados.getDescripcion());

        em.persist(estados);
        em.getTransaction().commit();
        em.close();
    }
    
    public void deleteEstados()
    {
     em= emf.createEntityManager();
     em.getTransaction().begin();
     
     ScEstados estados= em.find(ScEstados.class, inEstados.getEstadoId());
     
     em.remove(estados);
     
    }

    public List<ScEstados> getListEstados() {
        return listEstados;
    }

    public ScEstados getSelectedEstados() {
        return selectedEstados;
    }

    public void setSelectedEstados(ScEstados selectedEstados) {
        this.selectedEstados = selectedEstados;
    }

    public ScEstados getInEstados() {
        return inEstados;
    }

    public void setInEstados(ScEstados inEstados) {
        this.inEstados = inEstados;
    }

    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }
    
}
