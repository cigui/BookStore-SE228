package action.userActions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import action.BaseAction;
import model.User;
import service.UserService;

public class RegisterAction extends BaseAction implements SessionAware {

	private static final long serialVersionUID = 1L;

	private String usrName;
	private String pwd;
	private int phone;
	private String email;
	private UserService userService;
	private Map<String, Object> session;
	
	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserService(UserService appService) {
		this.userService = appService;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String execute() throws Exception {
		try {
			User user = new User(usrName, pwd, phone, email);
			if (!userService.register(user)) return ERROR;
			request().setAttribute("user", user);
			session.put("logined", true);
			session.put("userName", user.getUsrName());
			session.put("userId", user.getId()); 
			return SUCCESS;
		}
		catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}
