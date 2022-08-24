package project.isa.services;


import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import project.isa.dto.ComplaintDTO;
import project.isa.mappers.ComplaintMapper;
import project.isa.model.Complaint;
import project.isa.model.Reservations;
import project.isa.model.users.RegUser;
import project.isa.repository.ComplaintRepository;
import project.isa.repository.ReservationsRepository;
import project.isa.services.IServices.IComplaintService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class ComplaintService implements IComplaintService {

    private ComplaintRepository complaintRepository;

    private ReservationsRepository reservationsRepository;




    @Override
    public ComplaintDTO newComplaint(ComplaintDTO complaintDTO) {
        RegUser user = (RegUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(reservationsRepository.getById(complaintDTO.getReservationId()).getComplained() &&
                Objects.equals(reservationsRepository.getById(complaintDTO.getReservationId()).getClientId(), user.getId())
        ){
            return null;
        }else {
            Complaint complaint = new Complaint();
            complaint.setAttractionId(complaintDTO.getAttractionId());
            complaint.setClientUsername(user.getUsername());
            complaint.setText(complaintDTO.getText());
            complaint.setOwnerUsername(complaintDTO.getOwnerUsername());
            complaint.setAnswered(false);
            Reservations reservation = reservationsRepository.getById(complaintDTO.getReservationId());
            reservation.setComplained(true);
            reservationsRepository.save(reservation);
            complaintRepository.save(complaint);
            return ComplaintMapper.INSTANCE.complaintToDto(complaint);
        }

    }

    @Override
    public void deleteUsersComplaints(String username) {
        List<Long> ids = new ArrayList<>();
        List<Complaint> complaints = complaintRepository.findByClientUsername(username);
        if(complaints != null) {
            complaints.forEach(value -> {
                ids.add(value.getId());
            });

            ids.forEach(value -> {
                complaintRepository.deleteById(value);
            });

        }


    }
}
