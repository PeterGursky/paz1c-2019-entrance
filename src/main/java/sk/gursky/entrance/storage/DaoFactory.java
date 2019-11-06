package sk.gursky.entrance.storage;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

public enum DaoFactory {
	INSTANCE;

	private JdbcTemplate jdbcTemplate;
	private UserDAO userDao;
	private CarReaderDao carReaderDao;
	
	public CarReaderDao getCardReaderDao() {
		if (carReaderDao == null) {
			carReaderDao = new MysqlCardReaderDao(getJdbcTemplate());
		}
		return carReaderDao;
	}
	
	public UserDAO getUserDao() {
		if (userDao == null) {
//			userDao = new MemoryUserDAO();
			userDao = getMysqlUserDao();
		}
		return userDao;
	}
	
	private JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setDatabaseName("entrance_system");
			dataSource.setUser("paz1c");
			dataSource.setPassword("paz1cjesuper");
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}
	
	private MysqlUserDao getMysqlUserDao() {
		return new MysqlUserDao(getJdbcTemplate());
	}
	
}
