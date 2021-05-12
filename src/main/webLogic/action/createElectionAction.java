package webLogic.action;

import OnlineVoter.Person;
import OnlineVoter.VotingListInfo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import webLogic.model.HeyBean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class createElectionAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String title = null, description = null, department = null,  typeS = null, stTimeVal = null, endTimeVal;
    private Calendar stTime = Calendar.getInstance(), endTime = Calendar.getInstance();

    @Override
    public String execute() {
        // any username is accepted without confirmation (should check using RMI)
        if (this.title != null && !title.equals("") && this.description != null && !description.equals("") && this.department != null && !department.equals("") && this.typeS != null && !typeS.equals("") && this.stTimeVal != null && !stTimeVal.equals("") && this.endTimeVal != null && !endTimeVal.equals("")) {
            int type;
            String[] date_fields;
            try {
                type = Integer.parseInt(typeS);
                if (type != 0 && type != 1 && type != 2) return "failure";
                date_fields = stTimeVal.split("/");
                stTime.set(Integer.parseInt(date_fields[0]), Integer.parseInt(date_fields[1]) - 1,
                        Integer.parseInt(date_fields[2]), Integer.parseInt(date_fields[3]),
                        Integer.parseInt(date_fields[4]));
                date_fields = endTimeVal.split("/");
                endTime.set(Integer.parseInt(date_fields[0]), Integer.parseInt(date_fields[1]) - 1,
                        Integer.parseInt(date_fields[2]), Integer.parseInt(date_fields[3]),
                        Integer.parseInt(date_fields[4]));
                System.out.println(stTime.getTime());
                System.out.println(endTime.getTime());
                if (stTime.before(Calendar.getInstance())){
                    System.out.println("This nuts");
                    return "failure";
                }
                if (stTime != null && endTime != null) {
                    if (endTime.before(stTime)) {
                        System.out.println("asd");
                        return "failure";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "failure";
            }
            CopyOnWriteArrayList<VotingListInfo> validDeps = new CopyOnWriteArrayList<>();
            validDeps.add(new VotingListInfo("WEB"));



            this.getHeyBean().createElection(stTime, endTime, description, title, department, type, validDeps);
            return SUCCESS;
        } else{
            System.out.println("Here");
            return "failure";
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setTypeS(String typeS) {
        this.typeS = typeS;
    }

    public void setStTimeVal(String stTimeVal) {
        this.stTimeVal = stTimeVal;
    }

    public void setEndTimeVal(String endTimeVal) {
        this.endTimeVal = endTimeVal;
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
