package project.isa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.isa.dto.DiscountedEntityDTO;
import project.isa.dto.ReservationDTO;
import project.isa.model.Reservations;
import project.isa.model.entities.Attraction;
import project.isa.model.users.RegUser;
import project.isa.repository.AttractionRepository;
import project.isa.repository.RegUserRepository;
import project.isa.repository.ReservationsRepository;
import project.isa.services.IServices.IReservationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


@Service
public class ReservationService implements IReservationService {

    @Autowired
    private ReservationsRepository reservationsRepository;

    @Autowired
    private AttractionService attractionService;

    @Autowired
    private RegUserService regUserService;

    @Autowired
    private AttractionRepository attractionRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private RegUserRepository regUserRepository;

    @Override
    public void saveReservation(Reservations reservations) {
        reservationsRepository.save(reservations);
    }

    @Override
    public void makeReservation(ReservationDTO reservationDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Attraction> attractions = attractionService.getAllEntities();
       List<Attraction> list1 = attractions.stream()
               .filter(attraction -> attraction.getId().equals(reservationDTO.getAttractionId())).collect(Collectors.toList());


       Reservations reservations = new Reservations();
       RegUser owner = regUserRepository.findByUsername(attractionRepository.findByIdEquals(reservationDTO.getAttractionId()).getOwnerUsername());
       Long clientId = regUserService.getUser(reservationDTO.getUsername()).getId();
       reservations.setAttractionId(list1.get(0).getId());
       reservations.setOwnerId(owner.getId());
       reservations.setStartDate(LocalDate.parse(reservationDTO.getStartDate(), formatter));
       reservations.setEndDate(LocalDate.parse(reservationDTO.getEndDate(), formatter));
       reservations.setClientId(clientId);

       Attraction a = attractionService.getById(reservationDTO.getAttractionId());
       a.setReserved(true);
       attractionRepository.save(a);

       reservationsRepository.save(reservations);

       String msg = "You have successfully made  a reservation : " +
               attractionService.getType(list1.get(0)) + " " +
               attractionService.getById(list1.get(0).getId()).getName() + " " +
               "from " + reservationDTO.getStartDate() + " to " + reservationDTO.getEndDate();
        emailSenderService.sendSimpleEmail(reservationDTO.getUsername(), msg, "Reservation confirmaton" );

    }

    @Override
    public String reserveDiscountedEntity(DiscountedEntityDTO discountedEntityDTO, String username) {
        Attraction attraction = attractionService.getById(discountedEntityDTO.getAttractionId());

        Reservations reservations = new Reservations();
        reservations.setAttractionId(discountedEntityDTO.getAttractionId());;
        reservations.setClientId(regUserRepository.findByUsernameEquals(username).getId());
        reservations.setStartDate(attraction.getStartDate());
        reservations.setEndDate(attraction.getEndDate());
        reservations.setOwnerId(regUserRepository.findByUsernameEquals(attraction.getOwnerUsername()).getId());
        reservationsRepository.save(reservations);

        attraction.setReserved(true);
        attractionRepository.save(attraction);

        String msg = "You have successfully made  a reservation : " +
                attraction.getType() + " " +
                attraction.getName() + " " +
                "from " + attraction.getStartDate()+ " to " + attraction.getEndDate();
        emailSenderService.sendSimpleEmail(discountedEntityDTO.getUsername(), msg, "Reservation confirmaton" );

        return "Reservation successfull";
    }
}
