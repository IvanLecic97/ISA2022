package project.isa.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.isa.dto.DiscountedEntityDTO;
import project.isa.dto.ReservationDTO;
import project.isa.dto.ReservationHistoryDTO;
import project.isa.model.Reservations;
import project.isa.model.Review;
import project.isa.model.entities.*;
import project.isa.model.users.RegUser;
import project.isa.repository.*;
import project.isa.services.IServices.IReservationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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


    private BungalowRepository bungalowRepository;


    private ShipRepository shipRepository;


    private FishingInstructorRepository fishingInstructorRepository;





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
       reservations.setAttractionType(reservedAttraction.getType());
       reservations.setComplained(false);

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
        reservations.setAttractionId(discountedEntityDTO.getAttractionId());
        reservations.setClientId(regUserRepository.findByUsernameEquals(username).getId());
        reservations.setStartDate(attraction.getStartDate());
        reservations.setEndDate(attraction.getEndDate());
        reservations.setOwnerId(regUserRepository.findByUsernameEquals(attraction.getOwnerUsername()).getId());
        reservations.setReviewed(false);
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
    public List<ReservationHistoryDTO> getClientsReservedBungalows(String clientUsername) {
        List<Reservations> reservations = reservationsRepository.findByClientId(regUserRepository.findByUsername(clientUsername).getId())
                .stream().filter(value -> value.getAttractionType().equals("Bungalow")).collect(Collectors.toList());
        List<ReservationHistoryDTO> reservationHistoryDTOList = new ArrayList<>();
        reservations.forEach(value -> {
            Attraction a = attractionRepository.getById(value.getAttractionId());
            ReservationHistoryDTO r = new ReservationHistoryDTO();
            r.setId(value.getId());
            r.setName(a.getName());
            r.setCity(a.getCity());
            r.setCountry(a.getCountry());
            r.setStartDate(value.getStartDate().toString());
            r.setEndDate(value.getEndDate().toString());
            r.setPrice(a.getPrice());
            r.setAttractionId(a.getId());
            r.setOwnerUsername(a.getOwnerUsername());
            r.setReviewed(value.isReviewed());
            reservationHistoryDTOList.add(r);
        });

        return  reservationHistoryDTOList;
    }

    @Override
    public List<ReservationHistoryDTO> getClientsReservedShips(String clientUsername) {
            List<Reservations> reservations = reservationsRepository.findByClientId(regUserRepository.findByUsername(clientUsername).getId())
                    .stream().filter(value -> value.getAttractionType().equals("Ship")).collect(Collectors.toList());
            List<ReservationHistoryDTO> reservationHistoryDTOList = new ArrayList<>();
            reservations.forEach(value -> {
                Attraction a = attractionRepository.getById(value.getAttractionId());
                ReservationHistoryDTO r = new ReservationHistoryDTO();
                r.setId(value.getId());
                r.setName(a.getName());
                r.setCity(a.getCity());
                r.setCountry(a.getCountry());
                r.setStartDate(value.getStartDate().toString());
                r.setEndDate(value.getEndDate().toString());
                r.setPrice(a.getPrice());
                r.setAttractionId(a.getId());
                r.setOwnerUsername(a.getOwnerUsername());
                r.setReviewed(value.isReviewed());
                reservationHistoryDTOList.add(r);
            });

            return  reservationHistoryDTOList;
    }

    @Override
    public List<ReservationHistoryDTO> getClientsReservedInstructors(String clientUsername) {
        List<Reservations> reservations = reservationsRepository.findByClientId(regUserRepository.findByUsername(clientUsername).getId())
                .stream().filter(value -> value.getAttractionType().equals("Fishing instructor"))
                        .collect(Collectors.toList());
        List<ReservationHistoryDTO> reservationHistoryDTOList = new ArrayList<>();
        reservations.forEach(value -> {
            Attraction a = attractionRepository.getById(value.getAttractionId());
            ReservationHistoryDTO r = new ReservationHistoryDTO();
            r.setId(value.getId());
            r.setName(a.getName());
            r.setCity(a.getCity());
            r.setCountry(a.getCountry());
            r.setStartDate(value.getStartDate().toString());
            r.setEndDate(value.getEndDate().toString());
            r.setPrice(a.getPrice());
            r.setAttractionId(a.getId());
            r.setOwnerUsername(a.getOwnerUsername());
            r.setReviewed(value.isReviewed());
            reservationHistoryDTOList.add(r);
        });

        return  reservationHistoryDTOList;
    }


    @Override
    public void deleteAllUsersReservations(Long clientId) {
        List<Long> ids = new ArrayList<>();
        List<Reservations> reservations = reservationsRepository.findByClientId(clientId);
        if(reservations != null) {
            reservations.forEach(value -> {
                ids.add(value.getId());
            });

            ids.forEach(value -> {
                reservationsRepository.deleteById(value);
            });
        }
    }
}
