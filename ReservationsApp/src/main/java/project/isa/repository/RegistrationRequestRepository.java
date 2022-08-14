package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.isa.model.users.RegistrationRequest;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
    RegistrationRequest findByUserUsername(String userUsername);


}
