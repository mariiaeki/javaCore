package homework_5_1112;

import homework_5_1112.cargo.BasicCargo;
import homework_5_1112.cargo.Cargo;
import homework_5_1112.carrier.Carrier;
import homework_5_1112.transportation.Transportation;

public class Storage {
    private static final int ST = 10;

    private static BasicCargo[] cargoStorage = new BasicCargo[ST];
    private static Carrier[] carrierStorage = new Carrier[ST];
    private static Transportation[] transportationsStorage = new Transportation[ST];

    private int kCargo = 0;
    private int kCarrier = 0;
    private int kTrnsp = 0;

    public void addCargo(BasicCargo cargo){
        cargo.setId(IdGenerator.generateId());
        cargoStorage[kCargo] = cargo;
        kCargo++;

        if (kCargo % (ST - 1) == 0) {
            BasicCargo[] newCargoStorage = new BasicCargo[kCargo + ST];
            copyCargoArray(cargoStorage, newCargoStorage);
            cargoStorage = newCargoStorage;
        }
    }

    private static void copyCargoArray(BasicCargo[] src, BasicCargo[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }

    public void addCarrier(Carrier carrier){
        carrier.setId(IdGenerator.generateId());
        carrierStorage[kCarrier] = carrier;
        kCarrier++;

        if (kCarrier % (ST -1) == 0){
            Carrier[] newCarrierStorage = new Carrier[kCarrier + ST];
            copyCarrierArray(carrierStorage, newCarrierStorage);
            carrierStorage = newCarrierStorage;
        }
    }

    private static void copyCarrierArray(Carrier[] src, Carrier[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }

    public void addTransportation(Transportation trs){
        trs.setId(IdGenerator.generateId());
        transportationsStorage[kTrnsp] = trs;
        kTrnsp++;

        if (kTrnsp % (ST - 1) == 0){
            Transportation[] newTrsStorage = new Transportation[kTrnsp + ST];
            copyTrsArray(transportationsStorage, newTrsStorage);
            transportationsStorage = newTrsStorage;
        }
    }

    private static void copyTrsArray(Transportation[] src, Transportation[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }

    public void printAllCargo(){
        for (int i = 0; i < kCargo; i++){
            System.out.println(cargoStorage[i]);
        }
    }

    public void printAllCarrier(){
        for (int i = 0; i < kCarrier; i++){
            System.out.println(carrierStorage[i]);
        }
    }

    public void printAllTransportation(){
        for (int i = 0; i < kTrnsp; i++){
            System.out.println(transportationsStorage[i]);
        }
    }

}
