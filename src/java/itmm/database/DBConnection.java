package itmm.database;

import itmm.util.MyUtil;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class DBConnection {

    EntityManager em;
    EntityManagerFactory emf;

    public DBConnection() {

    }
    public boolean Authenticate(String user, String pass, String schema) {
        System.out.println("Esquema : " + schema + " user: " + user + " pass: " + pass);

        boolean ok;

        String NPU;

        if (schema.equals("PRODUCTIVO")) {
            NPU = MyUtil.PERSISTENCE_UNIT_NAME_PRODUCTIVO;
        } else {
            NPU = MyUtil.PERSISTENCE_UNIT_NAME_CALIDAD;
        }

        //Listamos las propiedades de archivo XML persistence
        Map property = Property(user, pass);
        try {
            emf = Persistence.createEntityManagerFactory(NPU, property);
            em = emf.createEntityManager();

            ok = true;
        } catch (PersistenceException pe) {
            System.out.println("Mensaje error en logueo usuario: " + pe.getMessage());
            System.err.println("Error DBConnection: " + pe.getMessage());
            em.close();
            emf.close();
            ok = false;
        } catch (Exception e) {
            System.out.println("Mensaje en DBConnection, Exception: " + e.getMessage());
            ok = false;
        }
        return ok;
    }

    //Asignamos el listado de propiedades de persistence el esquema al cual sea logueado
    public static Map Property(String username, String password) {
        Map properties = new HashMap();
        properties.put("javax.persistence.jdbc.url", "jdbc:oracle:thin:@localhost:1521:baan");
        properties.put("javax.persistence.jdbc.driver", "oracle.jdbc.OracleDriver");
        properties.put("javax.persistence.jdbc.user", username);
        properties.put("javax.persistence.jdbc.password", password);

        return properties;
    }
}
