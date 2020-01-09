package homework_13_3012.common.company_exceptions.checked;

public class OurCompanyCheckedExceptions extends Exception{
    public OurCompanyCheckedExceptions(String message) {
        super(message);
    }

    public OurCompanyCheckedExceptions(String message, Exception cause) {
        super(message);
        this.initCause(cause);
    }
}
