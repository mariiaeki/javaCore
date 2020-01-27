package homework_16_2401_java8.cargo.exceptions;

import homework_16_2401_java8.common.company_exceptions.uncheked.OurCompanyException;

public class CargoDeleteConstraintViolationException extends OurCompanyException {
    private static final String MESSAGE = "Cant delete cargo with id '%s'. There are transportations which relates to it!";

    public CargoDeleteConstraintViolationException(String message) {
        super(message);
    }

    public CargoDeleteConstraintViolationException(long cargoId) {
        this(String.format(MESSAGE, cargoId));
    }
}
