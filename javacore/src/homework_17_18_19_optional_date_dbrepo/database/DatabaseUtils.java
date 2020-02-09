package homework_17_18_19_optional_date_dbrepo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseUtils {
    public DatabaseUtils() {
    }

    public static void executeUpdate(String sql,
                                     JdbcConsumer<PreparedStatement> psConsumer) {
        try (Connection connection = DBConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            connection.setAutoCommit(false);

            psConsumer.accept(ps);
            ps.executeUpdate();

           connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static boolean isPresentQuery(String sql,
                                   JdbcConsumer<PreparedStatement> psConsumer) {
        try (Connection connection = DBConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            psConsumer.accept(ps);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()){
                return true;
            } else
                return false;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> executeQueryList(String sql,
                                               JdbcConsumer<PreparedStatement> psConsumer,
                                               JdbcFunction<ResultSet, T> rsConverter) {
        try (Connection connection = DBConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            psConsumer.accept(ps);
            ResultSet resultSet = ps.executeQuery();

            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                T t = rsConverter.apply(resultSet);
                result.add(t);
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> selectAll(String sql,
                                        JdbcFunction<ResultSet, T> rsConverter) {
        try (Connection connection = DBConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            connection.setAutoCommit(false);

            ResultSet resultSet = ps.executeQuery();

            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                T t = rsConverter.apply(resultSet);
                result.add(t);
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
