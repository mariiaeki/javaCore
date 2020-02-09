package homework_17_18_19_optional_date_dbrepo.transportation.repo;

import homework_17_18_19_optional_date_dbrepo.common.repository.MapperMethods;
import homework_17_18_19_optional_date_dbrepo.transportation.domain.Transportation;

import java.util.List;
import java.util.Optional;

import static homework_17_18_19_optional_date_dbrepo.database.DatabaseUtils.selectAll;

public class TrsDatabaseRepoImpl implements TrsRepo {
    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public void add(Transportation elem) {

    }

    @Override
    public Optional<Transportation> getById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Transportation> getAll() {
        return selectAll("SELECT *" +
                        "FROM transportations trs " +
                        "INNER JOIN cargos c ON c.cargo_id = trs.cargo_id " +
                        "inner JOIN carriers cr ON cr.carrier_id = trs.carrier_id",
                MapperMethods::mapTrs
        );
    }

    @Override
    public void update(Transportation elem) {

    }
}
