package homework_7_1612.cargo.domain;

import homework_7_1612.common.domain.BaseEntity;
import homework_7_1612.transportation.domain.Transportation;

public abstract class BasicCargo extends BaseEntity {

    protected String name;
    protected int weight;
    protected CargoType cargoType;
    protected Transportation[] transportations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public Transportation[] getTransportations() {
        return transportations;
    }

    public void setTransportations(Transportation[] transportations) {
        this.transportations = transportations;
    }

}
