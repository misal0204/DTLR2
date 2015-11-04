package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.DlEncamh;
import itmm.entities.ScCentroPaises;
import itmm.entities.ScCentros;
import itmm.entities.ScEstados;
import itmm.entities.ScMateriales;
import itmm.entities.ScMolinoCentros;
import itmm.entities.ScMolinos;
import itmm.entities.ScPaises;
import itmm.entities.ScTipomateriales;
import itmm.entities.ScTipomuestras;
import itmm.util.MyUtil;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
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
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "documentsB")
@ViewScoped
public class DocumentsBean implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;
    private List<DlEncamh> documentos;
    private List<DlEncamh> documentFiltered;

    private List<ScMolinos> molinos;
    private List<ScTipomuestras> muestras;
    private List<ScCentros> centros;
    private List<ScPaises> paises;
    private List<ScMateriales> materiales;

    private DlEncamh documentosSelected;
    private String selectedPais;
    private String selectedCentro;
    private String selectedMolino;
    private String selectedTpMaterial;
    private String selectedMaterial;

    private String schema;
    private String usuario;
    private String pass;

    private Date tofecha;
    private String docu;

    @ManagedProperty("#{attBean}")
    private AuthenticationBean attb;

    @PostConstruct
    public void init() {
        schema = attb.getSchema();
        usuario = attb.getUsername();
        pass = attb.getPassword();

        String NPU = selectedNPU(schema);

        Map property = DBConnection.Property(usuario, pass);

        try {
            emf = Persistence.createEntityManagerFactory(NPU, property);
            em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<DlEncamh> query
                    = em.createNamedQuery("DlEncamh.findAll", DlEncamh.class);
            List<DlEncamh> results = query.getResultList();

            documentos = results;
            //molinos = molinosId(em);
            muestras = muestrasId(em);
            //centros = centrosId(em);
            paises = paisesId(em);
            materiales = materialesDoc(em);

            //findCentro("SV");

            /*for (DlEncamh document : results) {
             System.out.println("Documento: " + document.getDocumento());
             }*/
        } catch (PersistenceException pe) {
            System.out.println("Mensaje error Documento bean: " + pe.getMessage());
            System.err.println("Error DBConnection: " + pe.getMessage());
            em.close();
            emf.close();
        }

        em.getTransaction().commit();
        em.close();
    }

    public DocumentsBean() {
    }

    public List<ScMolinos> molinosId(EntityManager em) {
        TypedQuery<ScMolinos> query
                = em.createNamedQuery("ScMolinos.findAll", ScMolinos.class);
        List<ScMolinos> results = query.getResultList();

        return results;
    }

    public List<ScTipomuestras> muestrasId(EntityManager em) {
        TypedQuery<ScTipomuestras> query
                = em.createNamedQuery("ScTipomuestras.findAll", ScTipomuestras.class);
        List<ScTipomuestras> results = query.getResultList();

        return results;
    }

    public List<ScCentros> centrosId(EntityManager em) {
        TypedQuery<ScCentros> query
                = em.createNamedQuery("ScCentros.findAll", ScCentros.class);
        List<ScCentros> results = query.getResultList();

        return results;
    }

    public List<ScPaises> paisesId(EntityManager em) {
        TypedQuery<ScPaises> query
                = em.createNamedQuery("ScPaises.findAll", ScPaises.class);
        List<ScPaises> results = query.getResultList();
        return results;
    }

    public List<ScMateriales> materialesDoc(EntityManager em) {
        TypedQuery<ScMateriales> query
                = em.createNamedQuery("ScMateriales.findAll", ScMateriales.class);
        List<ScMateriales> results = query.getResultList();
        return results;
    }

    public List<ScCentroPaises> findCentro(String paisId) {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScPaises pais = em.find(ScPaises.class, paisId);

        TypedQuery<ScCentroPaises> query
                = em.createNamedQuery("ScCentroPaises.findByPaisId", ScCentroPaises.class);
        query.setParameter("paisId", pais);
        List<ScCentroPaises> results = query.getResultList();

        em.close();
        return results;
    }

    public List<ScMolinoCentros> findMolino(String centroId) {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScCentros centro = em.find(ScCentros.class, centroId);

        TypedQuery<ScMolinoCentros> query
                = em.createNamedQuery("ScMolinoCentros.findByCentro", ScMolinoCentros.class);
        query.setParameter("centroId", centro);
        List<ScMolinoCentros> results = query.getResultList();

        for (ScMolinoCentros mol : results) {
            System.out.println("Molino: " + mol.getMolinoId().getNombre() + " - " + mol.getCentroId().getNombre());
        }

        em.close();
        return results;
    }

    public List<ScTipomateriales> tpMateriales() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<ScTipomateriales> query
                = em.createNamedQuery("ScTipomateriales.findAll", ScTipomateriales.class);
        List<ScTipomateriales> results = query.getResultList();

        em.close();
        return results;
    }

    public List<ScMateriales> findMaterial(String tpmaterial) {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        ScTipomateriales tmaterial = em.find(ScTipomateriales.class, tpmaterial);

        TypedQuery<ScMateriales> query
                = em.createNamedQuery("ScMateriales.findByMaterialTp", ScMateriales.class);
        query.setParameter("tpmaterialId", tmaterial);
        List<ScMateriales> results = query.getResultList();

        em.close();
        return results;
    }

    public List<ScEstados> findEstados() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<ScEstados> query
                = em.createNamedQuery("ScEstados.findAll", ScEstados.class);
        List<ScEstados> results = query.getResultList();

        em.close();
        return results;
    }

    public String toDate(Date date) {
        //Date now = new Date();
        DateFormat df = DateFormat.getDateInstance();
        String fecha = df.format(date);
        return fecha;
    }

    public String toDate_now() {
        Date now = new Date();
        DateFormat df = DateFormat.getDateInstance();
        String fecha = df.format(now);
        return fecha;
    }

    public String selectedNPU(String sch) {

        if (sch.equals("PRODUCTIVO")) {
            return MyUtil.PERSISTENCE_UNIT_NAME_PRODUCTIVO;
        } else {
            return MyUtil.PERSISTENCE_UNIT_NAME_CALIDAD;
        }
    }

    public String irProceso() {
        return "dt_control_muestras.xhtml?faces-redirect=true";
    }

    public String getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(String selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }

    public String getSelectedTpMaterial() {
        return selectedTpMaterial;
    }

    public void setSelectedTpMaterial(String selectedTpMaterial) {
        this.selectedTpMaterial = selectedTpMaterial;
    }

    public String getSelectedPais() {
        return selectedPais;
    }

    public void setSelectedPais(String selectedPais) {
        this.selectedPais = selectedPais;
    }

    public String getSelectedCentro() {
        return selectedCentro;
    }

    public void setSelectedCentro(String selectedCentro) {
        this.selectedCentro = selectedCentro;
    }

    public String getSelectedMolino() {
        return selectedMolino;
    }

    public void setSelectedMolino(String selectedMolino) {
        this.selectedMolino = selectedMolino;
    }

    public List<ScMateriales> getMateriales() {
        return materiales;
    }

    public List<ScPaises> getPaises() {
        return paises;
    }

    public List<ScTipomuestras> getMuestras() {
        return muestras;
    }

    public List<ScCentros> getCentros() {
        return centros;
    }

    public List<ScMolinos> getMolinos() {
        return molinos;
    }

    public List<DlEncamh> getDocumentFiltered() {
        return documentFiltered;
    }

    public void setDocumentFiltered(List<DlEncamh> documentFiltered) {
        this.documentFiltered = documentFiltered;
    }

    public DlEncamh getDocumentosSelected() {
        return documentosSelected;
    }

    public void setDocumentosSelected(DlEncamh documentosSelected) {
        this.documentosSelected = documentosSelected;
    }

    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }

    public List<DlEncamh> getDocumentos() {
        return documentos;
    }

    public Date getTofecha() {
        return tofecha;
    }

    public void setTofecha(Date tofecha) {
        this.tofecha = tofecha;
    }

    public String getDocu() {
        return docu;
    }

    public void setDocu(String docu) {
        this.docu = docu;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Muestra seleccionada", ((DlEncamh) event.getObject()).getDocumento());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
