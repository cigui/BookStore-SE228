package action.statisticsActions;

import java.sql.Date;
import java.util.List;

import action.BaseAction;
import model.Order;
import service.OrderService;
import service.OrderitemService;

public class StatisticsByDatesAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date startingDate;
	private Date endingDate;
	private OrderService orderService;
	private OrderitemService orderitemService;
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	public Date getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
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
		List<Order> orders = orderService.getOrdersBetween(startingDate, endingDate);
		request().setAttribute("staticsResults", orders);
		request().setAttribute("items", orderitemService.getOrderitemsOfOrders(orders));
		return SUCCESS;
	}
}
