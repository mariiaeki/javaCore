package homework_4_0912_trucking.cargo;

import homework_4_0912_trucking.transportation.Transportation;

import java.util.Arrays;

public class Cargo {
    private Long id;
    private String name;
    private int weight;
    private CargoType cargoType;
    private Transportation[] transportations;

    public Cargo() {
    }

    public Cargo(Long id, String name, int weight, CargoType cargoType) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.cargoType = cargoType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                '}';
    }
}
