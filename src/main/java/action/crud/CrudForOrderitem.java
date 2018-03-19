package action.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.BaseAction;
import model.Orderitem;
import service.OrderitemService;

public class CrudForOrderitem extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> resultMap;
	private OrderitemService orderitemService;
	private int order_id;
	private long book_ISBN;
	private int number;
	private int id;
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public OrderitemService getOrderitemService() {
		return orderitemService;
	}
	public void setOrderitemService(OrderitemService orderitemService) {
		this.orderitemService = orderitemService;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public long getBook_ISBN() {
		return book_ISBN;
	}
	public void setBook_ISBN(long book_ISBN) {
		this.book_ISBN = book_ISBN;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String All() throws Exception {
		List<Orderitem> orderitems = orderitemService.getAllOrderitems();
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("total", orderitems.size());  
		resultMap.put("rows", orderitems);
		return "all";
	}
	
	public String Add() throws Exception {
		Orderitem orderitem = new Orderitem(order_id, book_ISBN, number);
		resultMap = new HashMap<String, Object>(); 
		if (orderitemService.addOrderitem(orderitem) > 0) {
			resultMap.put("success", true);
		}
		return "add";
	}
	
	public String Delete() throws Exception {
		Orderitem orderitem = orderitemService.getOrderitemById(id);
		orderitemService.deleteOrderitem(orderitem);
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("success", true);
		return "delete";
	}
	
	public String Update() throws Exception {
		Orderitem orderitem = orderitemService.getOrderitemById(id);
		orderitem.setBook_ISBN(book_ISBN);
		orderitem.setNumber(number);
		orderitem.setOrder_id(order_id);
		orderitemService.updateOrderitem(orderitem);
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("success", true);
		return "update";
	}
}
