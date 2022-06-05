package project.isa.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "FishingInstructor")
public class FishingInstructor extends Attraction {

    @Column(name = "InstructorInfo")
    private String instructorInfo;

    @Column(name = "FishingEquipment")
    private boolean fishingEquipment;

    public FishingInstructor(Long id, String address, String country, String description, Double rates, Double price, boolean reserved, LocalDate startDate, LocalDate endDate, String image) {
        super(id, address, country, description, rates, price, reserved, startDate, endDate, image);
    }

    public FishingInstructor() {
    }

    public FishingInstructor(Long id, String address, String country, String description, Double rates, Double price, boolean reserved, LocalDate startDate, LocalDate endDate, String instructorInfo, boolean fishingEquipment, String image) {
        super(id, address, country,description, rates, price, reserved, startDate, endDate, image);
        this.instructorInfo = instructorInfo;
        this.fishingEquipment = fishingEquipment;
    }

    public FishingInstructor(String instructorInfo, boolean fishingEquipment) {
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
