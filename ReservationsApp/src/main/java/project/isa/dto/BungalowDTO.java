package project.isa.dto;

import javax.persistence.Column;
import java.time.LocalDate;

public class BungalowDTO {

    private String name;

    private String address;

    private String country;


    private String city;

    private String description;

    private Double rates;

    private Double price;


    private boolean reserved;


    private LocalDate startDate;

    private LocalDate endDate;

    private String image;

    private String ownerUsername;

    private int maxGuests;

    public boolean tv;

    public boolean airConditioner;

    public boolean wifi;

    public boolean fridge;


    public BungalowDTO(String name, String address, String country, String city, String description, Double rates, Double price, boolean reserved, LocalDate startDate, LocalDate endDate, String image, String ownerUsername, int maxGuests, boolean tv, boolean airConditioner, boolean wifi, boolean fridge) {
        this.name = name;
        this.address = address;
        this.country = country;
        this.description = description;
        this.rates = rates;
        this.price = price;
        this.reserved = reserved;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.ownerUsername = ownerUsername;
        this.maxGuests = maxGuests;
        this.tv = tv;
        this.airConditioner = airConditioner;
        this.wifi = wifi;
        this.fridge = fridge;
        this.city = city;
    }

    public BungalowDTO() {}

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRates() {
        return rates;
    }

    public void setRates(Double rates) {
        this.rates = rates;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
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
}
