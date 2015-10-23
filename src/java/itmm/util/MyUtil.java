package itmm.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MyUtil {

    public final static String PERSISTENCE_UNIT_NAME_CALIDAD = "DTLR2PU_Q";
    public final static String PERSISTENCE_UNIT_NAME_PRODUCTIVO = "DTLR2PU_P";

    public static String baseUrl() {
        return "http://localhost:7001/DTLR2";
    }

    public static String basepathlogin() {
        return "/DTLR2/faces/";
    }

    public static String basepathsecured() {
        return "/DTLR2/faces/secured/";
    }

    public static void addSuccessMessage(String estado, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, estado, msg);
        FacesContext.getCurrentInstance().addMessage(estado, facesMsg);
    }

    public static void addErrorMessage(String estado,String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, estado, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
}
