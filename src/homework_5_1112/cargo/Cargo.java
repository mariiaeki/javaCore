package homework_5_1112.cargo;

public class Cargo extends BasicCargo{
    public Cargo(Long id, String name, int weight, CargoType cargoType) {
        this.id = id;
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
