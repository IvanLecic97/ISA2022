package project.isa.services;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.isa.dto.AttractionDTO;
import project.isa.dto.EntityFilterDTO;
import project.isa.model.entities.Attraction;
import project.isa.model.entities.Bungalow;
import project.isa.model.entities.FishingInstructor;
import project.isa.model.entities.Ship;
import project.isa.repository.AttractionRepository;
import project.isa.repository.BungalowRepository;
import project.isa.repository.FishingInstructorRepository;
import project.isa.repository.ShipRepository;
import project.isa.services.IServices.IAttractionService;

import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
public class AttractionService implements IAttractionService {

    @Autowired
    private BungalowRepository bungalowRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private FishingInstructorRepository fishingInstructorRepository;

    @Autowired
    private AttractionRepository attractionRepository;



    @Override
    public void saveBungalow(Bungalow bungalow) {
        bungalowRepository.save(bungalow);
    }

    @Override
    public void saveShip(Ship ship) {
        shipRepository.save(ship);
    }

    @Override
    public void saveFishingInstructor(FishingInstructor fishingInstructor) {
        fishingInstructorRepository.save(fishingInstructor);
    }

    @Override
    public List<Bungalow> getAllBungalows() {
        List<Bungalow> retList = bungalowRepository.findAll();
        return retList;
    }

    @Override
    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }

    @Override
    public List<FishingInstructor> getAllFishingInstructors() {
        return fishingInstructorRepository.findAll();
    }

    @Override
    public List<Attraction> getAllEntities() {
        return attractionRepository.findAll();
    }

    @Override
    public String getType(Attraction attraction) {
        String retVal = null;
        if(bungalowRepository.findByIdEquals(attraction.getId()) != null){
            retVal = "Bungalow";
        }else if(shipRepository.findByIdEquals(attraction.getId()) != null){
                retVal = "Ship";
        } else {
            retVal = "Fishing instructor";
        }


        return retVal;
    }

    @Override
    public List<Attraction> filterByCountry(List<Attraction> attractions, String country) {
        List<Attraction> retList = new ArrayList<Attraction>();
        for(Attraction a : attractions ){
            if(a.getCountry().equals(country)){
                retList.add(a);
            }
            else if(country.equals("")){
                retList = attractions;
            }
        }


        return retList;
    }

    @Override
    public List<Attraction> filterByPrice(List<Attraction> attractions, double price) {
        List<Attraction> retList = new ArrayList<Attraction>();

        for(Attraction a : attractions){
            if(a.getPrice() <= price){
                retList.add(a);
            }
        }



        if(price == 0 ){
            retList = attractions;
        }



        return retList;
    }

    @Override
    public List<Attraction> filterByStartDate(List<Attraction> attractions, String startDate) {
        List<Attraction> retList = new ArrayList<Attraction>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(startDate, formatter) ;


        for(Attraction a : attractions){
            if(date.isEqual(a.getStartDate()) || date.isBefore(a.getStartDate())){
                retList.add(a);
            }
        }

        if(startDate.equals("1900/01/01")){
            retList = attractions;
        }

        return retList;
    }

    @Override
    public List<Attraction> filterByEndDate(List<Attraction> attractions, String endDate) {
        List<Attraction> retList = new ArrayList<Attraction>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(endDate, formatter);


        for(Attraction a : attractions){
            if(date.isEqual(a.getEndDate()) || date.isAfter(a.getEndDate())){
                retList.add(a);
            }
        }

        if(endDate.equals("1900/01/01")){
            retList = attractions;
        }

        return retList;
    }

    @Override
    public List<Attraction> filterByRating(List<Attraction> attractions, double rating) {
        List<Attraction> retList = new ArrayList<Attraction>();

        for(Attraction a : attractions){
            if(a.getRates() >= rating){
                retList.add(a);
            }
        }



        if(rating == 0 ){
            retList = attractions;
        }



        return retList;
    }

    public List<AttractionDTO> getTypes(){
        List<AttractionDTO> retList = new ArrayList<AttractionDTO>();
        for(Attraction a : attractionRepository.findAll()){
            AttractionDTO atr = new AttractionDTO();
            atr.setAttraction(a);
            atr.setType(a.getStartDate().getClass().toString());
            retList.add(atr);
        }

        return retList;

    }

    @Override
    public List<AttractionDTO> filterAttraction(EntityFilterDTO entityFilterDTO) {
        List<AttractionDTO> retVal = new ArrayList<AttractionDTO>();
        List<Attraction> loadedList = attractionRepository.findAll();
        List<Attraction> tempList = filterByCountry(loadedList, entityFilterDTO.getCountry());
        tempList = filterByPrice(tempList, entityFilterDTO.getPrice());
        tempList = filterByStartDate(tempList, entityFilterDTO.getStartDate());
        tempList = filterByEndDate(tempList, entityFilterDTO.getEndDate());
        tempList = filterByRating(tempList, entityFilterDTO.getRating());




        for(Attraction a : tempList){
            AttractionDTO atr = new AttractionDTO();
            atr.setAttraction(a);
            atr.setType(getType(a));
            retVal.add(atr);
        }


        return retVal;
    }

    @Override
    public List<String> getAllCountries() {

        return attractionRepository.getAllDistinctCountries();
    }
}
