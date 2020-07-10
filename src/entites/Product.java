package entites;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dao.ProductDaoImpl;

@DatabaseTable(tableName = "product", daoClass = ProductDaoImpl.class)
public class Product {
	@DatabaseField(generatedId = true)
	private Integer pid;
	@DatabaseField()
	private String name;
	@DatabaseField()
	private Double price;
	@DatabaseField()
	private Integer count;
	
	Product() {}
}
