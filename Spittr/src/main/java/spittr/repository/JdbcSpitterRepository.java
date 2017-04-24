 package spittr.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import spittr.domain.Spitter;
@Repository
public class JdbcSpitterRepository implements SpitterRepository {
	

	private JdbcOperations jdbc;
	 
	
	@Autowired
	public JdbcSpitterRepository(JdbcOperations jdbc) {
		this.jdbc=jdbc;
	}

	@Override
	public long count() {
		return jdbc.queryForObject("SELECT Count(*) FROM Spitter", long.class);//long.class返回的是长整形
		
	}

	@Override
	public Spitter save(Spitter spitter) {
		jdbc.update(
		        "insert into Spitter (username, password, first_name, last_name,fullName,email,updateByEmail)" +
		        " values (?, ?, ?, ?, ?, ?,?)",
		        spitter.getUsername(),
		        spitter.getPassword(),
		        spitter.getFirstName(),
		        spitter.getLastName(),
		        spitter.getFullName(),		        
		        spitter.getEmail(),
		        false);
		return spitter;
	}
	@Override
	public Spitter findOne(long id) {
		return jdbc.queryForObject("SELECT id,username,password,first_name,last_name,fullName,email,updateByEmail FROM Spitter WHERE id=?", 
				 new SpitterRowMapper(),id);
	}
	

	@Override
	public Spitter findByUsername(String username) {
		return jdbc.queryForObject("select id, username, password, first_name, last_name,fullName, email,updateByEmail from Spitter where username=?",
				 new SpitterRowMapper(),username);
	}


	@Override
	public List<Spitter> findAll() {
		return jdbc.query(
				"SELECT id ,username,password ,first_name ,last_name,fullName,email,updateByEmail FROM Spitter", 
				 new SpitterRowMapper());
	}
	private static class SpitterRowMapper implements RowMapper<Spitter> {
		public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Spitter(
					rs.getLong("id"), 
					rs.getString("username"), 
					null, 
					rs.getString("first_name"),
					rs.getString("last_name"), 
					rs.getString("fullName"),
					rs.getString("email"),
					rs.getBoolean("updateByEmail"));
		}
	}

}
