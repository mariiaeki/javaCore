package homework_16_2401_java8.cargo.domain;

import java.util.Date;

public class PerishableCargo extends BasicCargo {
    private Date dateOfExpire;
    private int storeTemperature;

    public PerishableCargo() {
    }

    public PerishableCargo(String name, int weight, CargoType cargoType, int storeTemperature) {
        this.name = name;
        this.weight = weight;
        this.cargoType = cargoType;
        this.dateOfExpire = new Date();
        this.storeTemperature = storeTemperature;
    }

    public Date getDateOfExpire() {
        return dateOfExpire;
    }

    public void setDateOfExpire(Date dateOfExpire) {
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
