package project.isa.services.IServices;

import project.isa.dto.ComplaintDTO;
import project.isa.model.Complaint;

import java.util.List;

public interface IComplaintService {

    ComplaintDTO newComplaint(ComplaintDTO complaintDTO);

    void deleteUsersComplaints(String username);

    List<ComplaintDTO> getAllNotAnswered();

    ComplaintDTO manageComplaint(ComplaintDTO complaintDTO);

}
