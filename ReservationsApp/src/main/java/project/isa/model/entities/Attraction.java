package project.isa.model.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Attraction {
    @Id
    @Column(name = "Id")
    @SequenceGenerator(name="gen12",sequenceName = "gen112",initialValue = 4,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gen12")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Address")
    private String address;

    @Column(name = "Country")
    private String country;

    @Column(name = "City")
    private String city;

    @Column(name = "Description")
    private String description;

    @Column(name = "Rating")
    private Double rates;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Reserved")
    private boolean reserved;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "EndDate")
    private LocalDate endDate;

    @Column(name = "Image")
    private String image;

    @Column(name = "Owner_Username")
    private String ownerUsername;

    @Column(name = "Max_Guests")
    private int maxGuests;

    @Column(name = "Type")
    private String type;


    public Attraction(Long id, String address, String country, String city, String description, Double rates,
                      Double price, boolean reserved, LocalDate startDate, LocalDate endDate,
                      String image, String ownerUsername, int maxGuests, String type) {
        this.id = id;
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
        this.type = type;
        this.city = city;
    }

    public Attraction() {

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

    public String getCountry() {return country;}

    public void setCountry(String country) {this.country = country;}

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
