package webLogic.action;

import OnlineVoter.Person;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;
import webLogic.model.HeyBean;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

public class RegisterAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String name = null, password = null, department = null, address = null, phoneNr = null, role = null, ccNr = null, ccVal = null;
    private Calendar ccValidity = Calendar.getInstance();

    @Override
    public String execute() {
        // any username is accepted without confirmation (should check using RMI)
        System.out.println(name);
        if (this.name != null && !name.equals("") && this.password != null && !password.equals("") && this.department != null && !department.equals("") && this.address != null && !address.equals("") && this.phoneNr != null && !phoneNr.equals("") && this.role != null && !role.equals("") && this.ccNr != null && !ccNr.equals("")&& this.ccVal != null && !ccVal.equals("")) {
            int phoneNumber;
            int ccNrI;
            int type;
            try{
                phoneNumber = Integer.parseInt(phoneNr);
                ccNrI = Integer.parseInt(ccNr);
                type = Integer.parseInt(role);
                if (type != 0 && type != 1 && type != 2) return "failure";
                String[] date_fields = ccVal.split("/");
                System.out.println(Arrays.toString(date_fields));
                ccValidity.set(Integer.parseInt(date_fields[0]), Integer.parseInt(date_fields[1]) - 1,
                        Integer.parseInt(date_fields[2]));
                if (password.contains(";") || password.contains("|")) {
                    System.out.println("List's name is invalid, it cannot contain ';' or '|'");
                    return "failure";
                }
            }
            catch(Exception e ){
                e.printStackTrace();
                return "failure";
            }

            Person p = new Person(name, password, department, address, phoneNumber, ccNrI, type, ccValidity);
            this.getHeyBean().registerUser(p);
            return SUCCESS;
        } else
            return "failure";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password; // what about this input?
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCcNr(String ccNr) {
        this.ccNr = ccNr;
    }

    public void setCcVal(String ccVal) {
        this.ccVal = ccVal;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public void setRole(String role) {
        this.role = role;
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
