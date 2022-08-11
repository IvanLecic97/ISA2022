package project.isa.dto;

public class ReservationDTO {
    private Long Id;

    private Long attractionId;

    private Long ownerId;

    private String username;

    private Long clientId;

    private String startDate;

    private String endDate;


    public ReservationDTO(Long id, Long attractionId, Long ownerId, Long clientId, String username, String startDate, String endDate) {
        Id = id;
        this.attractionId = attractionId;
        this.ownerId = ownerId;
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientId = clientId;
    }

    public ReservationDTO() {
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
