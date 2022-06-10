package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.Reservations;

public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
}
