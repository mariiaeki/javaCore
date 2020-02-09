package homework_17_18_19_optional_date_dbrepo.database;

import java.sql.Connection;

public interface DBConnection  {
    Connection getConnection() throws Exception;
}
