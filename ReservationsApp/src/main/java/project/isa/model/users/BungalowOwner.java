package project.isa.model.users;

import project.isa.model.entities.Bungalow;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Bungalow_Owner")
public class BungalowOwner extends RegUser {

    @Column(name = "Description")
    private String description;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "bungalow_users",
            joinColumns = @JoinColumn(name = "owner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "bungalow_id", referencedColumnName = "id"))
    private List<Bungalow> bungalowsOwned;

    public BungalowOwner(Long id, String username, String password, String name, String surname,
                         String address, String city, String country, String phone, String description, List<Bungalow> bungalows) {
        super(id, username, password, name, surname, address, city, country, phone);
        this.description = description;
        this.bungalowsOwned = bungalows;
    }

    public BungalowOwner() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Bungalow> getBungalowsOwned() {
        return bungalowsOwned;
    }

    public void setBungalowsOwned(List<Bungalow> bungalowsOwned) {
        this.bungalowsOwned = bungalowsOwned;
    }

    public void addNewBungalow(Bungalow bungalow){
        this.getBungalowsOwned().add(bungalow);
    }

}
