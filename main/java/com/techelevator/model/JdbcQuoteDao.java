package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcQuoteDao implements QuoteDao {
	
	private final static String SQL_SELECT_QUOTE = "SELECT id, quote FROM quotes";
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcQuoteDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int getCurrentQuoteCount() {
		int idCounter = 1;
		String sql = SQL_SELECT_QUOTE;
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		while (rows.next()) {
			idCounter++;
		}
		
		return idCounter;
	}
	
	private int randomizeId() {
		Random random = new Random();
		
		int number = random.nextInt(getCurrentQuoteCount());
		
		return number;
	}

	@Override
	public List<Quote> getRandomQuote() {
		List <Quote> quotes = new ArrayList<Quote>();
		Quote quote = new Quote();
		String sql = SQL_SELECT_QUOTE + " WHERE id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, randomizeId());
		
		while (rows.next()) {
		
		quote = mapRowToQuote(rows);
		quotes.add(quote);
		}
		return quotes;
	}

	private Quote mapRowToQuote(SqlRowSet row) {
		Quote quote = new Quote();
		quote.setId(row.getLong("id"));
		quote.setQuote(row.getString("quote"));
		return quote;
	}

}
