package project.isa.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.isa.dto.DiscountedEntityDTO;
import project.isa.dto.ReservationDTO;
import project.isa.model.Reservations;
import project.isa.model.entities.Attraction;
import project.isa.model.entities.FreeDays;
import project.isa.model.users.RegUser;
import project.isa.repository.AttractionRepository;
import project.isa.repository.FreeDaysRepository;
import project.isa.repository.RegUserRepository;
import project.isa.repository.ReservationsRepository;
import project.isa.services.IServices.IReservationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private ReservationsRepository reservationsRepository;


    private AttractionService attractionService;


    private RegUserService regUserService;


    private AttractionRepository attractionRepository;


    private EmailSenderService emailSenderService;


    private RegUserRepository regUserRepository;


    private FreeDaysRepository freeDaysRepository;

    @Override
    public void saveReservation(Reservations reservations) {
        reservationsRepository.save(reservations);
    }

    @Override
    public void makeReservation(ReservationDTO reservationDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

       Attraction reservedAttraction = attractionRepository.getById(reservationDTO.getAttractionId());

       Reservations reservations = new Reservations();
       RegUser owner = regUserRepository.findByUsername(attractionRepository.findByIdEquals(reservationDTO.getAttractionId()).getOwnerUsername());
       Long clientId = regUserService.getUser(reservationDTO.getUsername()).getId();
       reservations.setAttractionId(reservedAttraction.getId());
       reservations.setOwnerId(owner.getId());
       LocalDate date1 = LocalDate.parse(reservationDTO.getStartDate(), formatter);
       reservations.setStartDate(date1.minusDays(1));
       LocalDate date2 = LocalDate.parse(reservationDTO.getEndDate(), formatter);
       reservations.setEndDate(date2.minusDays(1));
       reservations.setClientId(clientId);

       Attraction a = attractionService.getById(reservationDTO.getAttractionId());
       attractionRepository.save(a);

       reservationsRepository.save(reservations);

       String msg = "You have successfully made  a reservation : " +
               reservedAttraction.getType() + " " +
               reservedAttraction.getName() + " " +
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

        attractionRepository.save(attraction);

        String msg = "You have successfully made  a reservation : " +
                attraction.getType() + " " +
                attraction.getName() + " " +
                "from " + attraction.getStartDate()+ " to " + attraction.getEndDate();
        emailSenderService.sendSimpleEmail(discountedEntityDTO.getUsername(), msg, "Reservation confirmaton" );

        return "Reservation successfull";
    }

    @Override
    public List<Reservations> getAllByAttractionId(Long attractionId) {
        return reservationsRepository.findByAttractionId(attractionId);
    }

    @Override
    public void setFreeDaysAfterReservation(LocalDate startDate, LocalDate endDate, Attraction attraction) {
        FreeDays startToStartDate = new FreeDays();
        FreeDays endToEndDate = new FreeDays();

        if(attraction.getFreeDaysList() == null) {
            if (startDate.compareTo(attraction.getStartDate()) == 0) {
                endToEndDate.setStartDate(endDate.plusDays(1));
                endToEndDate.setEndDate(attraction.getEndDate());
                attraction.addFreeDays(endToEndDate);
                freeDaysRepository.save(endToEndDate);
                attractionRepository.save(attraction);
            } else if (endDate.compareTo(attraction.getEndDate()) == 0) {
                startToStartDate.setStartDate(attraction.getStartDate());
                startToStartDate.setEndDate(startDate.minusDays(1));
                attraction.addFreeDays(startToStartDate);
                freeDaysRepository.save(startToStartDate);
                attractionRepository.save(attraction);
            } else {
                startToStartDate.setStartDate(attraction.getStartDate());
                startToStartDate.setEndDate(startDate.minusDays(1));
                attraction.addFreeDays(startToStartDate);
                endToEndDate.setStartDate(endDate.plusDays(1));
                endToEndDate.setEndDate(endDate);
                attraction.addFreeDays(endToEndDate);
                freeDaysRepository.save(startToStartDate);
                freeDaysRepository.save(endToEndDate);
                attractionRepository.save(attraction);
            }


        }



    }
}
