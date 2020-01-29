package homework_17_2701_optional.common.company_exceptions.uncheked;

import homework_17_2701_optional.common.company_exceptions.checked.OurCompanyCheckedExceptions;

public class NoEntityException extends OurCompanyException {
    public NoEntityException(String message) {
        super(message);
    }
}
