package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.isa.model.users.Authorities;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

     Authorities findByNameEquals(String name);

    Authorities findByName(String name);

}
