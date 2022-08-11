package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.isa.model.users.ShipOwner;

@Repository
public interface ShipOwnerRepository extends JpaRepository<ShipOwner, Long> {
    ShipOwner findByUsername(String username);

}
