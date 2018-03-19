package action.shoppingCartActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import action.BaseAction;
import model.ShoppingCartItem;
import service.ShoppingCartService;

public class PurchaseAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String json;
	private ShoppingCartService shoppingCartService;
	private int userId;
	private Map<String, Object> resultMap;
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}
	public void setShoppingCartService(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public String execute() {
		Gson gson = new Gson();
		resultMap = new HashMap<String, Object>();
		
		/* 
		 * NOTE:
		 * We MUST do a transform from double to long here
		 * using 'longValue()', because javascript doesn't
		 * support 'long' type, and the default type in js
		 * is 'double', and in java we can't force to
		 * transform from double to long.
		 * Looks ugly here, but seems necessary.
		 */
		@SuppressWarnings("unchecked")
		List<Double> doubles = gson.fromJson(json, List.class);
		List<Long> books = new ArrayList<Long>();
		for (int i = 0; i < doubles.size(); i++) {
			books.add(doubles.get(i).longValue());
		}
		
		
		List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
		for (int i = 0; i < books.size(); i++) {
			long isbn = books.get(i);
			items.add(shoppingCartService.getItemByKey(userId, isbn));
		}
		if (shoppingCartService.purchase(items)) {
			resultMap.put("success", true);
		}
		else {
			resultMap.put("error", "stock");
		}
		return SUCCESS;
	}
}
