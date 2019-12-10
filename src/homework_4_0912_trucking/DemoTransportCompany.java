package homework_4_0912_trucking;


import homework_4_0912_trucking.cargo.Cargo;
import homework_4_0912_trucking.cargo.CargoType;
import homework_4_0912_trucking.carrier.Carrier;
import homework_4_0912_trucking.carrier.CarrierType;
import homework_4_0912_trucking.transportation.Transportation;

public class DemoTransportCompany {
    public static void main(String[] args) {

        Cargo apple = new Cargo(1L, "Apple", 10, CargoType.FOOD);
        Cargo tshirt = new Cargo(2L, "T-shirt", 1, CargoType.CLOTHER);
        Cargo mac = new Cargo(3L, "Macintosh", 2, CargoType.COMPUTERS);


        Carrier company_1 = new Carrier(1L, "FlyWings", "SPB, Pulkovskaya 10", CarrierType.PLANE);
        Carrier company_2 = new Carrier(2L, "RedLabel", "SPB, Lenina 8", CarrierType.CAR);
        Carrier company_3 = new Carrier(3L, "BlackShip", "SPB, Morskoy vokzal 1", CarrierType.SHIP);

        Transportation transportation_1 = new Transportation(1L, apple, company_3, "Tom Keek");
        Transportation transportation_2 = new Transportation(2L, tshirt, company_2, "Mark Korsin");
        Transportation transportation_3 = new Transportation(3L, mac, company_1, "Gregory Cops");


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
    }
}
