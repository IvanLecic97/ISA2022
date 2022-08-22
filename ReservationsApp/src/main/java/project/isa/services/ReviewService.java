package project.isa.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import project.isa.dto.ReviewDTO;
import project.isa.mappers.ReviewMapper;
import project.isa.model.Reservations;
import project.isa.model.Review;
import project.isa.model.entities.Attraction;
import project.isa.model.users.RegUser;
import project.isa.repository.AttractionRepository;
import project.isa.repository.RegUserRepository;
import project.isa.repository.ReservationsRepository;
import project.isa.repository.ReviewRepository;
import project.isa.services.IServices.IReviewService;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService implements IReviewService {

    private ReviewRepository reviewRepository;

    private RegUserRepository regUserRepository;

    private AttractionRepository attractionRepository;

    private ReservationsRepository reservationsRepository;



    @Override
    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        if(reservationsRepository.getById(reviewDTO.getReservationId()).isReviewed())
        {
            return null;
        } else {
            Review review = ReviewMapper.INSTANCE.dtoToReview(reviewDTO);
            RegUser loggedUser = (RegUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            review.setClientUsername(loggedUser.getUsername());
            review.setApproved(false);
            review.setSeenByAdmin(false);
            Double grade = attractionRepository.getById(review.getAttractionId()).getRates();
            Attraction a = attractionRepository.getById(review.getAttractionId());
            List<Review> reviews = reviewRepository.findByAttractionId(a.getId());
            //List<Double> rates = new ArrayList<>();
            Double rates = 0.0;
            for (Review r : reviews) {
                rates += r.getAttractionRating();
            }
            int count = reviews.size() + 1;
            a.setRates((rates + review.getAttractionRating()) / (count));
            Reservations reservations = reservationsRepository.getById(reviewDTO.getReservationId());
            reservations.setReviewed(true);
            reservationsRepository.save(reservations);
            attractionRepository.save(a);
            reviewRepository.save(review);


            return ReviewMapper.INSTANCE.reviewToDto(review);
        }
    }


    @Override
    public List<ReviewDTO> getUnseenByAdmin() {
        return ReviewMapper.INSTANCE.reviewsToDtos(reviewRepository.findBySeenByAdminFalse());
    }

    @Override
    public ReviewDTO manageReview(ReviewDTO reviewDTO) {
        Review review = reviewRepository.getById(reviewDTO.getId());
        if(reviewDTO.getApproved()){
            review.setApproved(true);
            review.setSeenByAdmin(true);
        } else {
            review.setSeenByAdmin(true);
        }
        reviewRepository.save(review);

        return ReviewMapper.INSTANCE.reviewToDto(review);
    }
}
