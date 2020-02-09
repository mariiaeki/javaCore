package homework_17_18_19_optional_date_dbrepo.cargo.domain;

import java.time.LocalDate;

public class PerishableCargo extends BasicCargo {
    private LocalDate dateOfExpire;
    private int storeTemperature;

    public PerishableCargo() {
    }

    public PerishableCargo(String name, int weight, CargoType cargoType, LocalDate date, int storeTemperature) {
        this.name = name;
        this.weight = weight;
        this.cargoType = cargoType;
        this.dateOfExpire = date;
        this.storeTemperature = storeTemperature;
    }

    public PerishableCargo( Long id, String name, int weight, CargoType cargoType, LocalDate date, int storeTemperature) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.cargoType = cargoType;
        this.dateOfExpire = date;
        this.storeTemperature = storeTemperature;    }

    public LocalDate getDateOfExpire() {
        return dateOfExpire;
    }

    public void setDateOfExpire(LocalDate dateOfExpire) {
        this.dateOfExpire = dateOfExpire;
    }

    public int getStoreTemperature() {
        return storeTemperature;
    }

    public void setStoreTemperature(int storeTemperature) {
        this.storeTemperature = storeTemperature;
    }

    @Override
    public String toString() {
        return "PerishableCargo{" +
                "id=" + id +
                ", dateOfExpire='" + dateOfExpire + '\'' +
                ", storeTemperature=" + storeTemperature +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                '}';
    }
}
