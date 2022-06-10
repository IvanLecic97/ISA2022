package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.users.FishingInstructorCoach;

public interface FishingInstructorCoachRepository extends JpaRepository<FishingInstructorCoach, Long> {
}
