package homework_4_0912_trucking;

public class StorageStr {
    private static StringBuilder strCargoStorage = new StringBuilder();
    private static StringBuilder strCarrierStorage = new StringBuilder();
    private static StringBuilder strTrsStorage = new StringBuilder();


    public void addCargo(String cargo){
        strCargoStorage.append(cargo).append("; \n");
    }

    public void printAllCargo(){
        System.out.println(strCargoStorage);
    }

    public void addCarrier(String carrier){
        strCarrierStorage.append(carrier).append("; \n");
    }

    public void printAllCarrier(){
        System.out.println(strCarrierStorage);
    }

    public void addTrs(String trs){
        strTrsStorage.append(trs).append("; \n");
    }

    public void printAllTrs(){
        System.out.println(strTrsStorage);
    }
}
