package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.users.ShipOwner;

public interface ShipOwnerRepository extends JpaRepository<ShipOwner, Long> {
}
