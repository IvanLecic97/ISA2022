package project.isa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.isa.Roles;
import project.isa.dto.DiscountedEntityDTO;
import project.isa.dto.ReservationDTO;
import project.isa.model.Reservations;
import project.isa.model.users.RegUser;
import project.isa.services.ReservationService;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RolesAllowed(Roles.ROLE_CLIENT)
    @PostMapping(value = "/makeReservation")
    public ResponseEntity<?> makeReservation(@RequestBody ReservationDTO reservationDTO){

        reservationService.makeReservation(reservationDTO);;

        return new ResponseEntity<String>("Reservation made!", HttpStatus.OK);

    }

    @RolesAllowed(Roles.ROLE_CLIENT)
    @PostMapping(value = "/makeDiscountReservation")
    public ResponseEntity<?> makeDiscountedReservation(@RequestBody DiscountedEntityDTO discountedEntityDTO){

        RegUser regUser = (RegUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = regUser.getUsername();

       String retVal = reservationService.reserveDiscountedEntity(discountedEntityDTO, username);

       return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @RolesAllowed({Roles.ROLE_CLIENT, Roles.ROLE_BUNGALOW_OWNER, Roles.ROLE_SHIP_OWNER, Roles.ROLE_FISHING_INSTRUCTOR})
    @GetMapping(value = "/getAllByAttractionId/{id}")
    public ResponseEntity<?> getAllByAttractionId(@PathVariable Long id){
        return new ResponseEntity<>(reservationService.getAllByAttractionId(id), HttpStatus.OK);
    }
}
