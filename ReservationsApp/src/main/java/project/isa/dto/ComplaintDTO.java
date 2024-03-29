package project.isa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComplaintDTO {


    private Long id;

    private String clientUsername;

    private String ownerUsername;

    private Long attractionId;

    private String text;

    private Boolean answered;

    private Long reservationId;

    private String ownerAnswer;

    private String clientAnswer;

}
