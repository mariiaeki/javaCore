package homework_16_2401_java8.cargo.domain;

public class Cargo extends BasicCargo {
    public Cargo(String name, int weight, CargoType cargoType) {
        this.name = name;
        this.weight = weight;
        this.cargoType = cargoType;
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
