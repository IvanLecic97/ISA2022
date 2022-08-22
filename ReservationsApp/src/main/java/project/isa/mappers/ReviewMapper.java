package project.isa.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;
import project.isa.dto.ReviewDTO;
import project.isa.model.Review;

import java.util.List;

@Mapper
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    Review dtoToReview(ReviewDTO reviewDTO);
    ReviewDTO reviewToDto(Review review);

    List<ReviewDTO> reviewsToDtos(List<Review> reviews);
}
