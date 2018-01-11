package com.epam.tkdg.testtask.controller;

import com.epam.tkdg.testtask.model.EnergyLevel;
import com.epam.tkdg.testtask.service.EnergyLevelService;
import com.epam.tkdg.testtask.service.EnergyLevelServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Api(description = "API for CRUD operations with Energy Levels")
@RestController
@RequestMapping("/energy-levels")
public class EnergyLevelController {
    private final EnergyLevelService service;


    @Autowired
    public EnergyLevelController(final EnergyLevelService service) {
        this.service = service;
    }


    @ApiOperation("Get all Energy Levels")
    @RequestMapping(method = GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<EnergyLevel> getAll() {
        return service.getAll();
    }

    @ApiOperation("Get Energy Level by ISIN")
    @RequestMapping(value = "/{isin}", method = GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public EnergyLevel getByIsin(@PathVariable("isin") final String isin) {
        return service.getByIsin(isin).orElse(null);
    }
}
