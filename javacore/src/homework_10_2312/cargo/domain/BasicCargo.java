package homework_10_2312.cargo.domain;

import homework_10_2312.common.domain.BaseEntity;
import homework_10_2312.transportation.domain.Transportation;

import java.util.List;

public abstract class BasicCargo extends BaseEntity {

    protected String name;
    protected int weight;
    protected CargoType cargoType;
    protected List<Transportation> transportations;

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

    public List<Transportation> getTransportations() {
        return transportations;
    }

    public void setTransportations(List<Transportation> transportations) {
        this.transportations = transportations;
    }
}
