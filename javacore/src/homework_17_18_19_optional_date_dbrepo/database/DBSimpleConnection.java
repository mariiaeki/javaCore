package homework_17_18_19_optional_date_dbrepo.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBSimpleConnection implements  DBConnection{

    private static final DBSimpleConnection INSTANCE = new DBSimpleConnection();

    private DBSimpleConnection() {

    }

    public static DBSimpleConnection getInstance() {
        return INSTANCE;
    }

    @Override
    public Connection getConnection() throws Exception {
        String driverClass = "com.mysql.cj.jdbc.Driver";
        Class.forName(driverClass);

        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/javaepam?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow",
                        "root",
                        "masha2505");
    }
}
