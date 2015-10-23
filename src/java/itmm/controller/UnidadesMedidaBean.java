package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.ScTpanalisis;
import itmm.entities.ScUndmedidas;
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

@ManagedBean(name = "umB")
@ViewScoped
public class UnidadesMedidaBean implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;

    private List<ScUndmedidas> listUndMedidas;
    private ScUndmedidas selectedUndmedidas;
    private ScUndmedidas inUndmedidas;

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

            TypedQuery<ScUndmedidas> query
                    = em.createNamedQuery("ScUndmedidas.findAll", ScUndmedidas.class);
            List<ScUndmedidas> results = query.getResultList();

            /*
             for (ScUndmedidas un : results) {
             System.out.println(un.getNombre());
             }*/
        } catch (PersistenceException pe) {
            System.out.println("Mensaje error UnidadMedida bean: " + pe.getMessage());
            System.out.println("Error UnidadMedida DBConnection: " + pe.getMessage());
            em.close();
            emf.close();
        }
        em.getTransaction().commit();
        em.close();
    }

    public UnidadesMedidaBean() {
    }

    public void insertUnidadMedida() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScUndmedidas scunmedida = new ScUndmedidas();
        scunmedida.setUnidadId(inUndmedidas.getUnidadId());
        scunmedida.setNombre(inUndmedidas.getNombre());

        em.persist(scunmedida);
        em.getTransaction().commit();
        em.close();
    }

    public void updateUnidadMedida() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScUndmedidas scunmedida = em.find(ScUndmedidas.class, inUndmedidas.getUnidadId());

        scunmedida.setNombre(inUndmedidas.getNombre());

        em.persist(scunmedida);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteUnidadMedida() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScUndmedidas scunmedida = em.find(ScUndmedidas.class, inUndmedidas.getUnidadId());

        em.remove(scunmedida);
        em.getTransaction().commit();
        em.close();
    }

    public List<ScUndmedidas> getListUndMedidas() {
        return listUndMedidas;
    }
    
    public ScUndmedidas getSelectedUndmedidas() {
        return selectedUndmedidas;
    }

    public void setSelectedUndmedidas(ScUndmedidas selectedUndmedidas) {
        this.selectedUndmedidas = selectedUndmedidas;
    }

    public ScUndmedidas getInUndmedidas() {
        return inUndmedidas;
    }

    public void setInUndmedidas(ScUndmedidas inUndmedidas) {
        this.inUndmedidas = inUndmedidas;
    }

    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }
    
    
}
