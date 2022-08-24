package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.isa.dto.ReviewDTO;
import project.isa.model.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAttractionId(Long attractionId);

    List<Review> findBySeenByAdminFalse();

    List<Review> findByClientUsername(String clientUsername);






}
