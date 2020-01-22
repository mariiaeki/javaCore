package homework_15_2001_concurrency.common.company_exceptions.checked;

public class OurCompanyCheckedExceptions extends Exception{
    public OurCompanyCheckedExceptions(String message) {
        super(message);
    }

    public OurCompanyCheckedExceptions(String message, Exception cause) {
        super(message);
        this.initCause(cause);
    }
}
