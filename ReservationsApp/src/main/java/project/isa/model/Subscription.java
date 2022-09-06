package project.isa.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "subscription")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subscription {

    @Id
    @Column(name = "Id")
    @SequenceGenerator(name="genSub",sequenceName = "genSub",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "genSub")
    private Long id;

    @Column(name = "attraction_id")
    private Long attractionId;

    @Column(name = "client_username")
    private String clientUsername;

}
