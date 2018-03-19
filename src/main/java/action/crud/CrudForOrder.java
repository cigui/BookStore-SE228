package action.crud;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.BaseAction;
import model.Order;
import service.OrderService;
import utils.JsonBean;

public class CrudForOrder extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> resultMap;
	private List<JsonBean> resultList;
	private OrderService orderService;
	private int user_id;
	private Timestamp order_time;
	private int id;
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public List<JsonBean> getResultList() {
		return resultList;
	}
	public void setResultList(List<JsonBean> resultList) {
		this.resultList = resultList;
	}
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String All() throws Exception {
		List<Order> orders = orderService.getAllOrders();
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("total", orders.size());  
		resultMap.put("rows", orders);
		return "all";
	}
	
	public String Add() throws Exception {
		Order order = new Order(user_id, order_time);
		orderService.addOrder(order);
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("success", true);
		return "add";
	}
	
	public String Delete() throws Exception {
		Order order = orderService.getOrderById(id);
		orderService.deleteOrder(order);
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("success", true);
		return "delete";
	}
	
	public String Update() throws Exception {
		Order order = orderService.getOrderById(id);
		order.setUser_id(user_id);
		orderService.updateOrder(order);
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("success", true);
		return "update";
	}
	
	public String IdList() throws Exception {
		List<Integer> orders = orderService.getAllOrdersId();
		resultList = new ArrayList<JsonBean>();
		for (int i = 0; i < orders.size(); i++) {
			JsonBean tmp = new JsonBean(orders.get(i).toString(), orders.get(i).toString());
			resultList.add(tmp);
		}
		return "idList";
	}
	
}
