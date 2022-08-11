package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.entities.DiscountedEntity;

public interface DiscountedEntityRepository extends JpaRepository<DiscountedEntity, Long> {
}
