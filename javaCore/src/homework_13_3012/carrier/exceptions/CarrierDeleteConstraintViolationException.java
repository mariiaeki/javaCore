package homework_13_3012.carrier.exceptions;

import homework_13_3012.common.company_exceptions.uncheked.OurCompanyException;

public class CarrierDeleteConstraintViolationException extends OurCompanyException {

    private static final String MESSAGE = "Cant delete carrier with id '%s'. There are transportations which relates to it!";

    public CarrierDeleteConstraintViolationException(String message) {
        super(message);
    }

    public CarrierDeleteConstraintViolationException(long carrierId) {
        this(String.format(MESSAGE, carrierId));
    }
}
