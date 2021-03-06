package project.isa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.isa.dto.ReservationDTO;
import project.isa.model.Reservations;
import project.isa.model.entities.Attraction;
import project.isa.model.users.RegUser;
import project.isa.repository.AttractionRepository;
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

    @Override
    public void saveReservation(Reservations reservations) {
        reservationsRepository.save(reservations);
    }

    @Override
    public void makeReservation(ReservationDTO reservationDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        List<RegUser> users = regUserService.getAllRegUsers();
        List<Attraction> attractions = attractionService.getAllEntities();
       List<Attraction> list1 = attractions.stream()
               .filter(attraction -> attraction.getId().equals(reservationDTO.getAttractionId())).collect(Collectors.toList());


       Reservations reservations = new Reservations();

       Long ownerId = attractionRepository.getById(reservationDTO.getAttractionId()).getOwnerId();
       reservations.setAttractionId(list1.get(0).getId());
       reservations.setOwnerId(ownerId);
       reservations.setStartDate(LocalDate.parse(reservationDTO.getStartDate(), formatter));
       reservations.setEndDate(LocalDate.parse(reservationDTO.getEndDate(), formatter));

       Attraction a = attractionService.getById(reservationDTO.getAttractionId());
       a.setReserved(true);
       attractionRepository.save(a);

       reservationsRepository.save(reservations);
    }
}
