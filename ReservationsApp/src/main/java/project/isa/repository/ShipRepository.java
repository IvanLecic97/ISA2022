package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.entities.Ship;

import java.util.List;

public interface ShipRepository extends JpaRepository<Ship, Long> {
    public Ship findByIdEquals(Long id);

    List<Ship> findByOwnerUsername(String ownerUsername);



}
