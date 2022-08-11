package project.isa.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Reservations")
public class Reservations {

    @Id
    @Column(name = "Id")
    @SequenceGenerator(name="gen1",sequenceName = "gen11",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gen1")
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


    public Reservations() {
    }

    public Reservations(Long id, Long attractionId, Long ownerId, Long clientId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.attractionId = attractionId;
        this.ownerId = ownerId;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
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
}
