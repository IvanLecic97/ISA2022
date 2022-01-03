package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.users.RegUser;

public interface RegUserRepository extends JpaRepository<RegUser, Long> {
    public RegUser findByUsernameEquals(String username);
    public RegUser findByUsername(String username);

}
