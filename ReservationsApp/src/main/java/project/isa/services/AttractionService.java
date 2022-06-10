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
    public Attraction getById(Long id) {
        return attractionRepository.findByIdEquals(id);
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
    public List<Attraction> getAllFreeAttractions() {
        return attractionRepository.findAllByReservedEquals(false);
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
    public List<String> getAllCountries() {

        return attractionRepository.getAllDistinctCountries();
    }
}
