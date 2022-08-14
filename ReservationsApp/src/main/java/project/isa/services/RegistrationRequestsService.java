package project.isa.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.isa.model.users.RegistrationRequest;
import project.isa.repository.RegistrationRequestRepository;
import project.isa.services.IServices.IRegistrationRequestsService;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationRequestsService implements IRegistrationRequestsService {

    private RegistrationRequestRepository registrationRequestRepository;

    @Override
    public List<RegistrationRequest> getAllRequests() {
        return registrationRequestRepository.findAll();
    }
}
