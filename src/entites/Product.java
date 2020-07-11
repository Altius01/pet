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
	
	public Product() {}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
