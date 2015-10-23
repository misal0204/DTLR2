package itmm.controller;

import itmm.database.DBConnection;
import itmm.entities.ScDeptos;
import itmm.entities.ScEstados;
import itmm.entities.ScPaises;
import itmm.entities.ScUsuarioPaises;
import itmm.entities.ScUsuarios;
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

@ManagedBean(name = "usuariosB")
@SessionScoped
public class UsuariosBean implements Serializable {

    EntityManager em;
    EntityManagerFactory emf;

    private List<ScUsuarios> usuarios;
    private ScUsuarios selectedAnalisis;
    private ScUsuarios inUser;
    private ScPaises inPais;

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

            TypedQuery<ScUsuarios> query
                    = em.createNamedQuery("ScUsuarios.findAll", ScUsuarios.class);
            List<ScUsuarios> results = query.getResultList();

            for (ScUsuarios u : results) {
                System.out.println(u.getNombre());
            }

        } catch (PersistenceException pe) {
            System.out.println("Mensaje error usuarios bean: " + pe.getMessage());
            System.out.println("Error usuarios DBConnection: " + pe.getMessage());
            em.close();
            emf.close();
        }
        em.getTransaction().commit();
        em.close();
    }

    public UsuariosBean() {
    }
    
    public void insertUsuario()
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        ScUsuarios scusuarios=new ScUsuarios();
        scusuarios.setUsuarioId(inUser.getUsuarioId());
        scusuarios.setNombre(inUser.getNombre());
        scusuarios.setCorreo(inUser.getCorreo());
        scusuarios.setExtension(inUser.getExtension());
        scusuarios.setTelefono(inUser.getTelefono());
        /*Revisar parametro de ingresar Depto*/
        scusuarios.setDeptoId(em.find(ScDeptos.class, inUser.getDeptoId().getDeptoId()));
        scusuarios.setCargo(inUser.getCargo());
        scusuarios.setTipo(inUser.getTipo());
        scusuarios.setEstado(em.find(ScEstados.class, inUser.getEstado()));
        /*Revisar formato de ingreso de fecha*/
        scusuarios.setFechaUltLogon(inUser.getFechaUltLogon());
        /*Revisar formato de ingreso de fecha*/
        scusuarios.setFechaCamCla(inUser.getFechaCamCla());
        scusuarios.setVigenciaCla(inUser.getVigenciaCla());
        scusuarios.setUsuarioSap(inUser.getUsuarioSap());
        
        em.persist(scusuarios);
        em.getTransaction().commit();
        
        //Insertar en tabla de ScUSuarioPaises
        /*insertUserPais(em, inUser.getUsuarioId());*/
        
        em.close();
    }
    
    // cem= copia de entity manager
    public void insertUserPais(EntityManager cem,String usuarioid)
    {
        ScUsuarioPaises scusuariospaises=new ScUsuarioPaises();
        
        ScPaises pais= cem.getReference(ScPaises.class, inPais.getPaisId());
        ScUsuarios user= cem.getReference(ScUsuarios.class, usuarioid);
        
        scusuariospaises.setPaidId(pais);
        scusuariospaises.setUsuarioId(user);
        
        cem.getTransaction().commit();
    }
    
    public void updateUser()
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        ScUsuarios scusuario=em.find(ScUsuarios.class, inUser.getUsuarioId());
        ScUsuarios scusuarios=new ScUsuarios();
        scusuarios.setUsuarioId(inUser.getUsuarioId());
        scusuarios.setNombre(inUser.getNombre());
        scusuarios.setCorreo(inUser.getCorreo());
        scusuarios.setExtension(inUser.getExtension());
        scusuarios.setTelefono(inUser.getTelefono());
        /*Revisar parametro de ingresar Depto*/
        scusuarios.setDeptoId(em.find(ScDeptos.class, inUser.getDeptoId().getDeptoId()));
        scusuarios.setCargo(inUser.getCargo());
        scusuarios.setTipo(inUser.getTipo());
        scusuarios.setEstado(em.find(ScEstados.class, inUser.getEstado()));
        /*Revisar formato de ingreso de fecha*/
        scusuarios.setFechaUltLogon(inUser.getFechaUltLogon());
        /*Revisar formato de ingreso de fecha*/
        scusuarios.setFechaCamCla(inUser.getFechaCamCla());
        scusuarios.setVigenciaCla(inUser.getVigenciaCla());
        scusuarios.setUsuarioSap(inUser.getUsuarioSap());
        
        em.persist(scusuarios);
        em.getTransaction().commit();
        
        em.close();
    }

    //método para actualizar país de usuario
    public void updateUserPais(){}
    
    public void deleteUser()
    {
        em= emf.createEntityManager();
        em.getTransaction().begin();
        
        ScUsuarios scusuarios= em.find(ScUsuarios.class, inUser.getUsuarioId());
        em.remove(scusuarios);
        em.getTransaction().commit();
        em.close();
    }

    public List<ScUsuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<ScUsuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public ScUsuarios getSelectedAnalisis() {
        return selectedAnalisis;
    }

    public void setSelectedAnalisis(ScUsuarios selectedAnalisis) {
        this.selectedAnalisis = selectedAnalisis;
    }

    public ScUsuarios getInUser() {
        return inUser;
    }

    public void setInUser(ScUsuarios inUser) {
        this.inUser = inUser;
    }

    public ScPaises getInPais() {
        return inPais;
    }

    public void setInPais(ScPaises inPais) {
        this.inPais = inPais;
    }

    public AuthenticationBean getAttb() {
        return attb;
    }

    public void setAttb(AuthenticationBean attb) {
        this.attb = attb;
    }
    
    
}
