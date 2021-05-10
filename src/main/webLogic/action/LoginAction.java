/**
 * Raul Barbosa 2014-11-07
 */
package webLogic.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.util.Map;
import webLogic.model.HeyBean;

public class LoginAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private String username = null, password = null;

	@Override
	public String execute() {
		// any username is accepted without confirmation (should check using RMI)
		if(this.username != null && username.equals("admin") && this.password != null && password.equals("admin")) {
			this.getHeyBean().setUsername(this.username);
			this.getHeyBean().setPassword(this.password);
			return "admin";
		}
		else if(this.username != null && this.password != null ) {
			this.getHeyBean().setUsername(this.username);
			return "success";
		}
		else
			return "failure";
	}
	
	public void setUsername(String username) {
		this.username = username; // will you sanitize this input? maybe use a prepared statement?
	}

	public void setPassword(String password) {
		this.password = password; // what about this input? 
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
