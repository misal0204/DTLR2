package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.ScAnalisis;
import itmm.entities.ScCentros;
import itmm.entities.ScMolinoCentros;
import itmm.entities.ScMolinos;
import itmm.util.MyUtil;
import java.io.Serializable;
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

@ManagedBean(name = "molinosB")
@ViewScoped
public class MolinosBean implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;

    private List<ScMolinos> listMolinos;
    private List<ScMolinoCentros> listMolinoCentros;
    private ScMolinos selectedMolinos;
    private ScMolinos inMolinos;
    private ScCentros inCentros;

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

        inMolinos = new ScMolinos();

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

            TypedQuery<ScMolinos> query
                    = em.createNamedQuery("ScMolinos.findAll", ScMolinos.class);
            listMolinos = query.getResultList();

            readListMP(em);

            //List<ScMolinos> results = query.getResultList();

            /*
             for (ScMolinos m : results) {
             System.out.println(m.getNombre());
             }*/
        } catch (PersistenceException pe) {
            System.out.println("Mensaje error molinos bean: " + pe.getMessage());
            System.out.println("Error molinos DBConnection: " + pe.getMessage());
            em.close();
            emf.close();
        }
        em.getTransaction().commit();
        em.close();
    }

    public MolinosBean() {
    }

    public String insertMolinos() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            // Verificamos si el registro no existe, si existe
            //aparecera un mensaje en pantalla
            TypedQuery<ScMolinos> findAnalisis
                    = em.createNamedQuery("ScMolinos.findByNombre", ScMolinos.class);
            findAnalisis.setParameter("nombre", inMolinos.getNombre());
            ScMolinos scm = findAnalisis.getSingleResult();

            MyUtil.addErrorMessage("Estado de insert", "El registro: " + scm.getNombre() + " ya existe");
            System.out.println("El registro: " + scm.getNombre() + " ya existe");
            return null;
        } catch (PersistenceException pe) {
            try {
                ScMolinos molinos = new ScMolinos();
                molinos.setMolinoId(inMolinos.getMolinoId());
                molinos.setNombre(inMolinos.getNombre());
                molinos.setDescripcion(inMolinos.getDescripcion());

                em.persist(molinos);
                em.getTransaction().commit();
                MyUtil.addSuccessMessage("Estado de insert", "Con exito");
            } catch (PersistenceException e) {
                em.getTransaction().rollback();
                System.out.println("Error PersistenceException insertar molino: " + pe.getMessage());
                System.err.println("Error PersistenceException insertar molino: " + pe.getMessage());
                MyUtil.addErrorMessage("Estado de insert", "Sin Exíto");
                return null;
            } catch (Exception ex) {
                em.getTransaction().rollback();
                System.out.println("Error Exception insertar molino: " + ex.getMessage());
                System.err.println("Error PersistenceException insertar molino: " + ex.getMessage());
                MyUtil.addErrorMessage("Estado de insert", "Sin Exíto");
                return null;
            }
            em.close();
        }

        return "dt_list_molino.xhtml";
    }

    public void onRowEdit(RowEditEvent event) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            ScMolinos molinos = em.find(ScMolinos.class, selectedMolinos.getMolinoId());
            molinos.setNombre(selectedMolinos.getNombre());
            molinos.setDescripcion(selectedMolinos.getDescripcion());

            em.merge(molinos);
            em.getTransaction().commit();
            MyUtil.addSuccessMessage("Estado de update", "Con éxito");
            em.close();

        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            System.out.println("Error PersistenceException actualizar molino: " + e.getMessage());
            System.err.println("Error PersistenceException actualizar molino: " + e.getMessage());
            MyUtil.addSuccessMessage("Estado de actualizado", "Sin Exíto");
            em.close();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("Error Exception actualizado molino: " + ex.getMessage());
            System.err.println("Error PersistenceException actualizado molino: " + ex.getMessage());
            MyUtil.addSuccessMessage("Estado de actualizado", "Sin Exíto");
            em.close();
        }
    }

    public void insertMolinoCentro(EntityManager cem, String molinoid) {
        cem = emf.createEntityManager();
        cem.getTransaction().begin();

        ScMolinoCentros molinocentro = new ScMolinoCentros();

        ScCentros centro = cem.getReference(ScCentros.class, inCentros.getCentroId());
        ScMolinos molino = cem.getReference(ScMolinos.class, molinoid);
        molinocentro.setCentroId(centro);
        molinocentro.setMolinoId(molino);

        cem.persist(molinocentro);

        cem.getTransaction().commit();
    }

    public void updateMolinos() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScMolinos molinos = em.find(ScMolinos.class, inMolinos.getMolinoId());
        molinos.setNombre(inMolinos.getNombre());
        molinos.setDescripcion(inMolinos.getDescripcion());

        em.persist(molinos);
        em.getTransaction().commit();
        em.close();
    }

    //actualizar molino_centro
    public void updateMolinoCentro() {
    }

    public void deleteMolino() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScMolinos molinos = em.find(ScMolinos.class, inMolinos.getMolinoId());
        em.remove(molinos);
        em.getTransaction().commit();
        em.close();
    }

    public void readListMP(EntityManager cem) {
        cem = emf.createEntityManager();
        cem.getTransaction().begin();

        TypedQuery<ScMolinoCentros> query
                = cem.createNamedQuery("ScMolinoCentros.findAll", ScMolinoCentros.class);
        listMolinoCentros = query.getResultList();
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición cancelada", ((ScMolinos) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<ScMolinoCentros> getListMolinoCentros() {
        return listMolinoCentros;
    }

    public List<ScMolinos> getListMolinos() {
        return listMolinos;
    }

    public ScMolinos getSelectedMolinos() {
        return selectedMolinos;
    }

    public void setSelectedMolinos(ScMolinos selectedMolinos) {
        this.selectedMolinos = selectedMolinos;
    }

    public ScMolinos getInMolinos() {
        return inMolinos;
    }

    public void setInMolinos(ScMolinos inMolinos) {
        this.inMolinos = inMolinos;
    }

    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }

}
