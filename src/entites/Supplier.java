package entites;

import java.util.Objects;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import dao.SupplierDaoImpl;

@DatabaseTable(tableName = "supplier", daoClass = SupplierDaoImpl.class)
public class Supplier {
	@DatabaseField(generatedId = true)
	private Integer pid;
    
    @DatabaseField(foreign = true)
    private User user;

    @DatabaseField(foreign = true)
    private Product product;

	@ForeignCollectionField()
	transient ForeignCollection<TenderSupplierJoin> tenders;

	@ForeignCollectionField()
	transient ForeignCollection<Deal> deals;
	
	public Supplier(){}
	
	public void setUser(User user) {
		this.user = user; 
	}
	
	public User getUser() {
		return this.user;
	}

    public void setProduct(Product product) {
		this.product = product; 
	}
	
	public Product getProduct() {
		return this.product;
	}    
    
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public ForeignCollection<TenderSupplierJoin> getTenders() {
		return tenders;
	}

	public void setTenders(ForeignCollection<TenderSupplierJoin> tenders) {
		this.tenders = tenders;
	}

	public ForeignCollection<Deal> getDeals() {
		return deals;
	}

	public void setDeals(ForeignCollection<Deal> deals) {
		this.deals = deals;
	}
}
