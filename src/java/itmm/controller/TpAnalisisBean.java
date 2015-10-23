package itmm.controller;

import itmm.database.DBConnection;
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

@ManagedBean(name = "tpAnalisisB")
@SessionScoped
public class TpAnalisisBean implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;

    private List<ScTpanalisis> listTpanalisis;
    private ScTpanalisis selectedTpAnalisis;
    private ScTpanalisis inTpAnalisis;

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

            TypedQuery<ScTpanalisis> query
                    = em.createNamedQuery("ScTpanalisis.findAll", ScTpanalisis.class);
            List<ScTpanalisis> results = query.getResultList();

            /*
            for (ScTpanalisis u : results) {
                System.out.println(u.getNombre());
            }*/

        } catch (PersistenceException pe) {
            System.out.println("Mensaje error tpanalisis bean: " + pe.getMessage());
            System.out.println("Error tpanalisis DBConnection: " + pe.getMessage());
            em.close();
            emf.close();
        }
        em.getTransaction().commit();
        em.close();
    }

    public TpAnalisisBean() {
    }
    
    public void insertTpAnalisis()
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        ScTpanalisis tpanalisis= new ScTpanalisis();
        tpanalisis.setNombre(inTpAnalisis.getNombre());
        
        em.persist(tpanalisis);
        em.getTransaction().commit();
        em.close();        
    }
    
    public void updateTpAnalisis()
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        ScTpanalisis tpanalisis=em.find(ScTpanalisis.class, inTpAnalisis.getTpanalisisId());
        tpanalisis.setNombre(inTpAnalisis.getNombre());
        
        em.persist(tpanalisis);
        em.getTransaction().commit();
        em.close();    
    }
    
    public void deleteTpAnalisis()
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        ScTpanalisis sctpanalisis=em.find(ScTpanalisis.class, inTpAnalisis.getTpanalisisId());
        em.remove(sctpanalisis);
        em.getTransaction().commit();
        em.close();
    }

    public List<ScTpanalisis> getListTpanalisis() {
        return listTpanalisis;
    }

    public ScTpanalisis getSelectedTpAnalisis() {
        return selectedTpAnalisis;
    }

    public void setSelectedTpAnalisis(ScTpanalisis selectedTpAnalisis) {
        this.selectedTpAnalisis = selectedTpAnalisis;
    }

    public ScTpanalisis getInTpAnalisis() {
        return inTpAnalisis;
    }

    public void setInTpAnalisis(ScTpanalisis inTpAnalisis) {
        this.inTpAnalisis = inTpAnalisis;
    }

    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }
    
    
}
