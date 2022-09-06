package project.isa.services;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.isa.Categories;
import project.isa.Roles;
import project.isa.dto.*;
import project.isa.mappers.BungalowOwnerMapper;
import project.isa.mappers.DeleteRequestMapper;
import project.isa.mappers.FishingInstructorOwnerMapper;
import project.isa.mappers.ShipOwnerMapper;
import project.isa.model.DeleteRequest;
import project.isa.model.LoyaltyCard;
import project.isa.model.entities.Bungalow;
import project.isa.model.users.*;
import project.isa.repository.*;
import project.isa.services.IServices.IRegUserService;

import javax.crypto.Cipher;
import javax.management.relation.Role;
import javax.transaction.Transactional;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;

@Service
@Transactional
@Slf4j
public class RegUserService implements IRegUserService, UserDetailsService {

    @Autowired
    private RegUserRepository regUserRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private BungalowOwnerRepository bungalowOwnerRepository;

    @Autowired
    private ShipOwnerRepository shipOwnerRepository;

    @Autowired
    private FishingInstructorOwnerRepository fishingInstructorOwnerRepository;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private DeleteRequestRepository deleteRequestRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private LoyaltyCardRepository loyaltyCardRepository;





    private static final String ALGO = "AES";


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegUser user = regUserRepository.findByUsername(username);
        if(user == null)
        {
            throw new UsernameNotFoundException("Not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
       return new org.springframework.security.core.userdetails.User(user.getUsername(),
               user.getPassword(), authorities
               );
    }

    @Override
    public void saveRegUser(RegUser user) {
        regUserRepository.save(user);

    }

    @Override
    public List<RegUser> getAllRegUsers() {
        return regUserRepository.findAll();
    }

    @Override
    public void deleteRegUser(RegUser user) {
        regUserRepository.delete(user);
    }

    @Override
   public RegUser getUser(String username)
    {
        return regUserRepository.findByUsername(username);
    }


    @Override
    public Client registerClient(RegUser user) {
        if(regUserRepository.findByUsernameEquals(user.getUsername()) == null)
        {
            Client u = new Client();
            u.setAddress(user.getAddress());
            u.setCity(user.getCity());
            u.setCountry(user.getCountry());
            u.setName(user.getName());
            u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            u.setPhone(user.getPhone());
            u.setRole("ROLE_CLIENT");
            u.setSurname(user.getSurname());
            u.setUsername(user.getUsername());
            u.setActivated(false);

            LoyaltyCard loyaltyCard = new LoyaltyCard();
            loyaltyCard.setCategory(Categories.REGULAR);
            loyaltyCard.setUserUsername(user.getUsername());
            loyaltyCard.setPoints(0L);
            loyaltyCardRepository.save(loyaltyCard);

            /*Authorities authorities = authoritiesRepository.findByName(Roles.ROLE_CLIENT);
            List<Authorities> authorities1 = new ArrayList<Authorities>();
            authorities1.add(authorities);
            u.setAuthorities(authorities1); */


            String msg = "http://localhost:3000/confirm";

            emailSenderService.sendSimpleEmail(u.getUsername(), msg, "Mail messsage");
            clientRepository.save(u);
            return u;
        }
        else
            return null;
    }

    @Override
    public BungalowOwnerDTO registerBungalowOwner(BungalowOwnerDTO bungalowOwnerDTO) {
        if(regUserRepository.findByUsernameEquals(bungalowOwnerDTO.getUsername()) == null){
            BungalowOwner bungalowOwner = BungalowOwnerMapper.INSTANCE.dtoToOwner(bungalowOwnerDTO);
            bungalowOwner.setPassword(bCryptPasswordEncoder.encode(bungalowOwner.getPassword()));
            bungalowOwner.setRole(Roles.ROLE_BUNGALOW_OWNER);
            bungalowOwner.setActivated(false);
            bungalowOwnerRepository.save(bungalowOwner);

            RegistrationRequest registrationRequest = new RegistrationRequest();
            registrationRequest.setUserUsername(bungalowOwner.getUsername());
            registrationRequestRepository.save(registrationRequest);



            return BungalowOwnerMapper.INSTANCE.ownerToDto(bungalowOwner);
        }
        return null;
    }

    @Override
    public ShipOwnerDTO registerShipOwner(ShipOwnerDTO shipOwnerDTO) {
        if(regUserRepository.findByUsernameEquals(shipOwnerDTO.getUsername()) == null){
            ShipOwner shipOwner = ShipOwnerMapper.INSTANCE.dtoToOwner(shipOwnerDTO);
            shipOwner.setPassword(bCryptPasswordEncoder.encode(shipOwner.getPassword()));
            shipOwner.setRole(Roles.ROLE_SHIP_OWNER);
            shipOwner.setActivated(false);
            shipOwnerRepository.save(shipOwner);

            RegistrationRequest registrationRequest = new RegistrationRequest();
            registrationRequest.setUserUsername(shipOwner.getUsername());
            registrationRequestRepository.save(registrationRequest);

            return  ShipOwnerMapper.INSTANCE.ownerToDto(shipOwner);

        }
        return null;
    }

    @Override
    public FishingInstructorOwnerDTO registerFishingInstructorOwner(FishingInstructorOwnerDTO fishingInstructorOwnerDTO) {
        if(regUserRepository.findByUsernameEquals(fishingInstructorOwnerDTO.getUsername()) == null){
            FishingInstructorOwner fishingInstructorOwner = FishingInstructorOwnerMapper.INSTANCE.dtoToOwner(fishingInstructorOwnerDTO);
            fishingInstructorOwner.setPassword(bCryptPasswordEncoder.encode(fishingInstructorOwner.getPassword()));
            fishingInstructorOwner.setRole(Roles.ROLE_FISHING_INSTRUCTOR);
            fishingInstructorOwner.setActivated(false);
            fishingInstructorOwnerRepository.save(fishingInstructorOwner);

            RegistrationRequest registrationRequest = new RegistrationRequest();
            registrationRequest.setUserUsername(fishingInstructorOwner.getUsername());
            registrationRequestRepository.save(registrationRequest);



            return  FishingInstructorOwnerMapper.INSTANCE.ownerToDto(fishingInstructorOwner);

        }
        return null;
    }

    @Override
    public Admin registerAdmin(RegUserDTO regUserDTO) {
        if(regUserRepository.findByUsernameEquals(regUserDTO.getUsername()) == null)
        {
            Admin u = new Admin();
            u.setAddress(regUserDTO.getAddress());
            u.setCity(regUserDTO.getCity());
            u.setCountry(regUserDTO.getCountry());
            u.setName(regUserDTO.getName());
            u.setPassword(bCryptPasswordEncoder.encode(regUserDTO.getPassword()));
            u.setPhone(regUserDTO.getPhone());
            u.setRole(Roles.ROLE_ADMIN);
            u.setSurname(regUserDTO.getSurname());
            u.setUsername(regUserDTO.getUsername());
            u.setActivated(false);

            adminRepository.save(u);
            return u;
        }
        else
            return null;
    }

    @Override
  public  boolean checkIfEnabled(String username)
    {
        boolean retVal = false;
        RegUser user = regUserRepository.findByUsernameEquals(username);
        if(user.getActivated() == true)
        {
            retVal = true;
        }

        return retVal;
    }

    @Override
    public void updateUser(RegUser user)
    {
        RegUser u = regUserRepository.findByUsernameEquals(user.getUsername());
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setAddress(user.getAddress());
        u.setCountry(user.getCountry());
        u.setCity(user.getCity());
        u.setPhone(user.getPhone());

        regUserRepository.save(u);

    }


    @Override
    public void addBungalowToOwner(Bungalow bungalow, String username) {
        BungalowOwner bungalowOwner = bungalowOwnerRepository.findByUsername(username);
        if(bungalowOwner.getBungalowsOwned() == null){
            List<Bungalow> bungalows = new ArrayList<>();
            bungalows.add(bungalow);
            bungalowOwner.setBungalowsOwned(bungalows);

        }
        else {
            bungalowOwner.getBungalowsOwned().add(bungalow);

        }
        bungalowOwnerRepository.save(bungalowOwner);
    }

    @Override
    public void approveOwnerRegistration(String username) {
        RegUser regUser = regUserRepository.findByUsername(username);
        regUser.setActivated(true);
        regUserRepository.save(regUser);
        Long reqId = registrationRequestRepository.findByUserUsername(username).getId();
        registrationRequestRepository.deleteById(reqId);

        emailSenderService.sendSimpleEmail(regUser.getUsername(), "Your registration has been approved!", "Mail messsage");

    }

    @Override
    public void disApproveOwnerRegistration(RegDisapprovedDTO regDisapprovedDTO) {
        RegUser regUser = regUserRepository.findByUsername(regDisapprovedDTO.getUsername());
        regUserRepository.deleteById(regUser.getId());

        Long reqId = registrationRequestRepository.findByUserUsername(regDisapprovedDTO.getUsername()).getId();
        registrationRequestRepository.deleteById(reqId);

        emailSenderService.sendSimpleEmail(regDisapprovedDTO.getUsername(), "Your registration has been rejeceted." +
                        " Reason: " + regDisapprovedDTO.getMessage(),
                "Registration rejected");

    }


    @Override
    public void deleteClientById(Long clientId) {
        clientRepository.deleteById(clientId);
    }


    @Override
    public void deleteUser(Long id) {
        RegUser user = regUserRepository.getById(id);
        complaintService.deleteUsersComplaints(user.getUsername());
        reviewService.deleteUsersReviews(user.getUsername());
        reservationService.deleteAllUsersReservations(user.getId());
        clientRepository.deleteById(id);
    }

    @Override
    public DeleteRequestDTO makeDeleteRequest(DeleteRequestDTO deleteRequestDTO) {
        DeleteRequest deleteRequest = new DeleteRequest();
        RegUser currentUser = (RegUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        deleteRequest.setUserUsername(currentUser.getUsername());
        deleteRequest.setReason(deleteRequestDTO.getReason());
        deleteRequest.setSeenByAdmin(false);
        deleteRequestRepository.save(deleteRequest);


        return DeleteRequestMapper.INSTANCE.requestToDto(deleteRequest);
    }

    @Override
    public List<DeleteRequestDTO> getAllUnseenByAdmin() {
        List<DeleteRequest> list = deleteRequestRepository.findBySeenByAdminFalse();
        return DeleteRequestMapper.INSTANCE.requestsToDtos(list);
    }

    @Override
    public void deleteDeleteRequest(Long id) {
        deleteRequestRepository.deleteById(id);
    }


    @Override
    public String getRole(String username) {

        return regUserRepository.findByUsername(username).getRole();
    }

    @Override
    public boolean getActivated(String username) {
        return regUserRepository.findByUsername(username).getActivated();
    }

    @Override
    public String changeAdminsPassword(String username, String password) {
        RegUser user = regUserRepository.findByUsername(username);
        user.setPassword(password);
        regUserRepository.save(user);
        return "password changed!";
    }
}
