package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.isa.model.users.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
