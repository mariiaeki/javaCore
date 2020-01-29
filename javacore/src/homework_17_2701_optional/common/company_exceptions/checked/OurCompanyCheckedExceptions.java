package homework_17_2701_optional.common.company_exceptions.checked;

public class OurCompanyCheckedExceptions extends Exception{
    public OurCompanyCheckedExceptions(String message) {
        super(message);
    }

    public OurCompanyCheckedExceptions(String message, Exception cause) {
        super(message);
        this.initCause(cause);
    }
}
