package homework_5_1112.cargo;

public class PerishableCargo extends BasicCargo {
    private String dateOfExpire;

    public PerishableCargo(String name, int weight, CargoType cargoType, String dateOfExpire) {
        this.name = name;
        this.weight = weight;
        this.cargoType = cargoType;
        this.dateOfExpire = dateOfExpire;
    }

    public void makeUnsuitable(){
        this.dateOfExpire = "Expired";
    }

    @Override
    public String toString() {
        return "PerishableCargo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                ", dateOfExpire='" + dateOfExpire + '\'' +
                '}';
    }
}
