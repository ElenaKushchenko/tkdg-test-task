package com.epam.tkdg.testtask.service;

import com.epam.tkdg.testtask.model.EnergyLevel;
import com.epam.tkdg.testtask.model.Quote;
import com.epam.tkdg.testtask.dao.EnergyLevelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EnergyLevelServiceImpl implements EnergyLevelService {
    private EnergyLevelDao energyLevelDao;


    @Autowired
    public EnergyLevelServiceImpl(EnergyLevelDao energyLevelDao) {
        this.energyLevelDao = energyLevelDao;
    }


    @Override
    public List<EnergyLevel> getAll() {
        return energyLevelDao.getAll();
    }

    @Override
    public Optional<EnergyLevel> getByIsin(final String isin) {
        return energyLevelDao.getByIsin(isin);
    }

    @Override
    public EnergyLevel calculateActualEnergyLevel(final Quote quote) {
        final Optional<EnergyLevel> currentElvl = energyLevelDao.getByIsin(quote.getIsin());
        final EnergyLevel result = new EnergyLevel(quote.getIsin());


        if (currentElvl.isPresent()) {
            final BigDecimal currentValue = currentElvl.get().getValue();

            if (currentValue.compareTo(quote.getBid()) < 0) {
                result.setValue(quote.getBid());
            } else if (currentValue.compareTo(quote.getAsk()) > 0) {
                result.setValue(quote.getAsk());
            }

            if (!currentValue.equals(result.getValue())) {
                energyLevelDao.update(result);
            }
        } else {
            if (quote.getBid() != null) {
                result.setValue(quote.getBid());
            } else {
                result.setValue(quote.getAsk());
            }
            energyLevelDao.insert(result);
        }

        return result;
    }
}
