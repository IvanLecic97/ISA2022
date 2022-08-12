package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.isa.model.entities.DiscountedEntity;

@Repository
public interface DiscountedEntityRepository extends JpaRepository<DiscountedEntity, Long> {
    DiscountedEntity findByAttractionId(Long attractionId);


}
