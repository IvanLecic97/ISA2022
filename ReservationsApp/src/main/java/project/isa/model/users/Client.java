package project.isa.model.users;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Client")
public class Client extends RegUser {


    public Client(Long id, String username, String password, String name, String surname, String address, String city, String country, String phone) {
        super(id, username, password, name, surname, address, city, country, phone);
    }

    public Client() {

    }
}
