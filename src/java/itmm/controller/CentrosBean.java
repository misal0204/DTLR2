package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.ScCentroPaises;
import itmm.entities.ScCentros;
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

@ManagedBean(name = "centrosB")
@SessionScoped
public class CentrosBean implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;

    private List<ScCentros> listCentros;
    private ScCentros selectedCentros;
    private ScCentros inCentros;
    private ScPaises inPais;

    private String schema;
    private String usuario;
    private String pass;

    @ManagedProperty("{attBean}")
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

            TypedQuery<ScCentros> query
                    = em.createNamedQuery("ScCentros.findAll", ScCentros.class);
            List<ScCentros> results = query.getResultList();

            /*
             for (ScCentros c : results) {
             System.out.println(c.getNombre());
             }*/
        } catch (PersistenceException pe) {
            System.out.println("Mensaje error centros bean: " + pe.getMessage());
            System.out.println("Error centros DBConnection: " + pe.getMessage());
            em.close();
            emf.close();
        }

        em.getTransaction().commit();
        em.close();
    }

    public CentrosBean() {
    }

    public void insertCentros() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScCentros centros = new ScCentros();
        centros.setCentroId(inCentros.getCentroId());
        centros.setNombre(inCentros.getNombre());
        centros.setDireccion(inCentros.getDireccion());
        centros.setDescripcion(inCentros.getDescripcion());

        em.persist(centros);
        em.getTransaction().commit();
        //Insert centro_pais
        /*insertCentroPais(em, inCentros.getCentroId());*/
        em.close();
    }
    
    public void insertCentroPais(EntityManager cem,String cen)
    {
        ScCentroPaises centropaises=new ScCentroPaises();
        
        ScCentros centro= cem.getReference(ScCentros.class, cen);
        ScPaises pais= cem.getReference(ScPaises.class, inPais.getPaisId());
        
        centropaises.setCentroId(centro);
        centropaises.setPaisId(pais);
        
        cem.persist(centropaises);
        cem.getTransaction().commit();       
    }
    
    public void updateCentros() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScCentros centros = em.find(ScCentros.class, inCentros.getCentroId());
        centros.setNombre(inCentros.getNombre());
        centros.setDireccion(inCentros.getDireccion());
        centros.setDescripcion(inCentros.getDescripcion());
        
        em.persist(centros);
        em.getTransaction().commit();
        em.close();
    }
    
    //update centro_paises
    public void updateCentroPais(){}
    
    public void deleteCentros()
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        ScCentros centros= em.find(ScCentros.class, inCentros.getCentroId());
        
        em.remove(centros);
        em.getTransaction().commit();
        em.close();
    }

    //delete centro_paises
    public void deleteCentroPais(){}
    
    public List<ScCentros> getListCentros() {
        return listCentros;
    }

    public ScCentros getSelectedCentros() {
        return selectedCentros;
    }

    public void setSelectedCentros(ScCentros selectedCentros) {
        this.selectedCentros = selectedCentros;
    }

    public ScCentros getInCentros() {
        return inCentros;
    }

    public void setInCentros(ScCentros inCentros) {
        this.inCentros = inCentros;
    }

    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }
    
    
}
