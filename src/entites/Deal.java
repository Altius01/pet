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
	private Tender tender;
	@DatabaseField(foreign = true)
	private Supplier supplier;
	
	public Deal(){}
	
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

	public DealState getState() {
		return state;
	}

	public void setState(DealState state) {
		this.state = state;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Tender getTender() {
		return tender;
	}

	public void setTender(Tender tender) {
		this.tender = tender;
	}

}
