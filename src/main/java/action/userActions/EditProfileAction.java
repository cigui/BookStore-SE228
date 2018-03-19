package action.userActions;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import action.BaseAction;
import model.User;
import service.UserService;

public class EditProfileAction extends BaseAction implements SessionAware  {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;

	private int id;
	private String usrName;
	private int phone;
	private String pwd;
	private UserService userService;
	private Map<String, Object> resultMap;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public String execute() {
		User user = userService.getUserById(id);
		resultMap = new HashMap<String, Object>();
		user.setPhone(phone);
		user.setPwd(pwd);
		user.setUsrName(usrName);
		userService.updateUser(user);
		session.put("userName", usrName);
		request().setAttribute("user", user);
		resultMap.put("success", true);
		return SUCCESS;
	}
}
