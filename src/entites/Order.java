package entites;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import dao.OrderDaoImpl;

@DatabaseTable(tableName = "order", daoClass = OrderDaoImpl.class)
public class Order {
	@DatabaseField(generatedId = true)
	private Integer pid;
	@DatabaseField()
	private Integer count;
	@DatabaseField(foreign = true)
	private Customer customer;
	@DatabaseField(foreign = true)
	private Product product;
	
	public Order() {}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
