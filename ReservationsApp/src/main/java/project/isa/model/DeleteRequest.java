package project.isa.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "delete_requests")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteRequest {

    @Id
    @Column(name = "Id")
    @SequenceGenerator(name="genDel",sequenceName = "genDel",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "genDel")
    private Long id;

    @Column(name = "user_username")
    private String userUsername;

    @Column(name = "deleting_reason")
    private String reason;

    @Column(name = "seen_by_admin")
    private Boolean seenByAdmin;



}
