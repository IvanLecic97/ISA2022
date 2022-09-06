package project.isa.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "loyalty_card")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoyaltyCard {

    @Id
    @Column(name = "Id")
    @SequenceGenerator(name="genCard",sequenceName = "genCard",initialValue = 2,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "genCard")
    private Long id;

    @Column(name = "user")
    private String userUsername;

    @Column(name = "category")
    private String category;

    @Column(name = "points")
    private Long points;




}
