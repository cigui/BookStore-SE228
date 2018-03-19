package dao.impl;

import java.util.List;

import model.Orderitem;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.OrderitemDao;

@SuppressWarnings("deprecation")
public class OrderitemDaoImpl extends HibernateDaoSupport implements
		OrderitemDao {

	public Integer save(Orderitem orderitem) {
		return (Integer) getHibernateTemplate().save(orderitem);
	}

	public void delete(Orderitem orderitem) {
		getHibernateTemplate().delete(orderitem);
	}

	public void update(Orderitem orderitem) {
		getHibernateTemplate().merge(orderitem);
	}

	public Orderitem getOrderitemById(int id) {
		@SuppressWarnings("unchecked")
		List<Orderitem> orderitems = (List<Orderitem>) getHibernateTemplate()
				.find("from Orderitem as oi where oi.id=?", id);
		Orderitem orderitem = orderitems.size() > 0 ? orderitems.get(0) : null;
		return orderitem;
	}
	
	public List<Orderitem> getOrderitemByOrderId(int id) {
		@SuppressWarnings("unchecked")
		List<Orderitem> orderitems = (List<Orderitem>) getHibernateTemplate()
				.find("from Orderitem as oi where oi.order_id=?", id);
		return orderitems;
	}

	public List<Orderitem> getAllOrderitems() {
		@SuppressWarnings("unchecked")
		List<Orderitem> orderitems = (List<Orderitem>) getHibernateTemplate()
				.find("from Orderitem");
		return orderitems;
	}

	@Override
	public List<Orderitem> getOrderitemByCategory(String category) {
		@SuppressWarnings("unchecked")
		List<Orderitem> orderitems = (List<Orderitem>) getHibernateTemplate()
				.find("from Orderitem as oi where oi.category=?", category);
		return orderitems;
	}

}
