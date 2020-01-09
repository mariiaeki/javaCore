package homework_5_1112.transportation;

import homework_5_1112.cargo.BasicCargo;
import homework_5_1112.cargo.Cargo;
import homework_5_1112.carrier.Carrier;

import java.util.Date;

public class Transportation {

    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
