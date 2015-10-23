package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.ScTipomuestras;
import itmm.entities.ScTpanalisis;
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

@ManagedBean(name = "muestraB")
@ViewScoped
public class TpMuestraBean implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;

    private List<ScTipomuestras> listmuestra;
    private ScTipomuestras selectedTpmuestra;
    private ScTipomuestras inTpmuestra;

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

            TypedQuery<ScTipomuestras> query
                    = em.createNamedQuery("ScTipomuestras.findAll", ScTipomuestras.class);
            List<ScTipomuestras> results = query.getResultList();

            /*
             for (ScTipomuestras u : results) {
             System.out.println(u.getNombre());
             }*/
        } catch (PersistenceException pe) {
            System.out.println("Mensaje error tpmuestras bean: " + pe.getMessage());
            System.out.println("Error tpmuestras DBConnection: " + pe.getMessage());
            em.close();
            emf.close();
        }
        em.getTransaction().commit();
        em.close();
    }

    public TpMuestraBean() {
    }

    public void insertMuestra() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScTipomuestras scmuestras = new ScTipomuestras();

        scmuestras.setMuestraId(inTpmuestra.getMuestraId());
        scmuestras.setNombre(inTpmuestra.getNombre());

        em.persist(scmuestras);
        em.getTransaction().begin();
        em.close();
    }

    public void updateMuestra() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScTipomuestras scmuestras = em.find(ScTipomuestras.class, inTpmuestra.getMuestraId());

        scmuestras.setMuestraId(inTpmuestra.getMuestraId());
        scmuestras.setNombre(inTpmuestra.getNombre());

        em.persist(scmuestras);
        em.getTransaction().begin();
        em.close();
    }

    public void deleteMuestra() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScTipomuestras scmuestras = em.find(ScTipomuestras.class, inTpmuestra.getMuestraId());

        em.remove(scmuestras);
        em.getTransaction().begin();
        em.close();
    }
}
