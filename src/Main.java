import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;

import dao.CustomerDaoImpl;
import dao.OrderDaoImpl;
import dao.ProductDaoImpl;
import entites.Customer;
import entites.Deal;
import entites.Order;
import entites.Product;

public class Main {
	
	static String[] names = {"John", "Garry", "Lanny", "Joe", "Jessie", "Clara"};
	static String[] surnames = {"Ivanov", "Smirnov", "Pirogov"};
	static String[] prod_names = {"Bread", "Fish", "Eggs"};
	
	public static void main(String[] args) throws SQLException, IOException {
		String dataBaseUrl = "jdbc:sqlite:sample.db";
		JdbcConnectionSource connectionSource;
		connectionSource = new JdbcConnectionSource(dataBaseUrl);
		CustomerDaoImpl customerDao = new CustomerDaoImpl(connectionSource);
		TableUtils.createTableIfNotExists(connectionSource, Customer.class);
		TableUtils.clearTable(connectionSource, Customer.class);
		TableUtils.createTableIfNotExists(connectionSource, Product.class);
		TableUtils.clearTable(connectionSource, Product.class);
		TableUtils.createTableIfNotExists(connectionSource, Order.class);
		TableUtils.clearTable(connectionSource, Order.class);
		TableUtils.createTableIfNotExists(connectionSource, Deal.class);
		TableUtils.clearTable(connectionSource, Deal.class);
		
		for(String name: names) {
			for (String surname: surnames) {
				Customer customer = new Customer();
				customer.setName(name + ' ' + surname);
				customerDao.createIfNotExists(customer);
			}
		}
		
		ProductDaoImpl productDao = new ProductDaoImpl(connectionSource);
		OrderDaoImpl orderDao = new OrderDaoImpl(connectionSource);
		
		for (String name: prod_names) {
			Product product = new Product();
			product.setName(name);
			product.setPrice(100.50);
			product.setCount(100500);
			productDao.createIfNotExists(product);
		}
		
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
			/* 
			 * Почему-то выводит название продукта для этого заказа, как null, 
			 * хотя id выводит 26, и это id для товара Fish.
			*/
		}
	}
}
