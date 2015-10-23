package itmm.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

@ManagedBean(name = "appB")
@ApplicationScoped
public class AppBean {

    public AppBean() {
    }

    public String getBaseUrl() {
        return MyUtil.baseUrl();
    }
}
