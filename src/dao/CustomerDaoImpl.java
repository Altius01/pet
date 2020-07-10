package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import entites.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer, Integer> implements CustomerDao{

	public CustomerDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Customer.class);
		// TODO Auto-generated constructor stub
	}

}
