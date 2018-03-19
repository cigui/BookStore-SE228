package action.userActions;

import service.UserService;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import action.BaseAction;
import model.User;

public class LoginAction extends BaseAction implements SessionAware {

	private static final long serialVersionUID = 1L;
	private String email;
	private String pwd;
	private UserService userService;
	private Map<String, Object> session;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setUserService(UserService appService) {
		this.userService = appService;
	}
	
	public String execute() throws Exception{
		if (userService.login(email, pwd)) {
			User u = userService.getUserByEmail(email);
			session.put("logined", true);
			session.put("userName", u.getUsrName());
			session.put("userId", u.getId());
			if (userService.isAdmin(u)){
				session.put("isAdmin", true);
			}
			return SUCCESS;
		}
		return ERROR;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
