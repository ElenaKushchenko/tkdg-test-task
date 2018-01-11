package com.epam.tkdg.testtask.dao;

import com.epam.tkdg.testtask.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class QuoteJdbcDao implements QuoteDao {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public QuoteJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void insert(final Quote quote) {
        final String sql = "INSERT INTO quote (isin, bid, ask, bidsize, asksize) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, quote.getIsin(), quote.getBid(), quote.getAsk(), quote.getBidSize(), quote.getAskSize());
    }
}
