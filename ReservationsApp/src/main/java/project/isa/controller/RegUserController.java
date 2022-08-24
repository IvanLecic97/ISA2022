package project.isa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.isa.Roles;
import project.isa.dto.*;
import project.isa.model.users.*;
import project.isa.repository.RegUserRepository;
import project.isa.services.EmailSenderService;
import project.isa.services.RegUserService;
import project.isa.services.RegistrationRequestsService;

import javax.annotation.security.RolesAllowed;
import javax.management.relation.Role;
import java.util.List;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/user")
public class RegUserController {
    @Autowired
    private RegUserService regUserService;

    @Autowired
    private RegistrationRequestsService registrationRequestsService;

    @Autowired
    private EmailSenderService emailSenderService;



    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<List<RegUser>> getAllRegUsers()
    {
        List<RegUser> list = regUserService.getAllRegUsers();
        return new ResponseEntity<List<RegUser>>(list, HttpStatus.OK);
    }


    @PostMapping(value = "/registerClient")
    public ResponseEntity<?> registerClient (@RequestBody RegUser user)
    {
        Client c = regUserService.registerClient(user);
        if(c != null)
        {
            return new ResponseEntity<Client>(c, HttpStatus.OK);
        }
        else
            return  new ResponseEntity<String>("error!", HttpStatus.OK);
    }

    @PostMapping(value = "/registerBungalowOwner")
    public ResponseEntity<?> registerBungalowOwner(@RequestBody BungalowOwnerDTO bungalowOwnerDTO){
        BungalowOwnerDTO bungalowOwner = regUserService.registerBungalowOwner(bungalowOwnerDTO);
        if(bungalowOwner != null)
        {
            return new ResponseEntity<BungalowOwnerDTO>(bungalowOwner, HttpStatus.OK);
        }
        else
            return  new ResponseEntity<String>("error!", HttpStatus.OK);
    }

    @PostMapping(value = "/registerShipOwner")
    public ResponseEntity<?> registerShipOwner(@RequestBody ShipOwnerDTO shipOwnerDTO){
        ShipOwnerDTO shipOwnerDTO1 = regUserService.registerShipOwner(shipOwnerDTO);
        if(shipOwnerDTO1 != null){
            return new ResponseEntity<ShipOwnerDTO>(shipOwnerDTO1, HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("error!", HttpStatus.OK);

    }

    @PostMapping(value = "/registerFishingInstructorOwner")
    public ResponseEntity<?> registerInstructor(@RequestBody FishingInstructorOwnerDTO fishingInstructorOwnerDTO){
        FishingInstructorOwnerDTO fishingInstructorOwnerDTO1 = regUserService.registerFishingInstructorOwner(fishingInstructorOwnerDTO);
        if(fishingInstructorOwnerDTO1 != null){
            return new ResponseEntity<FishingInstructorOwnerDTO>(fishingInstructorOwnerDTO1, HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("error!", HttpStatus.OK);

    }

    @PostMapping(value = "/confirmEmail/{email}")
    public ResponseEntity<?> confirmEmail(@PathVariable("email") String email)
    {
        List<RegUser> userList = regUserService.getAllRegUsers();
        String retVal = "";
        for(RegUser u : userList)
        {
            if(u.getUsername().equals(email))
            {
                u.setActivated(true);
                regUserService.saveRegUser(u);
                retVal = "SUCCESS";
            }
            else
                retVal = "FAILURE";
        }
        return new ResponseEntity<String>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/getUser/{username}")
    public ResponseEntity<?> getUser(@PathVariable("username") String username)
    {
        RegUser user = regUserService.getUser(username);


        return new ResponseEntity<RegUser>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody RegUser user)
    {
        regUserService.updateUser(user);
        return new ResponseEntity<String>("Updated!", HttpStatus.OK);

    }

    @RolesAllowed(Roles.ROLE_ADMIN)
    @PostMapping(value = "/approveRegistration")
    public ResponseEntity<?> approveRegistration(@RequestBody RegDisapprovedDTO regDisapprovedDTO){
        regUserService.approveOwnerRegistration(regDisapprovedDTO.getUsername());
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    @RolesAllowed(Roles.ROLE_ADMIN)
    @PostMapping(value = "/disApproveRegistration")
    public ResponseEntity<?> disApproveRegistration(@RequestBody RegDisapprovedDTO regDisapprovedDTO){
        regUserService.disApproveOwnerRegistration(regDisapprovedDTO);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    @RolesAllowed(Roles.ROLE_ADMIN)
    @GetMapping(value = "/getRegistrationRequests")
    public ResponseEntity<List<RegistrationRequest>> getRegRequests(){
        return new ResponseEntity<List<RegistrationRequest>>(registrationRequestsService.getAllRequests(), HttpStatus.OK);
    }

    @RolesAllowed(Roles.ROLE_ADMIN)
    @GetMapping(value = "/getUserByUsername/{username}")
    public ResponseEntity<RegUser> getUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(regUserService.getUser(username), HttpStatus.OK);
    }


    @RolesAllowed(Roles.ROLE_ADMIN)
    @PostMapping(value = "/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteApprovalDTO deleteApprovalDTO){
        RegUser user = regUserService.getUser(deleteApprovalDTO.getUsername());

        if(deleteApprovalDTO.getAllowed()){
            regUserService.deleteUser(user.getId());
            regUserService.deleteDeleteRequest(deleteApprovalDTO.getDeleteRequestId());
            emailSenderService.sendSimpleEmail(deleteApprovalDTO.getUsername(), deleteApprovalDTO.getReason(), "Account deletion");

            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        } else {
            emailSenderService.sendSimpleEmail(deleteApprovalDTO.getUsername(), deleteApprovalDTO.getReason(), "Account deletion");
            regUserService.deleteDeleteRequest(deleteApprovalDTO.getDeleteRequestId());
            return new ResponseEntity<>("User not deleted", HttpStatus.OK);
        }
    }


    @RolesAllowed(Roles.ROLE_CLIENT)
    @PostMapping(value = "/makeDeleteRequest")
    public  ResponseEntity<?> makeDeleteRequest(@RequestBody DeleteRequestDTO deleteRequestDTO){
        return new ResponseEntity<>(regUserService.makeDeleteRequest(deleteRequestDTO), HttpStatus.OK);
    }

    @RolesAllowed(Roles.ROLE_ADMIN)
    @GetMapping(value = "/getDeleteRequests")
    public ResponseEntity<?> getDeleteRequests(){
        return new ResponseEntity<>(regUserService.getAllUnseenByAdmin(), HttpStatus.OK);
    }


}
