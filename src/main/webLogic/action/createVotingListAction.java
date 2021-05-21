package webLogic.action;

import OnlineVoter.VotingListInfo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import webLogic.model.HeyBean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class createVotingListAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String uidS = null, typeS = null, memberList = null, name = null;

    @Override
    public String execute() {
        // any username is accepted without confirmation (should check using RMI)
        if (this.uidS != null && !uidS.equals("") && this.typeS != null && !typeS.equals("") && this.memberList != null && !memberList.equals("") && this.name != null && !name.equals("") ) {
            int type;
            long uid;
            String[] parser = null;
            CopyOnWriteArrayList<String> members = new CopyOnWriteArrayList<String>();
            try {
                type = Integer.parseInt(typeS);
                uid = Long.parseLong(uidS);
                if (type != 0 && type != 1 && type != 2) return "failure";
                if (name.contains(";") || name.contains("|")){
                    System.out.println("List's name is invalid, it cannot contain ';' or '|'");
                    return "failure";
                }
                parser = memberList.split(",");
                members.addAll(Arrays.asList(parser));
            } catch (Exception e) {
                e.printStackTrace();
                return "failure";
            }


            this.getHeyBean().createVotingList(uid, name, type, members);
            return SUCCESS;
        } else{
            System.out.println("Here");
            return "failure";
        }
    }

    public void setUidS(String uidS) {
        this.uidS = uidS;
    }

    public void setTypeS(String typeS) {
        this.typeS = typeS;
    }

    public void setMemberList(String memberList) {
        this.memberList = memberList;
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
