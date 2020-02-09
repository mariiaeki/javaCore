package homework_17_18_19_optional_date_dbrepo.common.repository;

import homework_17_18_19_optional_date_dbrepo.cargo.domain.BasicCargo;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.CargoType;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.OutfitCargo;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.PerishableCargo;
import homework_17_18_19_optional_date_dbrepo.carrier.domain.Carrier;
import homework_17_18_19_optional_date_dbrepo.carrier.domain.CarrierType;
import homework_17_18_19_optional_date_dbrepo.transportation.domain.Transportation;

import javax.sql.rowset.CachedRowSet;
import javax.swing.text.DateFormatter;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static homework_17_18_19_optional_date_dbrepo.database.DatabaseUtils.executeQueryList;

public class MapperMethods {
    public static BasicCargo mapBasicCargo(ResultSet rs) throws Exception {
        BasicCargo cargo = new OutfitCargo();
        cargo.setId(rs.getLong("cargo_id"));
        cargo.setName(rs.getString("cargo_name"));
        cargo.setWeight(rs.getInt("weight"));
        cargo.setCargoType(CargoType.valueOf(rs.getString("cargo_type")));

        return cargo;
    }

    public static OutfitCargo mapOutfitCargo(ResultSet rs) throws Exception {
        OutfitCargo cargo = new OutfitCargo();
        cargo.setId(rs.getLong("cargo_id"));
        cargo.setName(rs.getString("cargo_name"));
        cargo.setWeight(rs.getInt("weight"));
        cargo.setCargoType(CargoType.valueOf(rs.getString("cargo_type")));
        cargo.setSize(rs.getInt("size"));
        cargo.setGender(OutfitCargo.Gender.valueOf(rs.getString("gender")));

        return cargo;
    }

    public static PerishableCargo mapPerishableCargo(ResultSet rs) throws Exception {
        PerishableCargo cargo = new PerishableCargo();
        cargo.setId(rs.getLong("cargo_id"));
        cargo.setName(rs.getString("cargo_name"));
        cargo.setWeight(rs.getInt("weight"));
        cargo.setCargoType(CargoType.valueOf(rs.getString("cargo_type")));
        cargo.setStoreTemperature(rs.getInt("store_temperature"));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        cargo.setDateOfExpire(LocalDate.parse(rs.getString("date_of_expire"), dateFormatter));

        return cargo;
    }

    public static Carrier mapCarrier(ResultSet rs) throws Exception {
        Carrier carrier = new Carrier();

        carrier.setId(rs.getLong("carrier_id"));
        carrier.setName(rs.getString("carrier_name"));
        carrier.setAddress(rs.getString("addres"));
        carrier.setCarrierType(CarrierType.valueOf(rs.getString("carrier_type")));

        return carrier;
    }

    public static Transportation mapTrs(ResultSet rs) throws Exception {
        Transportation trs = new Transportation();

        trs.setId(rs.getLong("transportation_id"));
        if (rs.getString("cargo_type").equals(CargoType.OUTFIT)) {
            trs.setCargo(mapOutfitCargo(rs));
        } else {
            trs.setCargo(mapBasicCargo(rs));
        }
        trs.setCarrier(mapCarrier(rs));
        trs.setDescription(rs.getString("description"));
        trs.setBillTo(rs.getString("billTo"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        trs.setDate(LocalDateTime.parse(rs.getString("date"), formatter));

        return trs;
    }
}
