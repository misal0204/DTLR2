package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.DlEncamh;
import itmm.entities.DlTempDetmh;
import itmm.entities.ScMateriales;
import itmm.entities.ScTpanalisis;
import itmm.process.ProcessPacking;
import itmm.util.MyUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
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
    private List<DlTempDetmh> NoMateriales;
    private List<ProcessPacking> NMuestras;
    
    private int coutAnalisis = 0;

    private Integer NoM;
    private Integer n = 0;

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

            TypedQuery<BigInteger> query
                    = em.createNamedQuery("ScMaterialAnalisis.readNoMuestras", BigInteger.class);
            query.setParameter("materialId", String.valueOf("34000173"));

            BigInteger val = (BigInteger) query.getSingleResult();
            int va1 = val.intValue();
            NoM = va1;
            
            NMuestras= new ArrayList<ProcessPacking>();
            for (int i = 1; i <= va1; i++) {
                
                NMuestras.add(new ProcessPacking(i));
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
        em.close();
    }

    public ControlMuestra() {
    }

    public List<DlTempDetmh> listadoDeMuestras() {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<DlTempDetmh> query
                = em.createNamedQuery("DlTempDetmh.findAll", DlTempDetmh.class);
        List<DlTempDetmh> val = query.getResultList();
        NoMateriales = val;

        return val;
    }

    public long countTpAnalisis() {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Long> query
                = em.createNamedQuery("ScTpanalisis.countTpanalisis", Long.class);
        long val = (long) query.getSingleResult();
        System.out.println("tpanalisis: " + val);
        em.close();
        return val;
    }

    public String nameTpAnalisis(int tpanalisis) {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<String> query
                = em.createNamedQuery("ScTpanalisis.nameTpanalisis", String.class);
        query.setParameter("tpanalisis_id", tpanalisis);
        String name = query.getSingleResult();

        //System.out.println("tpanalisis: " + name);
        em.close();
        return name;
    }

    public long countAnalisis(int tpanalisis) {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Long> query
                = em.createNamedQuery("ScMaterialAnalisis.countAnalisis", Long.class);
        query.setParameter("tpanalisis", em.find(ScTpanalisis.class, BigDecimal.valueOf(tpanalisis)));
        query.setParameter("material", em.find(ScMateriales.class, String.valueOf("34000173")));

        long val = (long) query.getSingleResult();
        //System.out.println("tpanalisis existent: " + val);
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

    public boolean existeAnalisis(int tpanalisis, int nmuestra) {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        boolean existe=false;
        
        TypedQuery<Long> query
                = em.createNamedQuery("DlTempDetmh.countByTp", Long.class);
        query.setParameter("tpanalisis", tpanalisis);
        query.setParameter("doc", "100000001011");
        query.setParameter("muestra", nmuestra);
        
        long val = (long) query.getSingleResult();
        // System.out.println("tpanalisis: " + val);
        
        if(val > 0)
        {
            existe=true;
        }
        em.close();
        return existe;
    }

    public long countAnalisis() {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Long> query
                = em.createNamedQuery("DlTempDetmh.countByTp", Long.class);
        query.setParameter("tpanalisis", 3);
        query.setParameter("doc", "100000001011");
        query.setParameter("muestra", 1);
        long val = (long) query.getSingleResult();
        System.out.println("count tpanalisis: " + val);
        em.close();
        return val;
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

    public List<Double> findDTAnalisis(int tpanalisis, int nmuestras) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        nmuestras++;
        System.out.println("nmuestra: "+nmuestras+" repeat: "+(coutAnalisis++));
        System.out.println("tpanalisis: "+tpanalisis);
        
        List<Double> val = null;

        try {

            TypedQuery<Double> query
                    = em.createNamedQuery("DlTempDetmh.analisisByTp", Double.class);
            query.setParameter("tpanalisis", em.find(ScTpanalisis.class, BigDecimal.valueOf(tpanalisis)));
            query.setParameter("doc", em.find(DlEncamh.class, "100000001011"));
            query.setParameter("muestra", BigDecimal.valueOf(nmuestras));

            val = query.getResultList();
            System.out.println("Valor analisis: ");
            for (Double a : val) {
                System.out.print(a + " ");
            }

            System.out.println();
        } catch (PersistenceException pe) {
            System.out.println("Mensaje Persistence: " + pe.getMessage());
        }
        em.close();
        return val;
    }


    public List<Double> valorAnalisis() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Double> query
                = em.createNamedQuery("DlTempDetmh.analisisByTp", Double.class);
        query.setParameter("tpanalisis", em.find(ScTpanalisis.class, BigDecimal.valueOf(3)));
        query.setParameter("doc", em.find(DlEncamh.class, String.valueOf("100000001011")));
        query.setParameter("muestra", 1);

        List<Double> valAnalisis = query.getResultList();

        for (Double a : valAnalisis) {
            System.out.print("-" + a + "-");
        }

        em.close();

        return valAnalisis;
    }
    
    public List<ProcessPacking> getNMuestras() {
        return NMuestras;
    }

    public Integer getNoM() {
        return NoM;
    }

    public void setNoM(Integer NoM) {
        this.NoM = NoM;
    }

    public List<DlTempDetmh> getNoMateriales() {
        return NoMateriales;
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
