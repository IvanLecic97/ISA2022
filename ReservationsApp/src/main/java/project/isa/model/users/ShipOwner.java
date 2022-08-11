package project.isa.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import project.isa.model.entities.Ship;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "Ship_Owner")
public class ShipOwner extends RegUser {
    @Column(name = "Description")
    private String description;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "ships_user",
                joinColumns = @JoinColumn(name = "owner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ship_id", referencedColumnName = "id"))
    private List<Ship> shipsOwned;


    public ShipOwner(Long id, String username, String password, String name, String surname,
                     String address, String city, String country, String phone, String description, List<Ship> ships) {
        super(id, username, password, name, surname, address, city, country, phone);
        this.description = description;
        this.shipsOwned = ships;
    }

    public ShipOwner() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ship> getShipsOwned() {
        return shipsOwned;
    }

    public void setShipsOwned(List<Ship> shipsOwned) {
        this.shipsOwned = shipsOwned;
    }

    public void addNewShip(Ship ship) { this.getShipsOwned().add(ship) ;}
}
