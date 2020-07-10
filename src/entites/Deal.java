package entites;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dao.DealDaoImpl;

@DatabaseTable(tableName = "deal", daoClass = DealDaoImpl.class)
public class Deal {
	@DatabaseField(generatedId = true)
	private Integer pid;
	@DatabaseField()
	private Integer count;
	@DatabaseField()
	private DealState state;
	@DatabaseField(foreign = true)
	private Customer customer;
	@DatabaseField(foreign = true)
	private Order order;
	
	Deal(){}
}
