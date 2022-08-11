package project.isa.dto;

public class DiscountedEntityDTO {

    private Long attractionId;

    private double oldPrice;

    private double newPrice;

    private String username;

    private String attractionName;

    public DiscountedEntityDTO(Long attractionId, double oldPrice, double newPrice, String username, String attractionName) {
        this.attractionId = attractionId;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.username = username;
        this.attractionName = attractionName;
    }

    public DiscountedEntityDTO() {

    }

    public Long getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(Long attractionId) {
        this.attractionId = attractionId;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }
}
