package entites;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "order")
public class Order {
	@DatabaseField(generatedId = true)
	private Integer pid;
	@DatabaseField()
	private Integer count;
	@DatabaseField(foreign = true)
	private Customer customer;
	@DatabaseField(foreign = true)
	private Order order;
}
