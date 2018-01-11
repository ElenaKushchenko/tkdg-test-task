package com.epam.tkdg.testtask.dao;

import com.epam.tkdg.testtask.model.EnergyLevel;

import java.util.List;
import java.util.Optional;

public interface EnergyLevelDao {
    List<EnergyLevel> getAll();
    Optional<EnergyLevel> getByIsin(String isin);
    void insert(EnergyLevel elvl);
    void update(EnergyLevel elvl);
}
