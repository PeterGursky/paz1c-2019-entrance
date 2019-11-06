package sk.gursky.entrance.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlUserDao implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	
	public MysqlUserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public User addUserOld(User user) {
		String sql = "INSERT INTO user (chip_id, name, admin) VALUES (?,?,?)";
		jdbcTemplate.update(sql, user.getChipId(), user.getName(), user.isAdmin());
		return user;
	}

	@Override
	public User addUser(User user) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName("user");
		simpleJdbcInsert.usingGeneratedKeyColumns("id");
		simpleJdbcInsert.usingColumns("chip_id", "name", "admin");
		
		Map<String, Object> values = new HashMap<>();
				values.put("chip_id", user.getChipId());
				values.put("name", user.getName());
				values.put("admin", user.isAdmin());
		long id = simpleJdbcInsert.executeAndReturnKey(values).longValue();
		user.setId(id);
		return user;
	}
	
	@Override
	public boolean validate(String chipId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getAll() {
//		String sql = "SELECT id, chip_id, name, admin from user";
//		return jdbcTemplate.query(sql, new UserRowMapper());
		String sql = "SELECT id, chip_id, name, admin, ucr.card_reader_id from user "
				+ "LEFT OUTER JOIN user_card_reader as ucr ON user.id = ucr.user_id";
		return jdbcTemplate.query(sql, new UserResultSetExtractor());
	}

	@Override
	public User getByChipId(long chipId) {
		String sql = "SELECT id, chip_id, name, admin, ucr.card_reader_id from user "
				+ "LEFT OUTER JOIN user_card_reader as ucr ON user.id = ucr.user_id "
				+ "WHERE user.chip_id = " + chipId;
		List<User> users = jdbcTemplate.query(sql, new UserResultSetExtractor());
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	private class UserResultSetExtractor implements ResultSetExtractor<List<User>> {

		@Override
		public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<CardReader> cr = DaoFactory.INSTANCE
									.getCardReaderDao().getAll();
			Map<Long, CardReader> map = new HashMap<Long, CardReader>();
			for(CardReader c: cr) {
				map.put(c.getId(), c);
			}
			
			List<User> users = new ArrayList<User>();
			User lastUser = null;
			while(rs.next()) {
				long id = rs.getLong("id");
				if (lastUser == null || lastUser.getId() != id) { 
					lastUser = new User();
					lastUser.setId(rs.getLong("id"));
					lastUser.setChipId(rs.getString("chip_id"));
					lastUser.setName(rs.getString("name"));
					lastUser.setAdmin(rs.getBoolean("admin"));
					users.add(lastUser);
				}
				Long idCR = rs.getLong("card_reader_id");
				if (! rs.wasNull()) {
					lastUser.getCardReaders().add(map.get(idCR));
				}
			}
			return users;
		}
	}
	
	@Override
	public User save(User user) {
		if (user == null)
			return null;
		if (user.getId() == null) { 		// INSERT
			SimpleJdbcInsert sjinsert = new SimpleJdbcInsert(jdbcTemplate);
			sjinsert.withTableName("user");
			sjinsert.usingGeneratedKeyColumns("id");
			sjinsert.usingColumns("name", "chip_id", "admin");
			
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("name", user.getName());
			values.put("chip_id", user.getChipId());
			values.put("admin", user.isAdmin());
			
			long id = sjinsert.executeAndReturnKey(values).longValue();
			user.setId(id);
			insertCardReaders(user);
		} else {							// UPDATE
			String sql = "UPDATE user SET name = ?, chip_id = ?, admin = ? WHERE "
					+ "id = " + user.getId();
			int deleted = jdbcTemplate.update(sql, user.getName(), user.getChipId(), user.isAdmin());
			if (deleted > 0) {
				String deleteSql = "DELETE FROM user_card_reader WHERE user_id = "
									+ user.getId();
				jdbcTemplate.update(deleteSql);
				insertCardReaders(user);
			}
		}
		return user;
	}

	private void insertCardReaders(User user) {
		if (user.getCardReaders() != null) {
			String sql = "INSERT INTO user_card_reader (user_id, card_reader_id) VALUES (?,?)";
			for (CardReader cr: user.getCardReaders()) {
				jdbcTemplate.update(sql, user.getId(), cr.getId());
			}
		}
	}
}
