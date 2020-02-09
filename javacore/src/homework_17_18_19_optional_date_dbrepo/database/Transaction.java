package homework_17_18_19_optional_date_dbrepo.database;

import homework_17_18_19_optional_date_dbrepo.cargo.domain.BasicCargo;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.CargoType;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.OutfitCargo;

import java.sql.*;

public class Transaction {
    public static void main(String[] args) throws Exception {
        demoTransaction();

    }

    private static void demoTransaction() throws Exception {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getConnection();

            connection.setAutoCommit(false);

            a_3_saveUser(connection, new OutfitCargo(103L, "FANTASTICDRESS",
                    100, CargoType.OUTFIT, 42, OutfitCargo.Gender.FEMALE ));


            connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                System.out.println("Revert ");
                connection.rollback();
            }
        } finally {
            a_2_getUsers();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    private static void a_2_getUsers() {
        try (Connection connection = DBConnectionPool.getConnection();
             Statement statement = connection.createStatement();) {

            String sql = "SELECT * FROM cargos";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                long id = resultSet.getLong("cargo_id");
                String name = resultSet.getString("cargo_name");
                int weight = resultSet.getInt("weight");
                CargoType type = CargoType.valueOf(resultSet.getString("cargo_type"));
                int size = resultSet.getInt("size");
                OutfitCargo.Gender gender =
                        OutfitCargo.Gender.valueOf("FEMALE");

                BasicCargo cargo = new OutfitCargo(id, name, weight, type, size, gender) {
                };
                System.out.println(cargo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void a_3_saveUser(Connection con, OutfitCargo cargo) {
        PreparedStatement ps = null;
        try {
            String sql = "insert cargos(cargo_id, cargo_name, weight, cargo_type, size, gender) \n" +
                    "values(?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setLong(1, cargo.getId());
            ps.setString(2, cargo.getName());
            ps.setInt(3, cargo.getWeight());
            ps.setString(4, String.valueOf(cargo.getCargoType()));
            ps.setInt(5, cargo.getSize());
            ps.setString(6, String.valueOf(cargo.getGender()));


            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
