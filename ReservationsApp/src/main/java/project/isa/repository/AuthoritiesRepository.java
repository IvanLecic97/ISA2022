package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.users.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

    public Authorities findByNameEquals(String name);
}
