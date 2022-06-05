package project.isa.dto;

import java.time.LocalDate;

public class EntityFilterDTO {

    private String country;

    private double price;

    private String startDate;

    private String endDate;

    private double rating;

    public EntityFilterDTO(String country, double price, String startDate, String endDate, double rating) {
        this.country = country;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rating = rating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    private EntityFilterDTO() {


    }
}
