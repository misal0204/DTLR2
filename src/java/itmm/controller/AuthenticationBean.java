/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.controller;

import itmm.database.DBConnection;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Misael
 */
@ManagedBean(name = "attBean")
@SessionScoped
public class AuthenticationBean implements Serializable {

    private String username;
    private String password;
    private String schema;

    private final FacesContext facesContext;
    //private final HttpServletRequest httpServletRequest;
    private final HttpSession httpSession;

    private final DBConnection DBC = new DBConnection();

    public AuthenticationBean() {
        facesContext = FacesContext.getCurrentInstance();
        //httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getSession(true);
        httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);

    }

    public String authenticationControl() {
        boolean access = DBC.Authenticate(username, password, schema);
        try {
            if (access) {
                //httpServletRequest.getSession().setAttribute("session_name", username);
                httpSession.setAttribute("session_name", username);
                System.out.println("Acceso satisfactorio");
                return "secured/menuprincipal.xhtml?faces-redirect=true";
            }
        } catch (NullPointerException m) {
            System.out.println("Error en authenticationControl: " + m.getMessage() + ": " + m.getLocalizedMessage());
            System.err.println(m.getMessage() + " -  " + m.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println("Error null login pointer en authenticationControl: " + e.getMessage() + ": " + e.getLocalizedMessage());
            System.err.println(e.getMessage() + " -  " + e.getLocalizedMessage());
        }
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciales invalidas", "usuario o contraseña invalido"));
        return "login.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

}
