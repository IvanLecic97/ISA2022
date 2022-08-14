package project.isa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import project.isa.Roles;
import project.isa.dto.AttractionDTO;
import project.isa.dto.DiscountedEntityDTO;
import project.isa.services.DiscountedEntityService;

//import java.net.http.HttpResponse;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping(value = "api/discount")
public class DiscountController {

    @Autowired
    private DiscountedEntityService discountedEntityService;



    @RolesAllowed({Roles.ROLE_FISHING_INSTRUCTOR, Roles.ROLE_SHIP_OWNER, Roles.ROLE_BUNGALOW_OWNER})
    @PostMapping(value = "/setDiscountedEntity")
    public ResponseEntity<String> setDiscountedEntity(@RequestBody DiscountedEntityDTO discountedEntityDTO){
        discountedEntityService.setEntityOnDiscount(discountedEntityDTO);

        return new ResponseEntity<String>("Entity set on discount!", HttpStatus.OK);
    }

    @GetMapping(value = "/getDiscountedEntities")
    public ResponseEntity<List<DiscountedEntityDTO>> getDiscountedEntities(){
        return new ResponseEntity<List<DiscountedEntityDTO>>(discountedEntityService.getAllDiscountedEntities(), HttpStatus.OK);
    }


}
