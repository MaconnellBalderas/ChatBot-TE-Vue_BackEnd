package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcPathwayDao implements PathwayDao {

	private final static String SQL_SELECT_PATHWAY = " SELECT id, topic, description, link FROM pathway";
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcPathwayDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Pathway> pathways() {
		List<Pathway> allPathways = new ArrayList<>();
		String sql = SQL_SELECT_PATHWAY;
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		while (rows.next()) {
			allPathways.add( mapRowToPathway(rows));
		}
		return allPathways;
	}

//	@Override
//	public Pathway getPathwayByTopic(String topic) {
//		Pathway pathway = new Pathway();
//		String sql = SQL_SELECT_PATHWAY + " WHERE topic = ?";
//		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, topic);
//
//		while (rows.next()) {
//			pathway = mapRowToPathway(rows);
//		}
//
//		return pathway;
//	}
	
	private Pathway mapRowToPathway(SqlRowSet row) {
		Pathway pathway = new Pathway();		
		pathway.setId(row.getInt("id"));
		pathway.setTopic(row.getString("topic"));
		pathway.setDescription(row.getString("description"));
		pathway.setLink(row.getString("link"));
		return pathway;
	}
}
