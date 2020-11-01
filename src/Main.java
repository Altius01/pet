import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;

import entites.User;
import entites.Deal;
import entites.Tender;
import entites.Product;
import entites.Supplier;
import entites.TenderSupplierJoin;

import dao.UserDaoImpl;
import dao.DealDaoImpl;
import dao.TenderDaoImpl;
import dao.ProductDaoImpl;
import dao.SupplierDaoImpl;
import dao.TenderSupplierJoinDaoImpl;

import com.google.gson.Gson;

public class Main {
	
	static String[] names = {"John", "Garry", "Lanny", "Joe", "Jessie", "Clara"};
	static String[] surnames = {"Ivanov", "Smirnov", "Pirogov"};
	static String[] prod_names = {"Bread", "Fish", "Eggs"};
	
    public static void main(String[] args) throws SQLException, IOException {
        private Main = new Main();
        main_(args);
    }

	public void main_(String[] args) throws SQLException, IOException {
		String dataBaseUrl = "jdbc:sqlite:sample.db";
		JdbcPooledConnectionSource connectionSource;
        connectionSource = new JdbcPooledConnectionSource(dataBaseUrl);
        connectionSource.setMaxConnectionAgeMillis(5 * 60 * 1000);

        createTables(connectionSource);

		UserDaoImpl userDao = new UserDaoImpl(connectionSource);
        
		for (String name: names) {
			for (String surname: surnames) {
				User customer = new User();
				customer.setName(name + ' ' + surname);
                signUp();
			}
		}
		
		ProductDaoImpl productDao = new ProductDaoImpl(connectionSource);
		TenderDaoImpl tenderDao = new TenderDaoImpl(connectionSource);
		
		for (String name: prod_names) {
			Product product = new Product();
			product.setName(name);
			product.setPrice(100.50);
			product.setCount(100500);
			productDao.createIfNotExists(product);
		}

        QueryBuilder<Product, Integer> product_qb = productDao.queryBuilder();
		product_qb.where().eq("name", "Fish");

        QueryBuilder<User, Integer> user_qb = userDao.queryBuilder();
		user_qb.where().eq("name", "Garry Ivanov");
		User supp = userDao.queryForFirst(user_qb.prepare());
        supp.addSupplier(productDao.queryForFirst(product_qb.prepare()), connectionSource);

        user_qb.where().eq("name", "John Ivanov");
		User customer = userDao.queryForFirst(user_qb.prepare());

        customer.addTender(productDao.queryForFirst(product_qb.prepare()), 100.00f, connectionSource);
        
        Tender tender = tenderDao.queryForFirst(tenderDao.queryBuilder().where().eq("product_id", productDao.queryForFirst(product_qb.prepare()).getPid()).prepare());
        
        tender.setSuppliers(connectionSource, null);
        
        Gson gson = new Gson();
        
        System.out.println(gson.toJson(productDao.queryForAll()));
        
        /*
		Order order = new Order();
		QueryBuilder<Customer, Integer> customer_qb = customerDao.queryBuilder();
		customer_qb.where().eq("name", "John Ivanov");
		Customer customer = customerDao.queryForFirst(customer_qb.prepare());
		// Query for customer by name
		QueryBuilder<Product, Integer> product_qb = productDao.queryBuilder();
		product_qb.where().eq("name", "Fish");
		Product product = productDao.queryForFirst(product_qb.prepare());
		// Query for product by name
		order.setCustomer(customer);
		order.setProduct(product);
		orderDao.create(order);
		
		customerDao.refresh(customer);
		productDao.refresh(product);
		for (Order o: customer.getOrders()) {
			System.out.println("Order: " + o.getCustomer().getName() + " " + o.getProduct().getName());
			 
			 * Почему-то выводит название продукта для этого заказа, как null, 
			 * хотя id выводит 26, и это id для товара Fish.
			
		}
        */
    }

    private void createTables(JdbcConnectionSource connectionSource) {
        TableUtils.createTableIfNotExists(connectionSource, Tender.class);
		TableUtils.clearTable(connectionSource, Tender.class);

        TableUtils.createTableIfNotExists(connectionSource, Product.class);
		TableUtils.clearTable(connectionSource, Product.class);

        TableUtils.createTableIfNotExists(connectionSource, Deal.class);
		TableUtils.clearTable(connectionSource, Deal.class);

        TableUtils.createTableIfNotExists(connectionSource, User.class);
		TableUtils.clearTable(connectionSource, User.class);

        TableUtils.createTableIfNotExists(connectionSource, Supplier.class);
		TableUtils.clearTable(connectionSource, Supplier.class);

        TableUtils.createTableIfNotExists(connectionSource, TenderSupplierJoin.class);
		TableUtils.clearTable(connectionSource, TenderSupplierJoin.class);
    }

    public void signUp(JdbcConnectionSource connectionSource, , User user) {
        UserDaoImpl userDao = new UserDaoImpl(connectionSource);
        userDao.createIfNotExists(user);
    }
}
