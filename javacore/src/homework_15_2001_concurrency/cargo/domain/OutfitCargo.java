package homework_15_2001_concurrency.cargo.domain;

public class OutfitCargo extends BasicCargo {
    private int size;
    private Gender gender;

    public OutfitCargo() {
    }

    public OutfitCargo(String name, int weight, CargoType cargoType, int size, Gender gender) {
        this.name = name;
        this.weight = weight;
        this.cargoType = cargoType;
        this.size = size;
        this.gender = gender;
    }

    public enum Gender{
        MALE,
        FEMALE,
        UNISEX
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "OutfitCargo{" +
                " id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                ", size=" + size +
                ", gender=" + gender +
                '}';
    }
}
