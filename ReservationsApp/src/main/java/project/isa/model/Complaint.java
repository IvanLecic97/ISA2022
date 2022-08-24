package project.isa.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "complaint")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Complaint {

    @Id
    @Column(name = "Id")
    @SequenceGenerator(name="genCmp",sequenceName = "genCmp",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "genCmp")
    private Long id;

    @Column(name = "client")
    private String clientUsername;

    @Column(name = "owner")
    private String ownerUsername;

    @Column(name = "attraction_id")
    private Long attractionId;

    @Column(name = "text")
    private String text;

    @Column(name = "is_answered")
    private Boolean answered;



}
