package spittr.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spittr.domain.Spittle;
@Repository
public class JdbcSpittleRepository implements SpittleRepository {

	private JdbcOperations jdbc;
	
	@Autowired
	public JdbcSpittleRepository(JdbcOperations jdbc) {
		super();
		this.jdbc = jdbc;
	}

	@Override
	public long count() {		
		return jdbc.queryForObject("select count(*) from Spittle", long.class);
	}

	@Override
	public List<Spittle> findRecent() {		
		return jdbc.query("SELECT id, message, created_at, latitude, longitude,spitter FROM Spittle limit 10",
				new SpittleRowMapper());
			
	}

	@Override
	public List<Spittle> findRecent(int count) {
		return jdbc.query("SELECT id, message, created_at, latitude, longitude,spitter FROM Spittle limit ?",
				new SpittleRowMapper(),count);
	}

	@Override
	public Spittle findOne(long id) {
		return jdbc.queryForObject("select id, message, created_at, latitude, longitude,spitter "
				+ "from Spittle where id = ?", 
				new SpittleRowMapper(),id);
	}

	@Override
	public Spittle save(Spittle spittle) {
		 jdbc.update(
		        "insert into Spittle (message, created_at, latitude, longitude,spitter) values (?, ?, ?, ?,?)",
		        spittle.getMessage(),
		        spittle.getTime(),
		        spittle.getLatitude(),
		        spittle.getLongitude(),
		        spittle.getSpitter());
		       
		 return spittle;
	}

	@Override
	public List<Spittle> findBySpitterId(long spitterId) {
		return
				jdbc.query("SELECT * FROM Spittle s inner join Spitter se on s.spitter=se.id where se.id=?",
				new SpittleRowMapper(),spitterId);
		
				
	}

	@Override
	public void delete(long id) {
		jdbc.update("delete from Spittle where id=?",id);
		
	}
	private static class SpittleRowMapper implements RowMapper<Spittle> {
	    public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
	      return new Spittle(
	          rs.getLong("id"),
	          rs.getString("message"), 
	          rs.getDate("created_at"), 
	          rs.getDouble("longitude"), 
	          rs.getDouble("latitude"),
	          rs.getLong("spitter"));
	    }
	  }
	
}
