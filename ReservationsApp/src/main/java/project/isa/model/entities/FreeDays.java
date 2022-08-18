package project.isa.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "free_days")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FreeDays {

    @Id
    @Column(name = "Id")
    @SequenceGenerator(name="genFreeDays",sequenceName = "genFreeDays",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "genFreeDays")
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;




}
