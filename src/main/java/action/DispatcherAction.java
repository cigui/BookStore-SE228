package action;

import com.opensymphony.xwork2.ActionSupport;

public class DispatcherAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	public String Register() {
		return "register";
	}
	
	public String CrudUser() {
		return "crudUser";
	}
	
	public String CrudBook() {
		return "crudBook";
	}
	
	public String CrudOrder() {
		return "crudOrder";
	}
	
	public String CrudOrderitem() {
		return "crudOrderitem";
	}
}
