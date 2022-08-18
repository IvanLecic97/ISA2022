package project.isa.model.entities;


import project.isa.model.users.RegUser;
import project.isa.model.users.ShipOwner;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Ship")
public class Ship extends Attraction {

    @Column(name = "MiniBar")
    private boolean miniBar;

    @Column(name = "Pool")
    private boolean pool;

    @Column(name = "Restaurant")
    private boolean restaurant;

    public Ship() {
    }

    public Ship(Long id, String name, String address, String country, String city, String description,
                Double rates, Double price, LocalDate startDate, LocalDate endDate, String image, String ownerUsername, int maxGuests,
                String type, List<FreeDays> freeDaysList, boolean miniBar, boolean pool, boolean restaurant) {
        super(id, name, address, country, city, description, rates, price, startDate, endDate, image, ownerUsername, maxGuests, type, freeDaysList);
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
