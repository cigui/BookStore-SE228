package service;

import java.util.List;

import model.ShoppingCartItem;

public interface ShoppingCartService {
	public ShoppingCartItem getItemByKey(int userId, long isbn);
	
	public List<ShoppingCartItem> getShoppingCartOfUser(int userId);
	
	public void addIntoShoppingCart(ShoppingCartItem item);
	
	public void deleteFromShoppingCart(ShoppingCartItem item);
	
	public void updateItemInfo(ShoppingCartItem item);
	
	public boolean purchase(List<ShoppingCartItem> item);
}
