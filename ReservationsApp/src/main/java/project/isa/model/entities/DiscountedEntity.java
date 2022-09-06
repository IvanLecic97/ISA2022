package project.isa.model.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Discounted_Entity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiscountedEntity {
    @Id
    @Column(name = "Id")
    @SequenceGenerator(name="gen2",sequenceName = "gen22",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gen2")
    private Long id;

    @Column(name = "Attraction_Id")
    private Long attractionId;

    @Column(name = "Old_Price")
    private double oldPrice;

    @Column(name = "New_Price")
    private double newPrice;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;






}
