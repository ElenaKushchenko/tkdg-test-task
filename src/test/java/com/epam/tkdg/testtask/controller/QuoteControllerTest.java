package com.epam.tkdg.testtask.controller;

import com.epam.tkdg.testtask.model.Quote;
import com.epam.tkdg.testtask.service.QuoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.json.Json;
import javax.json.JsonObject;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QuoteController.class)
public class QuoteControllerTest {
    private static final String BASE_URL = "/quotes";
    private Quote testQuote;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteService service;


    @Before
    public void setup() {
        testQuote = new Quote(new BigInteger("1"), "AAAA9999BBBB", new BigDecimal(100), new BigDecimal(200), 1, 1);
    }

    @Test
    public void saveQuote() throws Exception {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("id", testQuote.getId())
                .add("isin", testQuote.getIsin())
                .add("bid", testQuote.getBid())
                .add("ask", testQuote.getAsk())
                .add("bidSize", testQuote.getBidSize())
                .add("askSize", testQuote.getAskSize())
                .build();

        mockMvc.perform(post(BASE_URL)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject.toString()))
                .andExpect(status().isOk());

        verify(service).save(testQuote);
    }
}