package homework_17_18_19_optional_date_dbrepo.database;

public interface JdbcFunction<FROM, TO> {
    TO apply(FROM from) throws Exception;
}
