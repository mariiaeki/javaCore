package homework_16_2401_java8.transportation.domain;

import homework_16_2401_java8.cargo.domain.BasicCargo;
import homework_16_2401_java8.carrier.domain.Carrier;
import homework_16_2401_java8.common.domain.BaseEntity;

import java.util.Date;

public class Transportation extends BaseEntity {

    private BasicCargo cargo;
    private Carrier carrier;
    private String description;
    private String billTo;
    private Date date;

    public Transportation() {
    }

    public Transportation(Long id, BasicCargo cargo, Carrier carrier, String billTo) {
        this.id = id;
        this.cargo = cargo;
        this.carrier = carrier;
        this.billTo = billTo;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBillTo() {
        return billTo;
    }

    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }

    public BasicCargo getCargo() {
        return cargo;
    }

    public void setCargo(BasicCargo cargo) {
        this.cargo = cargo;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    @Override
    public String toString() {
        return "Transportation{" +
                "id=" + id +
                ", cargo=" + cargo +
                ", carrier=" + carrier +
                ", description='" + description + '\'' +
                ", billTo='" + billTo + '\'' +
                ", date=" + date +
                '}';
    }
}
