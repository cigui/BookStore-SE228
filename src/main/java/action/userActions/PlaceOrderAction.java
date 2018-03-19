package action.userActions;

import java.util.HashMap;
import java.util.Map;

import action.BaseAction;
import service.PurchaseService;

public class PlaceOrderAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private int userId;
	private long bookId;
	private int number;
	private Map<String, Object> resultMap;

	private PurchaseService purchaseService;

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

	public PurchaseService getPurchaseService() {
		return purchaseService;
	}

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String execute() {
		try {
			int price = purchaseService.placeOrder(userId, bookId, number);
			resultMap = new HashMap<String, Object>();
			resultMap.put("success", true);
			if (price > 0) {
				return SUCCESS;
			} else if (price == -4) {
				resultMap.put("error", "stock");
				return SUCCESS;
			} else {
				resultMap.put("error", "undefined");
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("error", "undefined");
			return SUCCESS;
		}
	}
}
