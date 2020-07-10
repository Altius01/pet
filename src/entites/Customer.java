package entites;

import java.util.Objects;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import dao.CustomerDaoImpl;

@DatabaseTable(tableName = "customer", daoClass = CustomerDaoImpl.class)
public class Customer {
	@DatabaseField(generatedId = true)
	private Integer pid;
	@DatabaseField(unique = true)
	private String name;
	@DatabaseField(unique = true)
	private String mail;
	@ForeignCollectionField()
	ForeignCollection<Order> orders;
	@ForeignCollectionField()
	ForeignCollection<Order> deals;
	
	public Customer(){}
	
	public void setName(String name) {
		this.name = Objects.requireNonNull(name, "Name must not be Null!"); 
	}
	
	public String getName() {
		return this.name;
	}
}
