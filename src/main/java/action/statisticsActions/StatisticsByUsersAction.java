package action.statisticsActions;

import java.util.List;

import action.BaseAction;
import model.Order;
import service.OrderService;
import service.OrderitemService;

public class StatisticsByUsersAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] userId;
	private OrderService orderService;
	private OrderitemService orderitemService;
	public int[] getUserId() {
		return userId;
	}
	public void setUserId(int[] userId) {
		this.userId = userId;
	}
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
	public String execute() {
		List<Order> orders = orderService.getOrdersOfUsers(userId);
		request().setAttribute("staticsResults",orders);
		request().setAttribute("items", orderitemService.getOrderitemsOfOrders(orders));
		return SUCCESS;
	}
}
