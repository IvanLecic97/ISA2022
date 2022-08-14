package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.entities.Bungalow;

import java.util.List;

public interface BungalowRepository extends JpaRepository<Bungalow, Long> {
    public Bungalow findByIdEquals(Long id);

    List<Bungalow> findByOwnerUsername(String ownerUsername);



}
