package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.ScAnalisis;
import itmm.entities.ScTpanalisis;
import itmm.util.MyUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "analisisB")
@ViewScoped
public class AnalisisBean implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;

    private List<ScAnalisis> analisis;
    private List<ScTpanalisis> tpanalisis;
    private ScAnalisis selectedAnalisis;

    private ScAnalisis inAnalisis;
    private ScTpanalisis inTpAnalisis;
    private int tipoAnalisisId;

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

        inAnalisis = new ScAnalisis();
        inTpAnalisis = new ScTpanalisis();

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

            TypedQuery<ScAnalisis> query
                    = em.createNamedQuery("ScAnalisis.findAll", ScAnalisis.class);
            List<ScAnalisis> results = query.getResultList();

            analisis = results;
            tpanalisis = getTpAnalisis(em);
            /*
             for (ScAnalisis a : results) {
             System.out.println(a.getNombre());
             }*/
        } catch (PersistenceException pe) {
            System.out.println("Mensaje error analisis bean: " + pe.getMessage());
            System.out.println("Error DBConnection: " + pe.getMessage());
            em.close();
            emf.close();
        }
        em.getTransaction().commit();
        em.close();
    }

    public AnalisisBean() {
    }

    public int getTipoAnalisisId() {
        return tipoAnalisisId;
    }

    public void setTipoAnalisisId(int tipoAnalisisId) {
        this.tipoAnalisisId = tipoAnalisisId;
    }

    public List<ScTpanalisis> getTpanalisis() {
        return tpanalisis;
    }

    public List<ScTpanalisis> getTpAnalisis(EntityManager em) {
        TypedQuery<ScTpanalisis> query
                = em.createNamedQuery("ScTpanalisis.findAll", ScTpanalisis.class);
        List<ScTpanalisis> results = query.getResultList();

        return results;
    }

    public String insertAnalisis() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            // Verificamos si el registro no existe, si existe
            //aparecera un mensaje en pantalla
            TypedQuery<ScAnalisis> findAnalisis
                    = em.createNamedQuery("ScAnalisis.findByNombre", ScAnalisis.class);
            findAnalisis.setParameter("nombre", inAnalisis.getNombre());
            ScAnalisis sca = findAnalisis.getSingleResult();

            MyUtil.addErrorMessage("Estado de insert", "El registro: " + sca.getNombre() + " ya existe");
            System.out.println("El registro: " + sca.getNombre() + " ya existe");
            return null;
        } catch (PersistenceException pe) {
            try {
                //Al no existir el resgistro, se ingresa uno nuevo
                ScAnalisis scanalisis = new ScAnalisis();
                scanalisis.setNombre(inAnalisis.getNombre());
                scanalisis.setTpanalisisId(em.find(ScTpanalisis.class, BigDecimal.valueOf(tipoAnalisisId)));
                scanalisis.setMetodo(inAnalisis.getMetodo());
                scanalisis.setNickName(inAnalisis.getNickName());

                em.persist(scanalisis);
                em.getTransaction().commit();
                MyUtil.addSuccessMessage("Estado de insert", "Con Exíto");

            } catch (PersistenceException e) {
                em.getTransaction().rollback();
                System.out.println("Error PersistenceException insertar analisis: " + pe.getMessage());
                System.err.println("Error PersistenceException insertar analisis: " + pe.getMessage());
                MyUtil.addSuccessMessage("Estado de insert", "Sin Exíto");
                return null;
            } catch (Exception ex) {
                em.getTransaction().rollback();
                System.out.println("Error Exception insertar analisis: " + ex.getMessage());
                System.err.println("Error PersistenceException insertar analisis: " + ex.getMessage());
                MyUtil.addSuccessMessage("Estado de insert", "Sin Exíto");
                return null;
            }
            em.close();
        }
        return "dt_config_analisis.xhtml";
    }

    public void onRowEdit(RowEditEvent event) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            //actualizar registro
            ScAnalisis scanalisis = em.find(ScAnalisis.class, selectedAnalisis.getAnalisisId());
            scanalisis.setNombre(selectedAnalisis.getNombre());
            scanalisis.setTpanalisisId(em.find(ScTpanalisis.class, BigDecimal.valueOf(tipoAnalisisId)));
            scanalisis.setMetodo(selectedAnalisis.getMetodo());
            scanalisis.setNickName(selectedAnalisis.getNickName());

            em.merge(scanalisis);
            em.getTransaction().commit();
            MyUtil.addSuccessMessage("Estado de update", "Con Exíto");
            em.close();

        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            System.out.println("Error PersistenceException actualizar analisis: " + e.getMessage());
            System.err.println("Error PersistenceException actualizar analisis: " + e.getMessage());
            MyUtil.addSuccessMessage("Estado de actualizado", "Sin Exíto");
            em.close();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("Error Exception actualizado analisis: " + ex.getMessage());
            System.err.println("Error PersistenceException actualizado analisis: " + ex.getMessage());
            MyUtil.addSuccessMessage("Estado de actualizado", "Sin Exíto");
            em.close();
        }
    }

    public void deleteAnalisis() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScAnalisis scanalisis = em.find(ScAnalisis.class, inAnalisis.getAnalisisId());
        em.remove(scanalisis);
        em.getTransaction().commit();
        em.close();
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición cancelada", ((ScAnalisis) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public ScAnalisis getInAnalisis() {
        return inAnalisis;
    }

    public void setInAnalisis(ScAnalisis inAnalisis) {
        this.inAnalisis = inAnalisis;
    }

    public ScTpanalisis getInTpAnalisis() {
        return inTpAnalisis;
    }

    public void setInTpAnalisis(ScTpanalisis inTpAnalisis) {
        this.inTpAnalisis = inTpAnalisis;
    }

    public List<ScAnalisis> getAnalisis() {
        return analisis;
    }

    public ScAnalisis getSelectedAnalisis() {
        return selectedAnalisis;
    }

    public void setSelectedAnalisis(ScAnalisis selectedAnalisis) {
        this.selectedAnalisis = selectedAnalisis;
    }

    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }
}
