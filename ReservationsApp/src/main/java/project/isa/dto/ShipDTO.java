package project.isa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ShipDTO  {

    private  String name;
    private  String address;
    private  String country;
    private  String city;
    private  String description;
    private  Double rates;
    private  Double price;
    private  boolean reserved;
    private  LocalDate startDate;
    private  LocalDate endDate;
    private  String image;
    private  String ownerUsername;
    private  int maxGuests;
    private  String type;
    private  boolean miniBar;
    private  boolean pool;
    private  boolean restaurant;
}
