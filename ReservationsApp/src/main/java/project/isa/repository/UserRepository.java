package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.Users.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
