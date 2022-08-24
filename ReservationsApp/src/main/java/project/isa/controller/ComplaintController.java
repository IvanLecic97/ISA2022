package project.isa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import project.isa.Roles;
import project.isa.dto.ComplaintDTO;
import project.isa.services.ComplaintService;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping(value = "/api/complaint")
@AllArgsConstructor
public class ComplaintController {

    private ComplaintService complaintService;

    @RolesAllowed(Roles.ROLE_CLIENT)
    @PostMapping
    public ResponseEntity<?> newComplaint(@RequestBody ComplaintDTO complaintDTO){
        return new ResponseEntity<>(complaintService.newComplaint(complaintDTO), HttpStatus.OK);
    }

}
