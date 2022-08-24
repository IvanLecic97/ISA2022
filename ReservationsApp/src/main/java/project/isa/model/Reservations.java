package project.isa.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Reservations")
public class Reservations {

    @Id
    @Column(name = "Id")
    @SequenceGenerator(name="genRes",sequenceName = "genRes",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "genRes")
    private Long id;

    @Column(name = "Attraction_Id")
    private Long attractionId;

    @Column(name = "Owner_Id")
    private Long ownerId;

    @Column(name = "Client_Id")
    private Long clientId;

    @Column(name = "Start_date")
    private LocalDate startDate;

    @Column(name = "End_Date")
    private LocalDate endDate;

    @Column(name = "attraction_type")
    private String attractionType;

    @Column(name = "is_reviewed")
    private boolean isReviewed;

    @Column(name = "is_complained")
    private Boolean isComplained;


    public Reservations() {
    }

    public Reservations(Long id, Long attractionId, Long ownerId, Long clientId, LocalDate startDate,
                        LocalDate endDate, String attractionType, boolean isReviewed, Boolean isComplained) {
        this.id = id;
        this.attractionId = attractionId;
        this.ownerId = ownerId;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.attractionType = attractionType;
        this.isReviewed = isReviewed;
        this.isComplained = isComplained;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(Long attractionId) {
        this.attractionId = attractionId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getAttractionType() {
        return attractionType;
    }

    public void setAttractionType(String attractionType) {
        this.attractionType = attractionType;
    }

    public boolean isReviewed() {
        return isReviewed;
    }

    public void setReviewed(boolean reviewed) {
        isReviewed = reviewed;
    }

    public Boolean getComplained() {
        return isComplained;
    }

    public void setComplained(Boolean complained) {
        isComplained = complained;
    }
}
