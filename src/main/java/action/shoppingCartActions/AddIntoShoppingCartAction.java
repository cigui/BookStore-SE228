package action.shoppingCartActions;

import java.util.HashMap;
import java.util.Map;

import action.BaseAction;
import model.ShoppingCartItem;
import service.ShoppingCartService;

public class AddIntoShoppingCartAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private int userId;
	private long bookId;
	private int number;
	private Map<String, Object> resultMap;
	
	private ShoppingCartService shoppingCartService;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}
	public void setShoppingCartService(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}
	public String execute() {
		resultMap = new HashMap<String, Object>();
		try {
			ShoppingCartItem item = new ShoppingCartItem(userId, bookId, number);
			shoppingCartService.addIntoShoppingCart(item);
			resultMap.put("success", true);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("error", "undefined");
			return SUCCESS;
		}
	}
	
}
