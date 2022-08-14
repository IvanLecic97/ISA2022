package project.isa.model.users;


import lombok.*;


import javax.persistence.*;


@Entity
@Table(name = "reservation_requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @Id
    @Column(name = "Id")
    @SequenceGenerator(name="genR",sequenceName = "genR",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "genR")
    private Long id;

   @Column(name = "user_username")
    private String userUsername;




}
