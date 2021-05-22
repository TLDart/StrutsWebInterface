package webLogic.action;

import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuthService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import webLogic.model.HeyBean;

import java.util.Map;

public class LoginWithFacebookAction extends ActionSupport implements SessionAware {
    private Map<String, Object> session;
    private String code;
    private Token EMPTY_TOKEN = null;
    private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/me";

    @Override
    public String execute(){
        String facebookId = this.getHeyBean().getFacebookId(this.code, 0);
        System.out.printf("facebookId: %s\n", facebookId);
        //verificar se ha algum utilizador que tenha este id associado a sua conta
        boolean result = this.getHeyBean().verifyFacebookLogin(facebookId);
        if (!result){
            this.session.put("login", false);
            return "failure";
        }
        this.session.put("login", true);
        return "success";
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

    public void setCode(String code){
        this.code = code;
    }
}
