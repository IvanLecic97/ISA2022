package project.isa.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteApprovalDTO {

    private String username;

    private Long deleteRequestId;

    private String reason;

    private Boolean allowed;

}
