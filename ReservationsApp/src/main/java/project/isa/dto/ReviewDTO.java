package project.isa.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDTO {

    private Long id;


    private Double attractionRating;


    private String attractionReview;


    private Double ownerRating;


    private String ownerReview;


    private Long attractionId;


    private String ownerUsername;


    private Long reservationId;


    private Boolean approved;
}
