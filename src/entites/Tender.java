package entites;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.ForeignCollectionField;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import dao.TenderDaoImpl;
import dao.SupplierDaoImpl;
import dao.TenderSupplierJoinDaoImpl;

@DatabaseTable(tableName = "tender", daoClass = TenderDaoImpl.class)
public class Tender {
	@DatabaseField(generatedId = true)
	private Integer pid;

	@DatabaseField()
	private Float volume;

	@DatabaseField(foreign = true)
	private User user;
    
	@DatabaseField(foreign = true)
	private Product product;
    
    @ForeignCollectionField()
	transient ForeignCollection<TenderSupplierJoin> suppliers;
	
	public Tender() {}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Float getVolume() {
		return volume;
	}

	public void setVolume(Float volume) {
		this.volume = volume;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

    public ForeignCollection<TenderSupplierJoin> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(ForeignCollection<TenderSupplierJoin> suppliers) {
        this.suppliers = suppliers;
    }

    public void setSuppliers(JdbcConnectionSource connectionSource, PreparedQuery query) throws SQLException, IOException {
        SupplierDaoImpl supplierDao = new SupplierDaoImpl(connectionSource);
        TenderSupplierJoinDaoImpl tsjoinDao = new TenderSupplierJoinDaoImpl(connectionSource);

        if (query == null) {
            QueryBuilder<Supplier, Integer> supplier_qb = supplierDao.queryBuilder();
            supplier_qb.where().eq("product_id", this.product.getPid());
            query = supplier_qb.prepare();
        }
        

        List<Supplier> suppliers = supplierDao.query(query);
        for (Supplier supplier: suppliers) {
            TenderSupplierJoin tsjoin = new TenderSupplierJoin();
            tsjoin.setTender(this);
            tsjoin.setSupplier(supplier);
            tsjoinDao.createIfNotExists(tsjoin);
        }
    }
}
