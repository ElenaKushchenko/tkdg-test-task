package com.epam.tkdg.testtask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    private BigInteger id;
    private String isin;
    private BigDecimal bid;
    private BigDecimal ask;
    private Integer bidSize;
    private Integer askSize;
}
