package homework_17_18_optional_and_date.cargo.exceptions;

import homework_17_18_optional_and_date.common.company_exceptions.uncheked.OurCompanyException;

public class CargoDeleteConstraintViolationException extends OurCompanyException {
    private static final String MESSAGE = "Cant delete cargo with id '%s'. There are transportations which relates to it!";

    public CargoDeleteConstraintViolationException(String message) {
        super(message);
    }

    public CargoDeleteConstraintViolationException(long cargoId) {
        this(String.format(MESSAGE, cargoId));
    }
}
