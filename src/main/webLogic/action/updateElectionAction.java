package webLogic.action;

import OnlineVoter.VotingListInfo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import webLogic.model.HeyBean;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class updateElectionAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String title = null, description = null, department = null, stTimeVal = null, endTimeVal, uidS;
    private Calendar stTime = Calendar.getInstance(), endTime = Calendar.getInstance();

    @Override
    public String execute() {
        // any username is accepted without confirmation (should check using RMI)
        if (this.uidS != null && !uidS.equals("") &&this.title != null && !title.equals("") && this.description != null && !description.equals("") && this.department != null && !department.equals("") && this.stTimeVal != null && !stTimeVal.equals("") && this.endTimeVal != null && !endTimeVal.equals("")) {
            long uid;
            int type;
            String[] date_fields;
            try {
                uid = Long.parseLong(uidS);
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


            this.getHeyBean().updateElection(uid, stTime, endTime, description, title, department);
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

    public void setUidS(String uidS) {
        this.uidS = uidS;
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
