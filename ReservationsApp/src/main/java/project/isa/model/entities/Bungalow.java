package project.isa.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Bungalow")
public class Bungalow extends Attraction {

    @Column(name = "TV")
    public boolean tv;

    @Column(name = "AirConditioner")
    public boolean airConditioner;

    @Column(name = "WiFi")
    public boolean wifi;

    @Column(name = "Fridge")
    public boolean fridge;

    @Column(name = "KitchenAppliances")
    public boolean kitchenAppliances;


    public Bungalow(Long id, String address, String country, String description, Double rates, Double price, boolean reserved, LocalDate startDate,
                    LocalDate endDate, boolean tv, boolean airConditioner, boolean wifi, boolean fridge, boolean kitchenAppliances, String image) {
        super(id, address, country, description, rates, price, reserved, startDate, endDate, image);
        this.tv = tv;
        this.airConditioner = airConditioner;
        this.wifi = wifi;
        this.fridge = fridge;
        this.kitchenAppliances = kitchenAppliances;
    }

    public Bungalow(boolean tv, boolean airConditioner, boolean wifi, boolean fridge, boolean kitchenAppliances) {
        this.tv = tv;
        this.airConditioner = airConditioner;
        this.wifi = wifi;
        this.fridge = fridge;
        this.kitchenAppliances = kitchenAppliances;
    }

    public Bungalow() {
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isFridge() {
        return fridge;
    }

    public void setFridge(boolean fridge) {
        this.fridge = fridge;
    }

    public boolean isKitchenAppliances() {
        return kitchenAppliances;
    }

    public void setKitchenAppliances(boolean kitchenAppliances) {
        this.kitchenAppliances = kitchenAppliances;
    }
}
