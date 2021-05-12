package webLogic.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import webLogic.model.HeyBean;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class addTableAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String uidS = null, name = null;

    @Override
    public String execute() {
        // any username is accepted without confirmation (should check using RMI)
        if (this.uidS != null && !uidS.equals("") && this.name != null && !name.equals("") ) {
            long uid;
            CopyOnWriteArrayList<String> members = new CopyOnWriteArrayList<String>();
            try {
                uid = Long.parseLong(uidS);
                if (name.contains(";") || name.contains("|")){
                    System.out.println("List's name is invalid, it cannot contain ';' or '|'");
                    return "failure";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "failure";
            }


            this.getHeyBean().updateTable(name, uid, 0);
            return SUCCESS;
        } else{
            System.out.println("Here");
            return "failure";
        }
    }

    public void setUidS(String uidS) {
        this.uidS = uidS;
    }

    public void setName(String name) {
        this.name = name;
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
