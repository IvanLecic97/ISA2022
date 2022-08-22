package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.entities.FishingInstructor;

import java.util.List;

public interface FishingInstructorRepository extends JpaRepository<FishingInstructor, Long> {


    List<FishingInstructor> findByOwnerUsername(String ownerUsername);


}
