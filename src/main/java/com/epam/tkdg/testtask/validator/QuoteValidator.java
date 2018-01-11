package com.epam.tkdg.testtask.validator;

import com.epam.tkdg.testtask.model.Quote;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class QuoteValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Quote.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Quote quote = (Quote) target;

        if (quote.getIsin() == null || quote.getIsin().length() != 12) {
            errors.rejectValue("isin", "ISIN must be 12 characters.");
        }
        if (quote.getBid() != null && quote.getAsk() != null
                && quote.getBid().compareTo(quote.getAsk()) >= 0) {
            errors.rejectValue("bid", "Bid must be less than Ask.");
        }
    }
}
