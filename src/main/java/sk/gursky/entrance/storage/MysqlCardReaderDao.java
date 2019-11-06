package sk.gursky.entrance.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MysqlCardReaderDao implements CarReaderDao  {
	
	private JdbcTemplate jdbcTemplate;
	
	public MysqlCardReaderDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<CardReader> getAll() {
		String sql = "SELECT id, name from card_reader";
		return jdbcTemplate.query(sql, new RowMapper<CardReader>() {

			@Override
			public CardReader mapRow(ResultSet rs, int rowNum) throws SQLException {
				CardReader cardReader =  new CardReader();
				cardReader.setId(rs.getLong("id"));
				cardReader.setName(rs.getString("name"));
				return cardReader;
			}
			
		});
	}
}
