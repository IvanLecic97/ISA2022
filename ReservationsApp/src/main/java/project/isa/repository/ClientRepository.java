package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.users.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
