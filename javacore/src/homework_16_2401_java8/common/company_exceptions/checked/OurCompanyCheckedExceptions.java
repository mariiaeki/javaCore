package homework_16_2401_java8.common.company_exceptions.checked;

public class OurCompanyCheckedExceptions extends Exception{
    public OurCompanyCheckedExceptions(String message) {
        super(message);
    }

    public OurCompanyCheckedExceptions(String message, Exception cause) {
        super(message);
        this.initCause(cause);
    }
}
