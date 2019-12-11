package homework_4_0912_trucking;


import homework_4_0912_trucking.cargo.Cargo;
import homework_4_0912_trucking.cargo.CargoType;
import homework_4_0912_trucking.carrier.Carrier;
import homework_4_0912_trucking.carrier.CarrierType;
import homework_4_0912_trucking.transportation.Transportation;


public class DemoTransportCompany {
    public static void main(String[] args) {

        Storage storage = new Storage();

        Cargo apple = new Cargo(1L, "Apple", 10, CargoType.FOOD);
        storage.addCargo(apple);
        Cargo tshirt = new Cargo(2L, "T-shirt", 1, CargoType.CLOTHER);
        storage.addCargo(tshirt);
        Cargo mac = new Cargo(3L, "Macintosh", 2, CargoType.COMPUTERS);
        storage.addCargo(mac);


        Carrier company_1 = new Carrier(1L, "FlyWings", "SPB, Pulkovskaya 10", CarrierType.PLANE);
        storage.addCarrier(company_1);
        Carrier company_2 = new Carrier(2L, "RedLabel", "SPB, Lenina 8", CarrierType.CAR);
        storage.addCarrier(company_2);
        Carrier company_3 = new Carrier(3L, "BlackShip", "SPB, Morskoy vokzal 1", CarrierType.SHIP);
        storage.addCarrier(company_3);

        Transportation transportation_1 = new Transportation(1L, apple, company_3, "Tom Keek");
        storage.addTransportation(transportation_1);
        Transportation transportation_2 = new Transportation(2L, tshirt, company_2, "Mark Korsin");
        storage.addTransportation(transportation_2);
        Transportation transportation_3 = new Transportation(3L, mac, company_1, "Gregory Cops");
        storage.addTransportation(transportation_3);

        apple.setTransportations(
                new Transportation[]{transportation_1}
        );
        company_3.setTransportations(
                new Transportation[]{transportation_1});

        tshirt.setTransportations(
                new Transportation[]{transportation_2}
        );
        company_2.setTransportations(
                new Transportation[]{transportation_2});

        mac.setTransportations(
                new Transportation[]{transportation_3}
        );
        company_1.setTransportations(
                new Transportation[]{transportation_3});


        storage.printAllCargo();
        storage.printAllCarrier();
        storage.printAllTransportation();

        //v2

        StorageStr strStorage = new StorageStr();

        strStorage.addCargo(apple.toString());
        strStorage.addCargo(tshirt.toString());
        strStorage.addCargo(mac.toString());

        strStorage.printAllCargo();

        strStorage.addCarrier(company_1.toString());
        strStorage.addCarrier(company_2.toString());
        strStorage.addCarrier(company_3.toString());

        strStorage.printAllCarrier();

        strStorage.addTrs(transportation_1.toString());
        strStorage.addTrs(transportation_2.toString());
        strStorage.addTrs(transportation_3.toString());

        strStorage.printAllTrs();

    }
}
