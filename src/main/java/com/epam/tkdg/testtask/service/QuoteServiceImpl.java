package com.epam.tkdg.testtask.service;

import com.epam.tkdg.testtask.model.Quote;
import com.epam.tkdg.testtask.dao.QuoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuoteServiceImpl implements QuoteService {
    private QuoteDao quoteDao;
    private EnergyLevelService energyLevelService;


    @Autowired
    public QuoteServiceImpl(QuoteDao quoteDao, EnergyLevelService energyLevelService) {
        this.quoteDao = quoteDao;
        this.energyLevelService = energyLevelService;
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void save(Quote quote) {
        quoteDao.insert(quote);
        energyLevelService.calculateActualEnergyLevel(quote);
    }
}
