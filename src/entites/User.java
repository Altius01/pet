package entites;

import java.util.Objects;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import dao.UserDaoImpl;
import dao.SupplierDaoImpl;
import dao.TenderDaoImpl;

import com.google.gson.annotations.Expose;

@DatabaseTable(tableName = "user", daoClass = UserDaoImpl.class)
public class User {
	@DatabaseField(generatedId = true)
	private Integer pid;

	@DatabaseField(unique = true)
	private String name;

	@DatabaseField(unique = true)
	private String mail;
    
	@ForeignCollectionField()
	transient ForeignCollection<Tender> tenders;
	
	@ForeignCollectionField()
	transient ForeignCollection<Supplier> suppliers;

	public User(){}
	
	public void setName(String name) {
		this.name = Objects.requireNonNull(name, "Name must not be Null!"); 
	}
	
	public String getName() {
		return this.name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public ForeignCollection<Tender> getTender() {
		return tenders;
	}

	public void setTenders(ForeignCollection<Tender> tenders) {
		this.tenders = tenders;
	}

	public ForeignCollection<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(ForeignCollection<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

    public void addSupplier(Product product, JdbcConnectionSource connectionSource) throws SQLException, IOException {
		SupplierDaoImpl supplierDao = new SupplierDaoImpl(connectionSource);
        Supplier supplier = new Supplier();
		supplier.setUser(this);
		supplier.setProduct(product);
		supplierDao.createIfNotExists(supplier);
    }

    public void addTender(Product product, Float volume, JdbcConnectionSource connectionSource) throws SQLException, IOException {
        TenderDaoImpl tenderDao = new TenderDaoImpl(connectionSource);
        Tender tender = new Tender();
		tender.setUser(this);
		tender.setProduct(product);
        tender.setVolume(volume);
		tenderDao.createIfNotExists(tender);
    }
}
