package project.isa.model.entities;


import javax.persistence.*;

@Entity
@Table(name = "Discounted_Entity")
public class DiscountedEntity {
    @Id
    @Column(name = "Id")
    @SequenceGenerator(name="gen2",sequenceName = "gen22",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gen2")
    private Long id;

    @Column(name = "Attraction_Id")
    private Long attractionId;

    @Column(name = "Old_Price")
    private double oldPrice;

    @Column(name = "New_Price")
    private double newPrice;



    public DiscountedEntity() {
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
}
