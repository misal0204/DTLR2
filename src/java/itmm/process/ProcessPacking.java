package itmm.process;

import itmm.controller.AuthenticationBean;
import itmm.controller.ControlMuestra;
import itmm.database.DBConnection;
import itmm.entities.DlEncamh;
import itmm.entities.DlTempDetmh;
import itmm.entities.ScTpanalisis;
import itmm.util.MyUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

public class ProcessPacking implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;

    private int noMuestra;

    private String schema;
    private String usuario;
    private String pass;
    
    @ManagedProperty("#{controlM}")
    private ControlMuestra dld;

    @ManagedProperty("#{attBean}")
    private AuthenticationBean attb;

    public ProcessPacking() {

    }

    public ProcessPacking(int noMuestra) {
        this.noMuestra = noMuestra;
    }

    public int getNoMuestra() {
        return noMuestra;
    }

    public void setNoMuestra(int noMuestra) {
        this.noMuestra = noMuestra;
    }

    public List<Double> getFindA(int tpanalisis, int nmuestras) {

        List<Double> val = null;
        schema = attb.getSchema();
        usuario = attb.getUsername();
        pass = attb.getPassword();
        System.out.println("Usuario de process" + usuario);
        String NPU;
        if (schema.equals("PRODUCTIVO")) {
            NPU = MyUtil.PERSISTENCE_UNIT_NAME_PRODUCTIVO;

        } else {
            NPU = MyUtil.PERSISTENCE_UNIT_NAME_CALIDAD;
        }

        try {
            Map property = DBConnection.Property("padmin", "datalabr2");
            emf = Persistence.createEntityManagerFactory(NPU, property);
            em = emf.createEntityManager();
            em.getTransaction().begin();

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

    public ControlMuestra getDld() {
        return dld;
    }

    public void setDld(ControlMuestra dld) {
        this.dld = dld;
    }
    
    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }

}
