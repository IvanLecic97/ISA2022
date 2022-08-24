package project.isa.services.IServices;

import project.isa.dto.*;
import project.isa.model.DeleteRequest;
import project.isa.model.entities.Bungalow;
import project.isa.model.users.BungalowOwner;
import project.isa.model.users.Client;
import project.isa.model.users.RegUser;

import java.util.List;

public interface IRegUserService {
    void saveRegUser(RegUser user);
    List<RegUser> getAllRegUsers();
    void deleteRegUser(RegUser user);
    Client registerClient(RegUser user);
    boolean checkIfEnabled(String username);
    RegUser getUser(String username);
    void updateUser(RegUser user);
    BungalowOwnerDTO registerBungalowOwner(BungalowOwnerDTO bungalowOwnerDTO);
    ShipOwnerDTO registerShipOwner(ShipOwnerDTO shipOwnerDTO);
    FishingInstructorOwnerDTO registerFishingInstructorOwner(FishingInstructorOwnerDTO fishingInstructorOwnerDTO);

    void addBungalowToOwner(Bungalow bungalow, String username);

    void approveOwnerRegistration(String username);

    void disApproveOwnerRegistration(RegDisapprovedDTO regDisapprovedDTO);

    void deleteClientById(Long clientId);

    void deleteUser(Long id);

    DeleteRequestDTO makeDeleteRequest(DeleteRequestDTO deleteRequestDTO);

    List<DeleteRequestDTO> getAllUnseenByAdmin();

    void deleteDeleteRequest(Long id);


}
