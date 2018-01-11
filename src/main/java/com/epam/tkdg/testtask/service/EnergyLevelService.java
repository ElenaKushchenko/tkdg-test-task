package com.epam.tkdg.testtask.service;

import com.epam.tkdg.testtask.model.EnergyLevel;
import com.epam.tkdg.testtask.model.Quote;

import java.util.List;
import java.util.Optional;

public interface EnergyLevelService {
    List<EnergyLevel> getAll();
    Optional<EnergyLevel> getByIsin(String isin);
    EnergyLevel calculateActualEnergyLevel(Quote quote);
}
