package dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import entites.Tender;

public class TenderDaoImpl extends BaseDaoImpl<Tender, Integer> implements TenderDao{

	public TenderDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Tender.class);
		// TODO Auto-generated constructor stub
	}

}
