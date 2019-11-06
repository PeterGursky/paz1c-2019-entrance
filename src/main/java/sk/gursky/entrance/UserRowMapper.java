package sk.gursky.entrance;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import sk.gursky.entrance.storage.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setChipId(rs.getString("chip_id"));
		user.setName(rs.getString("name"));
		user.setAdmin(rs.getBoolean("admin"));
		return user;
	}

}
