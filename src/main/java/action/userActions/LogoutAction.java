package action.userActions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import action.BaseAction;

public class LogoutAction extends BaseAction implements SessionAware {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;

	public String execute() throws Exception {
		session.remove("logined");
		session.remove("userName");
		session.remove("isAdmin");
		return SUCCESS;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
