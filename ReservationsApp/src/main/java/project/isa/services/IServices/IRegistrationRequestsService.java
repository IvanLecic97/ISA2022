package project.isa.services.IServices;

import project.isa.model.users.RegistrationRequest;

import java.util.List;

public interface IRegistrationRequestsService {

    List<RegistrationRequest> getAllRequests();

}
