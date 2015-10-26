package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.ScMateriales;
import itmm.entities.ScTpanalisis;
import itmm.util.MyUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

@ManagedBean(name = "controlM")
@ViewScoped
public class ControlMuestra implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;

    StoredProcedureQuery q = null;

    private List<Integer> rows;
    private List<Integer> rows2;
    private int coutAnalisis = 0;

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
            /*
             TypedQuery<Long> query
             = em.createNamedQuery("ScMaterialAnalisis.countAnalisis", Long.class);
             query.setParameter("tpanalisis", em.find(ScTpanalisis.class, BigDecimal.valueOf(3)));
             query.setParameter("material", em.find(ScMateriales.class, String.valueOf("34000174")));

             long val = (long) query.getSingleResult();
             System.out.println("Valor total: " + val);*/

        } catch (PersistenceException pe) {
            System.out.println("Mensaje error Control muestra bean: " + pe.getMessage());
            System.out.println("Error DBConnection CM: " + pe.getMessage());

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            em.close();
            emf.close();
        } catch (NoSuchMethodError m) {
            System.out.println("NoSuchMethodError : " + m.getMessage());
        }

        rows = new ArrayList<Integer>();
        rows.add(1);
        rows.add(2);
        rows.add(3);
        rows.add(4);
        rows.add(5);

        rows2 = new ArrayList<Integer>();
        rows2.add(1);
        rows2.add(2);
        rows2.add(3);
        rows2.add(4);
        rows2.add(5);
        rows2.add(6);
        rows2.add(7);
        rows2.add(8);
        rows2.add(9);
        rows2.add(10);
        rows2.add(11);
        //System.out.println("Rows: " + countAnalisis());
    }

    public long countAnalisis(int tpanalisis) {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Long> query
                = em.createNamedQuery("ScMaterialAnalisis.countAnalisis", Long.class);
        query.setParameter("tpanalisis", em.find(ScTpanalisis.class, BigDecimal.valueOf(tpanalisis)));
        query.setParameter("material", em.find(ScMateriales.class, String.valueOf("34000174")));

        long val = (long) query.getSingleResult();

        em.close();
        return val;
    }

    public boolean existe(int tpanalisis) {

        em = emf.createEntityManager();

        boolean mostrar = false;

        em.getTransaction().begin();

        TypedQuery<Long> query
                = em.createNamedQuery("ScMaterialAnalisis.countAnalisis", Long.class);
        query.setParameter("tpanalisis", em.find(ScTpanalisis.class, BigDecimal.valueOf(tpanalisis)));
        query.setParameter("material", em.find(ScMateriales.class, String.valueOf("34000174")));

        long val = (long) query.getSingleResult();

        if (val > 0) {
            mostrar = true;
        }

        em.close();

        return mostrar;
    }

    public List<String> findAnalisis(int tpanalisis) {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<String> query
                = em.createNamedQuery("ScMaterialAnalisis.readAnalisis", String.class);
        query.setParameter("tpanalisis", em.find(ScTpanalisis.class, BigDecimal.valueOf(tpanalisis)));
        query.setParameter("materialid", em.find(ScMateriales.class, String.valueOf("34000174")));

        List<String> val = query.getResultList();
        /*
         for (String a : val) {
         System.out.println("analisis: " + a);
         }*/
        em.close();
        return val;
    }

    public List<String> findAnalisisMax(int tpanalisis) {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<String> query
                = em.createNamedQuery("ScMaterialAnalisis.readMaximo", String.class);
        query.setParameter("tpanalisis", em.find(ScTpanalisis.class, BigDecimal.valueOf(tpanalisis)));
        query.setParameter("materialid", em.find(ScMateriales.class, String.valueOf("34000174")));

        List<String> val = query.getResultList();
        /*
         for (String a : val) {
         System.out.println("analisis: " + a);
         }*/
        em.close();
        return val;
    }

    public List<String> findAnalisisMin(int tpanalisis) {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<String> query
                = em.createNamedQuery("ScMaterialAnalisis.readMinimo", String.class);
        query.setParameter("tpanalisis", em.find(ScTpanalisis.class, BigDecimal.valueOf(tpanalisis)));
        query.setParameter("materialid", em.find(ScMateriales.class, String.valueOf("34000174")));

        List<String> val = query.getResultList();
        /*
         for (String a : val) {
         System.out.println("analisis: " + a);
         }*/
        em.close();
        return val;
    }

    public ControlMuestra() {

    }

    public int getCoutAnalisis() {
        return coutAnalisis;
    }

    public void setCoutAnalisis(int coutAnalisis) {
        this.coutAnalisis = coutAnalisis;
    }

    public int row() {
        return 2;
    }

    public List<Integer> getRows2() {
        return rows2;
    }

    public List<Integer> getRows() {
        return rows;
    }

    public int getCountRows() {
        return rows.size();
    }

    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }

}
