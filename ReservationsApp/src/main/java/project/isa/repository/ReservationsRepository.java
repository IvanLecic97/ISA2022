package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.isa.model.Reservations;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Long> {

}
