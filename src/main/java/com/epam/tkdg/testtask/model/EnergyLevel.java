package com.epam.tkdg.testtask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnergyLevel {
    private String isin;
    private BigDecimal value;


    public EnergyLevel(String isin) {
        this.isin = isin;
    }
}
