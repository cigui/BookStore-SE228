package action.statisticsActions;

import java.util.List;

import action.BaseAction;
import model.Orderitem;
import service.OrderService;
import service.OrderitemService;

public class StatisticsByCategoriesAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService;
	private OrderitemService orderitemService;
	private String[] categories;
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public OrderitemService getOrderitemService() {
		return orderitemService;
	}

	public void setOrderitemService(OrderitemService orderitemService) {
		this.orderitemService = orderitemService;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	
	public String execute() {
		List<Orderitem> items = orderitemService.getOrderitemsOfCategories(categories);
		request().setAttribute("items", items);
		request().setAttribute("staticsResults", orderService.getOrdersOfItems(items));
		return SUCCESS;
	}
}
