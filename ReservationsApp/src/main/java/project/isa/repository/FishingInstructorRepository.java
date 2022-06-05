package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.entities.FishingInstructor;

public interface FishingInstructorRepository extends JpaRepository<FishingInstructor, Long> {
    public FishingInstructor findByIdEquals(FishingInstructor fishingInstructor);
}
