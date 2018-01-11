package com.epam.tkdg.testtask.dao;

import com.epam.tkdg.testtask.model.EnergyLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EnergyLevelJdbcDao implements EnergyLevelDao {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public EnergyLevelJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<EnergyLevel> getAll() {
        final String sql = "select * from energy_level";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EnergyLevel.class));
    }

    @Override
    public Optional<EnergyLevel> getByIsin(final String isin) {
        final String sql = "select * from energy_level where isin = ?";

        return jdbcTemplate.query(
                sql,
                rs -> rs.next() ? Optional.of(new BeanPropertyRowMapper<>(EnergyLevel.class).mapRow(rs, 1)) : Optional.empty(),
                isin
        );
    }

    @Override
    public void insert(EnergyLevel elvl) {
        final String sql = "INSERT INTO energy_level (isin, value) VALUES (?, ?)";

        jdbcTemplate.update(sql, elvl.getIsin(), elvl.getValue());
    }

    @Override
    public void update(final EnergyLevel elvl) {
        final String sql = "update energy_level set value = ? where isin = ?";

        jdbcTemplate.update(sql, elvl.getValue(), elvl.getIsin());
    }
}
