package com.epam.tkdg.testtask.controller;

import com.epam.tkdg.testtask.model.EnergyLevel;
import com.epam.tkdg.testtask.service.EnergyLevelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EnergyLevelController.class)
public class EnergyLevelControllerTest {
    private static final String BASE_URL = "/energy-levels";
    private EnergyLevel testElvl;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnergyLevelService service;


    @Before
    public void setup() {
        testElvl = new EnergyLevel("AAAA9999BBBB", new BigDecimal(100.5));
    }

    @Test
    public void getAll() throws Exception {
        when(service.getAll()).thenReturn(Collections.singletonList(testElvl));

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].isin").value(testElvl.getIsin()))
                .andExpect(jsonPath("$[0].value").value(testElvl.getValue()))
                .andReturn().getResponse();

        verify(service).getAll();
    }

    @Test
    public void getByIsin() throws Exception {
        when(service.getByIsin(testElvl.getIsin())).thenReturn(Optional.of(testElvl));

        mockMvc.perform(get(BASE_URL + "/{isin}", testElvl.getIsin()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.isin").value(testElvl.getIsin()))
                .andExpect(jsonPath("$.value").value(testElvl.getValue()))
                .andReturn().getResponse();

        verify(service).getByIsin(testElvl.getIsin());
    }
}