package com.techelevator.model;

import java.util.List;

public interface QuoteDao {
	
	List<Quote> getRandomQuote();
	
	int getCurrentQuoteCount();

}
