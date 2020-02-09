package homework_17_18_19_optional_date_dbrepo.cargo.repo;

import homework_17_18_19_optional_date_dbrepo.cargo.domain.BasicCargo;
import homework_17_18_19_optional_date_dbrepo.common.comparator.EntitySortConditions;
import homework_17_18_19_optional_date_dbrepo.common.repository.MapperMethods;
import homework_17_18_19_optional_date_dbrepo.database.DBConnectionPool;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static homework_17_18_19_optional_date_dbrepo.database.DatabaseUtils.*;

public class CargoDatabaseRepoImpl implements CargoRepo, Serializable {

    public List<BasicCargo> getByName(String name) {
        if (isPresentName(name)) {

            return executeQueryList("SELECT * FROM cargos WHERE cargo_name =?",
                    ps -> {
                        ps.setString(1, name);
                    },
                    MapperMethods::mapBasicCargo

            );
        } else
            return Collections.EMPTY_LIST;
    }


    public List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions) {
        List<BasicCargo> cargos = new ArrayList<>();
        if (searchConditions.needSorting()) {
            switch (searchConditions.getOrderingConditionsAsString()) {
                case "NAME":
                    if (searchConditions.isAscOrdering() == true) {
                        cargos = executeQueryList("SELECT * FROM cargos ORDER BY ? ASC",
                                ps -> ps.setString(1, "cargo_name")
                                ,
                                MapperMethods::mapBasicCargo);
                    } else {
                        cargos = executeQueryList("SELECT * FROM cargos ORDER BY ? DESC",
                                ps -> {
                                    ps.setString(1, "cargo_name");
                                },
                                MapperMethods::mapBasicCargo);
                    }
                    break;
                case "WEIGHT":
                    if (searchConditions.isAscOrdering() == true) {
                        cargos = executeQueryList("SELECT * FROM cargos ORDER BY ? ASC",
                                ps -> ps.setString(1, "weight")
                                ,
                                MapperMethods::mapBasicCargo);
                    } else {
                        cargos = executeQueryList("SELECT * FROM cargos ORDER BY ? DESC",
                                ps -> {
                                    ps.setString(1, "weight");
                                },
                                MapperMethods::mapBasicCargo);
                    }
                    break;

                default:
                    if (searchConditions.isAscOrdering() == true) {
                        cargos = executeQueryList("SELECT * FROM cargos ORDER BY ?, ? ASC",
                                ps -> {
                                    ps.setString(1, "cargo_name");
                                    ps.setString(2, "weight");
                                }

                                ,
                                MapperMethods::mapBasicCargo);
                    } else {
                        cargos = executeQueryList("SELECT * FROM cargos ORDER BY ?, ? DESC",
                                ps -> {
                                    ps.setString(1, "cargo_name");
                                    ps.setString(2, "weight");
                                },
                                MapperMethods::mapBasicCargo);
                    }
                    break;
            }

        }
        return cargos;

    }


    public Optional<BasicCargo> getByIdFetchingTransportations(long id) {
        return getById(id);
    }


    public boolean deleteById(long id) {
        if (isPresentID(id)) {
            executeUpdate("DELETE FROM cargos WHERE cargo_id = ?",
                    ps -> ps.setLong(1, id));
            return true;
        } else return false;
    }

    public void addAll(List<BasicCargo> cargos){
        try (Connection connection = DBConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement
                     ("insert cargos(cargo_id, cargo_name, weight, cargo_type) values(?, ?, ?, ?)");
        ) {
            connection.setAutoCommit(false);

            for (BasicCargo cargo : cargos){
                ps.setLong(1, cargo.getId());
                ps.setString(2, cargo.getName());
                ps.setInt(3, cargo.getWeight());
                ps.setString(4, String.valueOf(cargo.getCargoType()));

                ps.addBatch();
            }

            ps.executeBatch();

            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void add(BasicCargo cargo) {
        executeUpdate(
                "insert cargos(cargo_id, cargo_name, weight, cargo_type) values(?, ?, ?, ?)",
                ps -> {
                    ps.setLong(1, cargo.getId());
                    ps.setString(2, cargo.getName());
                    ps.setInt(3, cargo.getWeight());
                    ps.setString(4, String.valueOf(cargo.getCargoType()));
                }
        );

//        if (cargo.getCargoType().equals(CargoType.PERISHABLE)) {
//
//            executeUpdate(
//                    "insert cargos(cargo_id, cargo_name, weight, cargo_type) values(?, ?, ?, ?)",
//                    ps -> {
//                        ps.setLong(1, cargo.getId());
//                        ps.setString(2, cargo.getName());
//                        ps.setInt(3, cargo.getWeight());
//                        ps.setString(4, String.valueOf(cargo.getCargoType()));
//                    }
//            );
//        } else {
//            executeUpdate("insert cargos(cargo_id, cargo_name, weight, cargo_type) values(?, ?, ?, ?)",
//                    ps -> {
//                        ps.setLong(1, cargo.getId());
//                        ps.setString(2, cargo.getName());
//                        ps.setInt(3, cargo.getWeight());
//                        ps.setString(4, String.valueOf(cargo.getCargoType()));
//                        ps.setInt(5, cargo.getSize());
//                        ps.setString(6, String.valueOf(cargo.getGender()));
//                    }
//            );
//        }


//        Connection connection = null;
//
//        PreparedStatement ps = null;
//        try {
//            connection = DBConnectionPool.getConnection();
//            String sql = "insert cargos(cargo_id, cargo_name, weight, cargo_type) \n" +
//                    "values(?, ?, ?, ?)";
//            ps = connection.prepareStatement(sql);
//            ps.setLong(1, cargo.getId());
//            ps.setString(2, cargo.getName());
//            ps.setInt(3, cargo.getWeight());
//            ps.setString(4, String.valueOf(cargo.getCargoType()));
//            //ps.setInt(5, cargo.getSize());
//            //ps.setString(6, String.valueOf(cargo.getGender()));
//
//            ps.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }


    public Optional<BasicCargo> getById(long id) {
        if (isPresentID(id)) {
            List<BasicCargo> cargos = executeQueryList("SELECT * FROM cargos WHERE cargo_id =?",
                    ps -> {
                        ps.setLong(1, id);
                    },
                    MapperMethods::mapBasicCargo

            );
            return Optional.of(cargos.get(0));
        } else
            return Optional.empty();
    }


    public List<BasicCargo> getAll() {
        return selectAll("SELECT * FROM cargos",
                MapperMethods::mapBasicCargo
        );
    }


    public void update(BasicCargo cargo) {
        executeUpdate(
                "UPDATE cargos SET cargo_name=? WHERE cargo_id =?",
                ps -> {
                    ps.setString(1, cargo.getName());
                    ps.setLong(2, cargo.getId());
                }
        );
    }

    public boolean isPresentID(long id) {
        return isPresentQuery("SELECT COUNT(cargo_id) FROM cargos WHERE cargo_id = ?",
                ps -> {
                    ps.setLong(1, id);
                });
    }

    public boolean isPresentName(String name) {
        return isPresentQuery("SELECT COUNT(cargo_name) FROM cargos WHERE cargo_name = ?",
                ps -> {
                    ps.setString(1, name);
                });
    }
}
