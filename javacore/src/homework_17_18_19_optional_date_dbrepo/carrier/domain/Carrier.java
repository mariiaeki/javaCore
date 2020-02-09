package homework_17_18_19_optional_date_dbrepo.carrier.domain;

import homework_17_18_19_optional_date_dbrepo.common.domain.BaseEntity;
import homework_17_18_19_optional_date_dbrepo.transportation.domain.Transportation;

import java.util.List;

public class Carrier extends BaseEntity {

    private String name;
    private String address;
    private CarrierType carrierType;
    private List<Transportation> transportations;

    public Carrier() {
    }

    public Carrier(Long id, String name, String address, CarrierType carrierType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.carrierType = carrierType;
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

    public List<Transportation> getTransportations() {
        return transportations;
    }

    public void setTransportations(List<Transportation> transportations) {
        this.transportations = transportations;
    }

    @Override
    public String toString() {
        return "Carrier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", carrierType=" + carrierType +
                '}';
    }
}
