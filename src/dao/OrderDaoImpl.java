package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import entites.Order;

public class OrderDaoImpl extends BaseDaoImpl<Order, Integer> implements OrderDao{

	public OrderDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Order.class);
		// TODO Auto-generated constructor stub
	}

}
