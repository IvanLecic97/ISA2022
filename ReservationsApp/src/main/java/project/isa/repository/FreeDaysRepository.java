package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.isa.model.entities.FreeDays;

import java.time.LocalDate;

@Repository
public interface FreeDaysRepository extends JpaRepository<FreeDays, Long> {
    FreeDays findByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);

}
