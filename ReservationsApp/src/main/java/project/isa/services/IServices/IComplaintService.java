package project.isa.services.IServices;

import project.isa.dto.ComplaintDTO;
import project.isa.model.Complaint;

public interface IComplaintService {

    ComplaintDTO newComplaint(ComplaintDTO complaintDTO);

    void deleteUsersComplaints(String username);

}
