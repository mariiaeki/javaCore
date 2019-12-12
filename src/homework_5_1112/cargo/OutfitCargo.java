package homework_5_1112.cargo;

public class OutfitCargo extends BasicCargo{
    private int size;
    private Gender gender;

    public OutfitCargo(Long id, String name, int weight, CargoType cargoType, int size, Gender gender) {
        this.id = id;
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
