package project.isa.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "FishingInstructor")
public class FishingInstructor extends Attraction {

    @Column(name = "InstructorInfo")
    private String instructorInfo;

    @Column(name = "FishingEquipment")
    private boolean fishingEquipment;



    public FishingInstructor() {
    }

    public FishingInstructor(Long id, String name, String address, String country, String city, String description, Double rates,
                             Double price, LocalDate startDate, LocalDate endDate, String image, String ownerUsername,
                             int maxGuests, String type, List<FreeDays> freeDaysList, String instructorInfo, boolean fishingEquipment) {
        super(id, name, address, country, city, description, rates, price, startDate, endDate, image, ownerUsername, maxGuests, type, freeDaysList);
        this.instructorInfo = instructorInfo;
        this.fishingEquipment = fishingEquipment;
    }

    public String getInstructorInfo() {
        return instructorInfo;
    }

    public void setInstructorInfo(String instructorInfo) {
        this.instructorInfo = instructorInfo;
    }

    public boolean isFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(boolean fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }
}
