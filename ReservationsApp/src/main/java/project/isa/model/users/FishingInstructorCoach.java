package project.isa.model.users;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Fishing_Instructor_Coach")
public class FishingInstructorCoach extends RegUser {

    public FishingInstructorCoach(Long id, String username, String password, String name, String surname, String address, String city, String country, String phone) {
        super(id, username, password, name, surname, address, city, country, phone);
    }

    public FishingInstructorCoach() {

    }
}
