package entites;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "product")
public class Product {
	@DatabaseField(generatedId = true)
	private Integer pid;
	@DatabaseField()
	private String name;
	@DatabaseField()
	private Double price;
	@DatabaseField()
	private Integer count;
}
