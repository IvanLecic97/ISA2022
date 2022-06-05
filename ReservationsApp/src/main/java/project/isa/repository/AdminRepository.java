package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.users.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
