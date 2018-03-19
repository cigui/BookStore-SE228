package action.shoppingCartActions;

import java.util.HashMap;
import java.util.Map;

import action.BaseAction;
import model.ShoppingCartItem;
import service.ShoppingCartService;

public class DeleteFromShoppingCartAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int userId;
	private long isbn;
	private ShoppingCartService shoppingCartService;
	private Map<String, Object> resultMap;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}
	public void setShoppingCartService(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}
	
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public String execute() {
		resultMap = new HashMap<String, Object>();
		try {
			ShoppingCartItem item = shoppingCartService.getItemByKey(userId, isbn);
			shoppingCartService.deleteFromShoppingCart(item);
			resultMap.put("success", true);
			return SUCCESS;
		}
		catch (Exception e) {
			e.printStackTrace();
			resultMap.put("error", "undefined");
			return ERROR;
		}
	}
	
}
