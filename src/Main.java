import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import dao.CustomerDaoImpl;
import entites.Customer;

public class Main {
	
	static String[] names = {"John", "Garry", "Lanny", "Joe", "Jessie", "Clara"};
	static String[] surnames = {"Ivanov", "Smirnov", "Pirogov"};
	
	public static void main(String[] args) throws SQLException, IOException {
		String dataBaseUrl = "jdbc:sqlite:sample.db";
		JdbcConnectionSource connectionSource;
		connectionSource = new JdbcConnectionSource(dataBaseUrl);
		CustomerDaoImpl customerDao = new CustomerDaoImpl(connectionSource);
		TableUtils.createTableIfNotExists(connectionSource, Customer.class);
		
		for(String name: names) {
			for (String surname: surnames) {
				Customer customer = new Customer();
				customer.setName(name + ' ' + surname);
				customerDao.create(customer);
			}
		}
		connectionSource.close();
	}
}
