package action.userActions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import action.BaseAction;
import model.User;
import service.UserService;

public class ProfileAction extends BaseAction implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService appService) {
		this.userService = appService;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	
	public String execute(){
		try {
			int userId = (Integer) session.get("userId");
			User u = userService.getUserById(userId);
			request().setAttribute("user", u);
			return SUCCESS;
		}
		catch (Exception e) {
			return ERROR;
		}
	}
}
