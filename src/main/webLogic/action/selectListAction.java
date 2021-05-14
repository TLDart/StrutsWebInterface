package webLogic.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import webLogic.model.HeyBean;

import java.util.Calendar;
import java.util.Map;

public class selectListAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String uidS = null;

    @Override
    public String execute() {
        // any username is accepted without confirmation (should check using RMI)
        if (this.uidS != null && !uidS.equals("")) {
            int uid;
            try {
                uid = Integer.parseInt(uidS);
            } catch (Exception e) {
                e.printStackTrace();
                return "failure";
            }
             return this.getHeyBean().setElectionToVote(uid);
        } else{
            return "failure";
        }
    }

    public void setUidS(String uidS) {
        this.uidS = uidS;
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
