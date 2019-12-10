package homework_4_0912_trucking.carrier;

import homework_4_0912_trucking.transportation.Transportation;

import java.util.Arrays;

public class Carrier {
    private Long id;
    private String name;
    private String address;
    private CarrierType carrierType;
    private Transportation[] transportations;

    public Carrier() {
    }

    public Carrier(Long id, String name, String address, CarrierType carrierType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.carrierType = carrierType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CarrierType getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(CarrierType carrierType) {
        this.carrierType = carrierType;
    }

    public Transportation[] getTransportations() {
        return transportations;
    }

    public void setTransportations(Transportation[] transportations) {
        this.transportations = transportations;
    }

}
