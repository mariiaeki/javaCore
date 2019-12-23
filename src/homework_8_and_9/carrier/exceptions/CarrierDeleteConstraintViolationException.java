package homework_8_and_9.carrier.exceptions;

import homework_8_and_9.common.company_exceptions.OurCompanyException;

public class CarrierDeleteConstraintViolationException extends OurCompanyException {

    private static final String MESSAGE = "Cant delete carrier with id '%s'. There are transportations which relates to it!";

    public CarrierDeleteConstraintViolationException(String message) {
        super(message);
    }

    public CarrierDeleteConstraintViolationException(long carrierId) {
        this(String.format(MESSAGE, carrierId));
    }
}
