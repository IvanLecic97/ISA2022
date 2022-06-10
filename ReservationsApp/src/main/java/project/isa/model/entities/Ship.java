package project.isa.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Ship")
public class Ship extends Attraction {

    @Column(name = "MiniBar")
    private boolean miniBar;

    @Column(name = "Pool")
    private boolean pool;

    @Column(name = "Restaurant")
    private boolean restaurant;


   // public Ship(Long id, String address, String description, Double rates, Double price, boolean reserved, LocalDateTime startDate, LocalDateTime endDate) {
    //    super(id, address, description, rates, price, reserved, startDate, endDate);
  //  }

    public Ship() {
    }

    public Ship(Long id, String address, String country, String description, Double rates, Double price, boolean reserved, LocalDate startDate, LocalDate endDate, boolean miniBar, boolean pool, boolean restaurant, String image, Long ownerId, int maxGuests) {
        super(id, address, country, description, rates, price, reserved, startDate, endDate, image, ownerId, maxGuests);
        this.miniBar = miniBar;
        this.pool = pool;
        this.restaurant = restaurant;
    }

    public Ship(boolean miniBar, boolean pool, boolean restaurant) {
        this.miniBar = miniBar;
        this.pool = pool;
        this.restaurant = restaurant;
    }

    public boolean isMiniBar() {
        return miniBar;
    }

    public void setMiniBar(boolean miniBar) {
        this.miniBar = miniBar;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isRestaurant() {
        return restaurant;
    }

    public void setRestaurant(boolean restaurant) {
        this.restaurant = restaurant;
    }
}
