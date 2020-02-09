package homework_17_18_19_optional_date_dbrepo.common.company_exceptions.checked;

public class OurCompanyCheckedExceptions extends Exception{
    public OurCompanyCheckedExceptions(String message) {
        super(message);
    }

    public OurCompanyCheckedExceptions(String message, Exception cause) {
        super(message);
        this.initCause(cause);
    }
}
