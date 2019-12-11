package homework_4_0912_trucking;

import homework_4_0912_trucking.cargo.Cargo;
import homework_4_0912_trucking.carrier.Carrier;
import homework_4_0912_trucking.transportation.Transportation;

public class Storage {
    private static final int ST = 3;

    private static Cargo[] cargoStorage = new Cargo[ST];
    private static Carrier[] carrierStorage = new Carrier[ST];
    private static Transportation[] transportationsStorage = new Transportation[ST];

    private int kCargo = 0;
    private int kCarrier = 0;
    private int kTrnsp = 0;

    public void addCargo(Cargo cargo){
        cargoStorage[kCargo] = cargo;
        kCargo++;
    }

    public void printAllCargo(){
        for (int i = 0; i < kCargo; i++){
            System.out.println(cargoStorage[i]);
        }
    }

    public void addCarrier(Carrier carrier){
        carrierStorage[kCarrier] = carrier;
        kCarrier++;
    }

    public void printAllCarrier(){
        for (int i = 0; i < kCarrier; i++){
            System.out.println(carrierStorage[i]);
        }
    }
    public void addTransportation(Transportation trs){
        transportationsStorage[kTrnsp] = trs;
        kTrnsp++;
    }

    public void printAllTransportation(){
        for (int i = 0; i < kTrnsp; i++){
            System.out.println(transportationsStorage[i]);
        }
    }

}
