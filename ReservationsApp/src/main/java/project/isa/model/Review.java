package project.isa.model;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Review")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {


    @Id
    @Column(name = "Id")
    @SequenceGenerator(name="genRvw",sequenceName = "genRvw",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "genRvw")
    private Long id;

    @Column(name = "attraction_rating")
    private Double attractionRating;

    @Column(name = "attraction_review")
    private String attractionReview;

    @Column(name = "owner_rating")
    private Double ownerRating;

    @Column(name = "owner_review")
    private String ownerReview;

    @Column(name = "attraction_id")
    private Long attractionId;

    @Column(name = "owner_username")
    private String ownerUsername;

    @Column(name = "client_username")
    private String clientUsername;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "seen_by_admin")
    private Boolean seenByAdmin;

}
