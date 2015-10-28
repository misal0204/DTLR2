package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.DlEncamh;
import itmm.entities.ScMateriales;
import itmm.entities.ScTpanalisis;
import itmm.util.MyUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
    private List<Integer> NoMuestras;
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

            TypedQuery<BigInteger> query
                    = em.createNamedQuery("ScMaterialAnalisis.readNoMuestras", BigInteger.class);
            query.setParameter("materialId", String.valueOf("34000173"));

            BigInteger val = (BigInteger) query.getSingleResult();
            int va1 = val.intValue();
            //System.out.println("NoMuestras: " + val);

            NoMuestras = new ArrayList<Integer>();

            for (int i = 1; i <= va1; i++) {

                NoMuestras.add(i);
            }

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
        //System.out.println("Rows: " + countAnalisis());
        em.close();
    }

    public long countAnalisis(int tpanalisis) {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Long> query
                = em.createNamedQuery("ScMaterialAnalisis.countAnalisis", Long.class);
        query.setParameter("tpanalisis", em.find(ScTpanalisis.class, BigDecimal.valueOf(tpanalisis)));
        query.setParameter("material", em.find(ScMateriales.class, String.valueOf("34000173")));

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
        query.setParameter("material", em.find(ScMateriales.class, String.valueOf("34000173")));

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
        query.setParameter("materialid", em.find(ScMateriales.class, String.valueOf("34000173")));

        List<String> val = query.getResultList();

        em.close();
        return val;
    }

    public List<String> findAnalisisMax(int tpanalisis) {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<String> query
                = em.createNamedQuery("ScMaterialAnalisis.readMaximo", String.class);
        query.setParameter("tpanalisis", em.find(ScTpanalisis.class, BigDecimal.valueOf(tpanalisis)));
        query.setParameter("materialid", em.find(ScMateriales.class, String.valueOf("34000173")));

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
        query.setParameter("materialid", em.find(ScMateriales.class, String.valueOf("34000173")));

        List<String> val = query.getResultList();

        em.close();
        return val;
    }

    public List<Double> findDTAnalisis(int tpanalisis) {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Double> query
                = em.createNamedQuery("DlTDetmh.analisisByTp", Double.class);
        query.setParameter("tpanalisis", em.find(ScTpanalisis.class, BigDecimal.valueOf(tpanalisis)));
        query.setParameter("doc", em.find(DlEncamh.class, "100000001009"));

        List<Double> val = query.getResultList();
        int aa=1;
        for (Double a : val) {
            System.out.println("valor de analisis: " + aa++);
        }

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

    public List<Integer> getNoMuestras() {
        return NoMuestras;
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
