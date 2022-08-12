package project.isa.services.IServices;

import project.isa.dto.DiscountedEntityDTO;
import project.isa.dto.ReservationDTO;
import project.isa.model.Reservations;

public interface IReservationService {

    public void makeReservation(ReservationDTO reservationDTO);
    public void saveReservation(Reservations reservations);

    String reserveDiscountedEntity(DiscountedEntityDTO discountedEntityDTO, String username);
}
