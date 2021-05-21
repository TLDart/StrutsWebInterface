package webLogic.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import uc.sd.apis.FacebookApi2;
import webLogic.model.HeyBean;

public class LoginAction extends ActionSupport implements SessionAware {
    private Map<String, Object> session;
    private String ccs = null, password = null;

    @Override
    public String execute() {
        // any username is accepted without confirmation (should check using RMI)
        this.session.put("login", false);
        this.session.put("admin", false);
        if (this.ccs != null && ccs.equals("admin") && this.password != null && password.equals("admin")) {
            this.getHeyBean().setUsername(this.ccs);
            this.getHeyBean().setPassword(this.password);
            session.put("admin", true);
            this.session.put("login", false);
            System.out.println("iN admin");
            return "admin";
        } else if (this.ccs != null && this.password != null) {
            try {
                this.getHeyBean().setUsername(this.ccs);
                this.getHeyBean().setPassword(this.password);
                int cc = Integer.parseInt(ccs);
                this.session.put("login", true);
                this.session.put("admin", false);
                return this.getHeyBean().checkValidUser(cc, password);
            } catch (Exception e) {
                this.session.put("login", false);
                this.session.put("admin", false);
                return "failure";
            }
        } else {
            this.session.put("login", false);
            this.session.put("admin", false);
            return "failure";

        }
    }

    public void setCcs(String ccs) {
        this.ccs = ccs;
    }

    public void setPassword(String password) {
        this.password = password; // what about this input?
    }

    public HeyBean getHeyBean() {
        if (!session.containsKey("heyBean"))
            this.setHeyBean(new HeyBean());
        return (HeyBean) session.get("heyBean");
    }

    public void setHeyBean(HeyBean heyBean) {
        this.session.put("heyBean", heyBean);
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
