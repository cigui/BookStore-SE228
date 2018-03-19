package dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.ShoppingCartItemDao;
import model.ShoppingCartItem;

@SuppressWarnings("deprecation")
public class ShoppingCartItemDaoImpl extends HibernateDaoSupport implements ShoppingCartItemDao {

	@Override
	public ShoppingCartItem save(ShoppingCartItem scItem) {
		return (ShoppingCartItem) getHibernateTemplate().save(scItem);
	}

	@Override
	public void delete(ShoppingCartItem scItem) {
		getHibernateTemplate().delete(scItem);
	}

	@Override
	public void update(ShoppingCartItem scItem) {
		getHibernateTemplate().merge(scItem);
	}

	@Override
	public List<ShoppingCartItem> getShoppingItemsByUserId(int UserId) {
		@SuppressWarnings("unchecked")
		List<ShoppingCartItem> items = (List<ShoppingCartItem>) getHibernateTemplate()
				.find("from ShoppingCartItem where user_id=?", UserId);
		return items;
	}

	@Override
	public ShoppingCartItem get(int userId, long isbn) {
		@SuppressWarnings("unchecked")
		List<ShoppingCartItem> items = (List<ShoppingCartItem>) getHibernateTemplate()
				.find("from ShoppingCartItem where user_id=? and book_id=?", userId, isbn);
		ShoppingCartItem item = items.size() > 0 ? items.get(0) : null;
		return item;
	}

}
