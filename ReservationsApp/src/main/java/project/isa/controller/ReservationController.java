package project.isa.controller;

import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import project.isa.dto.ReservationDTO;
import project.isa.services.ReservationService;

@Controller
@RequestMapping("api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping(value = "/makeReservation")
    public ResponseEntity<?> makeReservation(@RequestBody ReservationDTO reservationDTO){

        reservationService.makeReservation(reservationDTO);;

        return new ResponseEntity<String>("Reservation made!", HttpStatus.OK);

    }
}
