package project.isa.services.IServices;

import project.isa.dto.DiscountedEntityDTO;
import project.isa.dto.ReservationDTO;
import project.isa.model.Reservations;
import project.isa.model.entities.Attraction;

import java.time.LocalDate;
import java.util.List;

public interface IReservationService {

    public void makeReservation(ReservationDTO reservationDTO);
    public void saveReservation(Reservations reservations);

    String reserveDiscountedEntity(DiscountedEntityDTO discountedEntityDTO, String username);

    void setFreeDaysAfterReservation(LocalDate startDate, LocalDate endDate, Attraction attraction);

    List<Reservations> getAllByAttractionId(Long attractionId);
}
