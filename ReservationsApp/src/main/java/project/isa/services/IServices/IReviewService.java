package project.isa.services.IServices;



import java.util.List;
import project.isa.dto.ReviewDTO;
import project.isa.model.Review;

public interface IReviewService {
    ReviewDTO saveReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getUnseenByAdmin();

    ReviewDTO manageReview(ReviewDTO reviewDTO);


}
