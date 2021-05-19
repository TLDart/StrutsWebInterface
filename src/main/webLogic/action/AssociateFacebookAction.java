package webLogic.action;

import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuthService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import webLogic.model.HeyBean;

import java.util.Map;


public class AssociateFacebookAction extends ActionSupport implements SessionAware {
    private Map<String, Object> session;
    private String code;
    private Token EMPTY_TOKEN = null;

    @Override
    public String execute(){
        String facebookId = this.getHeyBean().getFacebookId(this.code, 1);
        //associar o facebook id ao user que se conectou
        this.getHeyBean().saveFacebookId(facebookId);
        System.out.printf("facebookId: %s\n", facebookId);
        return "success";
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public HeyBean getHeyBean() {
        if(!session.containsKey("heyBean"))
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
