package project.isa.model.users;


import project.isa.model.entities.FishingInstructor;
import project.isa.model.entities.Ship;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Fishing_instructor_coach")
public class FishingInstructorOwner extends RegUser {
    @Column(name = "Description")
    private String description;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "instructor_users",
            joinColumns = @JoinColumn(name = "owner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id", referencedColumnName = "id"))
    private List<FishingInstructor> instructors;

    public FishingInstructorOwner(Long id, String username, String password, String name, String surname,
                                  String address, String city, String country, String phone, String description, List<FishingInstructor> instructors) {
        super(id, username, password, name, surname, address, city, country, phone);
        this.description = description;
        this.instructors = instructors;
    }

    public FishingInstructorOwner() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FishingInstructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<FishingInstructor> instructors) {
        this.instructors = instructors;
    }

    public void addInstructor(FishingInstructor fishingInstructor) {this.getInstructors().add(fishingInstructor) ; }
}
