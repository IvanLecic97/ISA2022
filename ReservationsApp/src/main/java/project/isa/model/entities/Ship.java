package project.isa.model.entities;


import project.isa.model.users.RegUser;
import project.isa.model.users.ShipOwner;

import javax.persistence.*;
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

    /*@OneToOne(mappedBy = "id")
    private ShipOwner shipOwner;*/

    


   // public Ship(Long id, String address, String description, Double rates, Double price, boolean reserved, LocalDateTime startDate, LocalDateTime endDate) {
    //    super(id, address, description, rates, price, reserved, startDate, endDate);
  //  }

    public Ship() {
    }

    public Ship(Long id, String address, String country, String city, String description, Double rates, Double price, String type, boolean reserved,
                LocalDate startDate, LocalDate endDate, boolean miniBar, boolean pool, boolean restaurant, String image, String ownerUsername, int maxGuests) {
        super(id, address, country, city, description, rates, price, reserved, startDate, endDate, image, ownerUsername, maxGuests, type);
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
