package homework_17_18_19_optional_date_dbrepo.carrier.repo;

import homework_17_18_19_optional_date_dbrepo.carrier.domain.Carrier;
import homework_17_18_19_optional_date_dbrepo.common.repository.MapperMethods;
import homework_17_18_19_optional_date_dbrepo.database.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static homework_17_18_19_optional_date_dbrepo.database.DatabaseUtils.*;


public class CarrierDatabaseRepoImpl implements CarrierRepo {
    @Override
    public List<Carrier> getByName(String name) {
        if (isPresentName(name)) {

            return executeQueryList("SELECT * FROM carriers WHERE carrier_name =?",
                    ps -> {
                        ps.setString(1, name);
                    },
                    MapperMethods::mapCarrier

            );
        } else
            return Collections.EMPTY_LIST;
    }

    @Override
    public Optional<Carrier> getByIdFetchingTransportations(long id) {
        return getById(id);
    }

    @Override
    public boolean deleteById(long id) {
        if (isPresentID(id)) {
            executeUpdate("DELETE FROM carriers WHERE carrier_id = ?",
                    ps -> ps.setLong(1, id));
            return true;
        } else return false;
    }

    @Override
    public void add(Carrier carrier) {
        executeUpdate(
                "insert carriers(carrier_id, carrier_name, addres, carrier_type) values(?, ?, ?, ?)",
                ps -> {
                    ps.setLong(1, carrier.getId());
                    ps.setString(2, carrier.getName());
                    ps.setString(3, carrier.getAddress());
                    ps.setString(4, String.valueOf(carrier.getCarrierType()));
                }
        );

    }

    @Override
    public Optional<Carrier> getById(long id) {
        if (isPresentID(id)) {
            List<Carrier> carriers = executeQueryList("SELECT * FROM carriers WHERE carrier_id =?",
                    ps -> {
                        ps.setLong(1, id);
                    },
                    MapperMethods::mapCarrier

            );
            return Optional.of(carriers.get(0));
        } else
            return Optional.empty();
    }

    @Override
    public List<Carrier> getAll() {
        return selectAll("SELECT * FROM carriers",
                MapperMethods::mapCarrier
        );
    }

    public void addAll(List<Carrier> carriers) {
        try (Connection connection = DBConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement
                     ("insert carriers(carrier_id, carrier_name, addres, carrier_type) " +
                             "values(?, ?, ?, ?)");
        ) {
            for (Carrier carrier : carriers) {
                ps.setLong(1, carrier.getId());
                ps.setString(2, carrier.getName());
                ps.setString(3, carrier.getAddress());
                ps.setString(4, String.valueOf(carrier.getCarrierType()));
                ps.addBatch();
            }

            ps.executeBatch();

        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Carrier carrier) {
        executeUpdate(
                "UPDATE carriers SET carrier_name=? WHERE carrier_id =?",
                ps -> {
                    ps.setString(1, carrier.getName());
                    ps.setLong(2, carrier.getId());
                }
        );

    }

    public boolean isPresentID(long id) {
        return isPresentQuery("SELECT COUNT(carrier_id) FROM carriers WHERE carrier_id = ?",
                ps -> {
                    ps.setLong(1, id);
                });
    }

    public boolean isPresentName(String name) {
        return isPresentQuery("SELECT COUNT(carrier_id) FROM carriers WHERE carrier_id = ?",
                ps -> {
                    ps.setString(1, name);
                });
    }
}
