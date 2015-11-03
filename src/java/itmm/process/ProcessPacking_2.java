package itmm.process;

import itmm.controller.AuthenticationBean;
import itmm.database.DBConnection;
import itmm.util.MyUtil;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
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

@ManagedBean(name = "process_2")
@ViewScoped
public class ProcessPacking_2 implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;

    private int noMuestra;
    private Integer NoM;

    private ProcessPacking_2 process;

    private List<ProcessPacking_2> NMuestras;

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
            NMuestras = new ArrayList<ProcessPacking_2>();
            for (int i = 1; i <= va1; i++) {

                NMuestras.add(new ProcessPacking_2(i));
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

    public ProcessPacking_2() {
    }

    public long countTpAnalisis() {
        em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Long> query
                = em.createNamedQuery("ScTpanalisis.countTpanalisis", Long.class);
        long val = (long) query.getSingleResult();
        // System.out.println("tpanalisis: " + val);
        em.close();
        return val;
    }

    public List<ProcessPacking_2> getNMuestras() {
        return NMuestras;
    }

    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }

    public ProcessPacking_2(int noMuestra) {
        this.noMuestra = noMuestra;
    }

    public int getNoMuestra() {
        return noMuestra;
    }

    public void setNoMuestra(int noMuestra) {
        this.noMuestra = noMuestra;
    }

    public ProcessPacking_2 getProcess() {
        return process;
    }

    public void setProcess(ProcessPacking_2 process) {
        this.process = process;
    }
}
