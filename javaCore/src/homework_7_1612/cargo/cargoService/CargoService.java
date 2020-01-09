package homework_7_1612.cargo.cargoService;

import homework_7_1612.cargo.domain.BasicCargo;
import homework_7_1612.common.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService {
    void add(BasicCargo cargo);

    BasicCargo getById(long id);

    BasicCargo[] getByName(String name);
}
