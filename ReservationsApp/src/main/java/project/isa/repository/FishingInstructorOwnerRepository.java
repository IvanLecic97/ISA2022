package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.isa.model.users.FishingInstructorOwner;

@Repository
public interface FishingInstructorOwnerRepository extends JpaRepository<FishingInstructorOwner, Long> {
}
