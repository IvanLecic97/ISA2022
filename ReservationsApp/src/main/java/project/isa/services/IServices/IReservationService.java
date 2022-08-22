package project.isa.services.IServices;

import project.isa.dto.DiscountedEntityDTO;
import project.isa.dto.ReservationDTO;
import project.isa.dto.ReservationHistoryDTO;
import project.isa.model.Reservations;
import project.isa.model.entities.Attraction;
import project.isa.model.entities.Bungalow;
import project.isa.model.entities.FishingInstructor;
import project.isa.model.entities.Ship;

import java.time.LocalDate;
import java.util.List;

public interface IReservationService {

    public void makeReservation(ReservationDTO reservationDTO);
    public void saveReservation(Reservations reservations);

    String reserveDiscountedEntity(DiscountedEntityDTO discountedEntityDTO, String username);


    List<Reservations> getAllByAttractionId(Long attractionId);

    List<ReservationHistoryDTO> getClientsReservedBungalows(String clientUsername);

    List<ReservationHistoryDTO> getClientsReservedShips(String clientUsername);

    List<ReservationHistoryDTO> getClientsReservedInstructors(String clientUsername);

}
