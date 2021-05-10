package webLogic.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import webLogic.model.HeyBean;

import java.util.ArrayList;
import java.util.Map;

public class getElectionListAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private ArrayList<String> users;
    String department = null, typeS = null;
    int type;

    @Override
    public String execute() {
        // any username is accepted without confirmation (should check using RMI)
        System.out.println(department);
        System.out.println(typeS);
        if (this.department != null && !department.equals("") && this.typeS != null && !typeS.equals("")) {
           try{
               type = Integer.parseInt(typeS);
           }
           catch (Exception e){
              return ERROR;
           }
        }
        users = this.getHeyBean().getElectionList(department, type);
        return SUCCESS;
    }

    public HeyBean getHeyBean() {
        if (!session.containsKey("heyBean"))
            this.setHeyBean(new HeyBean());
        return (HeyBean) session.get("heyBean");
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setTypeS(String typeS) {
        this.typeS = typeS;
    }

    public void setHeyBean(HeyBean heyBean) {
        this.session.put("heyBean", heyBean);
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
