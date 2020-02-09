package homework_17_18_19_optional_date_dbrepo.database;

public interface JdbcConsumer<T> {
    void accept(T t) throws Exception;
}
