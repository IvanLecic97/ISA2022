package project.isa.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.GeneratedValue;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationHistoryDTO {


    private Long id;

    private String name;

    private String city;

    private String country;

    private Double price;

    private String startDate;

    private String endDate;

    private Long attractionId;

    private String ownerUsername;

    private boolean isReviewed;
}
