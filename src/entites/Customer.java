package entites;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "customer")
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
}
