package webLogic.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import webLogic.model.HeyBean;

import java.util.ArrayList;
import java.util.Map;

public class showElectionData extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String response;
    String uidS = null;

    @Override
    public String execute() {
        // any username is accepted without confirmation (should check using RMI)
        if (this.uidS != null && !uidS.equals("")) {
            long uid;
           try{
               uid = Long.parseLong(uidS);
           }
           catch (Exception e){
              return ERROR;
           }
            response = this.getHeyBean().showFinishedElectionData(uid);
            return SUCCESS;
        }
        else{
            return "failure";
        }
    }

    public HeyBean getHeyBean() {
        if (!session.containsKey("heyBean"))
            this.setHeyBean(new HeyBean());
        return (HeyBean) session.get("heyBean");
    }

    public void setUidS(String uidS) {
        this.uidS = uidS;
    }

    public void setHeyBean(HeyBean heyBean) {
        this.session.put("heyBean", heyBean);
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
