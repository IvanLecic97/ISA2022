package project.isa.model.users;


import javax.persistence.*;

@Entity
@Table(name = "Admin")
public class Admin extends RegUser  {

    public Admin(Long id, String username, String password, String name, String surname, String address, String city, String country, String phone) {
        super(id, username, password, name, surname, address, city, country, phone);
    }

    public Admin()
    {}
}
