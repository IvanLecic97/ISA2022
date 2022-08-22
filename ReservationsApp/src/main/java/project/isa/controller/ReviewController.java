package project.isa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.isa.Roles;
import project.isa.dto.ReviewDTO;
import project.isa.services.ReviewService;

import javax.annotation.security.RolesAllowed;


@Controller
@RequestMapping(value = "/api/review")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;

    @RolesAllowed(Roles.ROLE_CLIENT)
    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.saveReview(reviewDTO), HttpStatus.OK);
    }

    @RolesAllowed(Roles.ROLE_ADMIN)
    @GetMapping(value = "/getUnseenByAdmin")
    public ResponseEntity<?> getUnseenByAdmin(){
        return new ResponseEntity<>(reviewService.getUnseenByAdmin(), HttpStatus.OK);
    }

    @RolesAllowed(Roles.ROLE_ADMIN)
    @PostMapping(value = "/setApproved")
    public ResponseEntity<?> setApproved(@RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.manageReview(reviewDTO), HttpStatus.OK);
    }


}
