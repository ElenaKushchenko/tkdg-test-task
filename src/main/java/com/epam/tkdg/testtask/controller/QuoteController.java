package com.epam.tkdg.testtask.controller;

import com.epam.tkdg.testtask.model.Quote;
import com.epam.tkdg.testtask.service.QuoteService;
import com.epam.tkdg.testtask.validator.QuoteValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Api(description = "API for CRUD operations with Quotes")
@RestController
@RequestMapping("/quotes")
public class QuoteController {
    private final QuoteService service;


    @Autowired
    public QuoteController(QuoteService service) {
        this.service = service;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private List<FieldError> validationErrorHandler(MethodArgumentNotValidException ex) {
       return ex.getBindingResult().getFieldErrors();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new QuoteValidator());
    }

    @ApiOperation("Save new Quote")
    @ApiResponses( {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @RequestMapping(method = POST)
    public void saveQuote(@Validated @RequestBody Quote quote) {
        service.save(quote);
    }
}
