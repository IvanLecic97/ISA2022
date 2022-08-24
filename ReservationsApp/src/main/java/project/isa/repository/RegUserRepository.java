package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.isa.model.users.RegUser;

import java.util.Optional;

@Repository
public interface RegUserRepository extends JpaRepository<RegUser, Long> {
    public RegUser findByUsernameEquals(String username);
    public RegUser findByUsername(String username);




}
