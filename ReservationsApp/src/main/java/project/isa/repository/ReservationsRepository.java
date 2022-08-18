package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.isa.model.Reservations;

import java.util.List;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
    List<Reservations> findByAttractionId(Long attractionId);


}
