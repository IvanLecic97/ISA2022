package project.isa.controller;


import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Attr;
import project.isa.Roles;
import project.isa.dto.*;
import project.isa.model.entities.Attraction;
import project.isa.model.entities.Bungalow;
import project.isa.model.entities.FishingInstructor;
import project.isa.model.entities.Ship;
import project.isa.services.AttractionService;

import javax.annotation.security.RolesAllowed;
//import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/attraction")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @RolesAllowed("ROLE_BUNGALOW_OWNER")
    @PostMapping(value = "/addBungalow")
    public ResponseEntity<String> addBungalow(@RequestBody BungalowDTO bungalowDTO){
        /*Bungalow b = new Bungalow();
        b.setAirConditioner(bungalow.isAirConditioner());
        b.setFridge(bungalow.isFridge());
        b.setKitchenAppliances(bungalow.isKitchenAppliances());
        b.setTv(bungalow.isTv());
        b.setWifi(bungalow.isWifi());
        b.setAddress(bungalow.getAddress());
        b.setDescription(bungalow.getDescription());
        b.setEndDate(bungalow.getEndDate());
        b.setName(bungalow.getName());
        b.setPrice(bungalow.getPrice());
        b.setRates(0.0);
        b.setReserved(false);
        b.setCountry(bungalow.getCountry());
        b.setStartDate(bungalow.getStartDate());
        attractionService.saveBungalow(b); */
        BungalowDTO bungalow = attractionService.addBungalow(bungalowDTO);
        if(bungalow != null){
            return new ResponseEntity<String>("Bungalow added!", HttpStatus.OK);
        }
        else return new ResponseEntity<String>("Error!!", HttpStatus.OK);
    }

    @RolesAllowed("ROLE_SHIP_OWNER")
    @PostMapping(value = "/addShip")
    public ResponseEntity<String> addShip(@RequestBody ShipDTO ship){
        ShipDTO shipDTO = attractionService.addShip(ship);
        if(shipDTO != null){
            return new ResponseEntity<String>("Ship is added!", HttpStatus.OK);
        }
        else return new ResponseEntity<String>("Error!!", HttpStatus.OK);


    }

    @RolesAllowed(Roles.ROLE_FISHING_INSTRUCTOR)
    @PostMapping(value = "/addFishingInstructor")
    public ResponseEntity<String> addFishingInstructor(@RequestBody FishingInstructorDTO fishingInstructor){
        FishingInstructorDTO fishingInstructorDTO = attractionService.addFishingInstructor(fishingInstructor);
        if(fishingInstructorDTO != null){
            return new ResponseEntity<String>("Fishing instructor added!", HttpStatus.OK);
        }
        else return new ResponseEntity<String>("Error!!", HttpStatus.OK);

    }

    @GetMapping(value = "/getBungalows")
    public ResponseEntity<List<Bungalow>> getAllBungalows(){
        return new ResponseEntity<List<Bungalow>>(attractionService.getAllBungalows(), HttpStatus.OK);
    }

    @GetMapping(value = "/getShips")
    public ResponseEntity<List<Ship>> getAllShips(){
        return new ResponseEntity<List<Ship>>(attractionService.getAllShips(), HttpStatus.OK);
    }

    @GetMapping(value = "/getFishingInstructors")
    public ResponseEntity<List<FishingInstructor>> getAllFishingInstructors(){
        return new ResponseEntity<List<FishingInstructor>>(attractionService.getAllFishingInstructors(), HttpStatus.OK);
    }

    @GetMapping(value = "/getAttractions")
    @RolesAllowed("ROLE_CLIENT")
    public ResponseEntity<List<AttractionDTO>> getAllFreeAttractions(){
        List<Attraction> list1 = attractionService.getAllFreeAttractions();
        List<AttractionDTO> list2 = new ArrayList<AttractionDTO>();
        for(Attraction a : list1){
            AttractionDTO atr = new AttractionDTO();
            atr.setAttraction(a);
            atr.setType(attractionService.getType(a));
            list2.add(atr);
        }

      //  HttpHeaders response =  new HttpHeaders();


        return new ResponseEntity<List<AttractionDTO>>(list2, HttpStatus.OK);
    }

    @RolesAllowed(Roles.ROLE_CLIENT)
    @GetMapping(value = "/getType")
    public ResponseEntity<List<String>> getType(){
        List<String> retList = new ArrayList<>();
        for(Attraction a : attractionService.getAllEntities()){
            retList.add(attractionService.getType(a));
        }

        return new ResponseEntity<List<String>>(retList, HttpStatus.OK);
    }

    @RolesAllowed(Roles.ROLE_CLIENT)
    @GetMapping(value = "/getTypeOf")
    public ResponseEntity<List<AttractionDTO>> getTypeof(){
        List<AttractionDTO> retVal = attractionService.getTypes();


        return new ResponseEntity<List<AttractionDTO>>(retVal, HttpStatus.OK);

    }

    @RolesAllowed(Roles.ROLE_CLIENT)
    @GetMapping(value = "/getAllCountries")
    public ResponseEntity<List<String>> getAllCountries(){
        return new ResponseEntity<List<String>>(attractionService.getAllCountries(), HttpStatus.OK);
    }

    @RolesAllowed(Roles.ROLE_CLIENT)
    @GetMapping(value = "/getAttractionById/{id}")
    public ResponseEntity<Attraction> getAttractionByID(@PathVariable Long id){
        return new ResponseEntity<Attraction>(attractionService.getById(id), HttpStatus.OK);
    }

}
