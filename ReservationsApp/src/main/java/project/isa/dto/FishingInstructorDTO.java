package project.isa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FishingInstructorDTO implements Serializable {
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
    private String type;
    private String instructorInfo;
    private boolean fishingEquipment;
}
