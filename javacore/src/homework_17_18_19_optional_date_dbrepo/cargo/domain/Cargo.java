package homework_17_18_19_optional_date_dbrepo.cargo.domain;

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
