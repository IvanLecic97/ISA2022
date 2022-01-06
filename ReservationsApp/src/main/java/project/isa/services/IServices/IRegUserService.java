package project.isa.services.IServices;

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
}
