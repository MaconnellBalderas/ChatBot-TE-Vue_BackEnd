package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcCurriculumDao implements CurriculumDao {
	
	private final static String SQL_SELECT_CURRICULUM = " SELECT id, topic, description, link FROM curriculum";
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcCurriculumDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Curriculum getCurriculumByTopic(String topic) {
		Curriculum curriculum = new Curriculum();
		String sql = SQL_SELECT_CURRICULUM + " WHERE topic = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, topic);

		while (rows.next()) {
			curriculum = mapRowToCurriculum(rows);
		}

		return curriculum;
	}
	
	@Override
	public List<Curriculum> getCurriculums() {
		List<Curriculum> allCurriculums = new ArrayList<Curriculum>();
		SqlRowSet rows = jdbcTemplate.queryForRowSet(SQL_SELECT_CURRICULUM);
		
		while (rows.next()) {
			allCurriculums.add(mapRowToCurriculum(rows));
		}
		return allCurriculums;
	}
	
	private Curriculum mapRowToCurriculum(SqlRowSet row) {
		Curriculum curriculum = new Curriculum();
		curriculum.setId(row.getInt("id"));
		curriculum.setTopic(row.getString("topic"));
		curriculum.setDescription(row.getString("description"));
		curriculum.setLink(row.getString("link"));
		return curriculum;
	}

}
