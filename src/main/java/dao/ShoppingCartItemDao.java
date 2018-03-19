package dao;

import java.util.List;

import model.ShoppingCartItem;

public interface ShoppingCartItemDao {
	
	public ShoppingCartItem save(ShoppingCartItem scItem);

	public void delete(ShoppingCartItem scItem);

	public void update(ShoppingCartItem scItem);
	
	public ShoppingCartItem get(int userId, long isbn);
	
	public List<ShoppingCartItem> getShoppingItemsByUserId(int userId);
}
