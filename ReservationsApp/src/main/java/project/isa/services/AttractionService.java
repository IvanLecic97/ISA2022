package project.isa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.isa.dto.AttractionDTO;
import project.isa.dto.BungalowDTO;
import project.isa.dto.FishingInstructorDTO;
import project.isa.dto.ShipDTO;
import project.isa.mappers.BungalowMapper;
import project.isa.mappers.FishingInstructorMapper;
import project.isa.mappers.ShipMapper;
import project.isa.model.entities.Attraction;
import project.isa.model.entities.Bungalow;
import project.isa.model.entities.FishingInstructor;
import project.isa.model.entities.Ship;
import project.isa.model.users.BungalowOwner;
import project.isa.model.users.FishingInstructorOwner;
import project.isa.model.users.ShipOwner;
import project.isa.repository.*;
import project.isa.services.IServices.IAttractionService;

import java.util.ArrayList;
import java.util.List;


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

    @Autowired
    private BungalowOwnerRepository bungalowOwnerRepository;

    @Autowired
    private ShipOwnerRepository shipOwnerRepository;

    @Autowired
    private FishingInstructorOwnerRepository fishingInstructorOwnerRepository;

    @Autowired
    private RegUserService regUserService;



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

    @Override
    public BungalowDTO addBungalow(BungalowDTO bungalowDTO) {
        System.out.println(bungalowDTO);
        Bungalow bungalow = BungalowMapper.INSTANCE.dtoToBungalow(bungalowDTO);
        bungalow.setType("Bungalow");
        bungalowRepository.save(bungalow);

        BungalowOwner bungalowOwner = bungalowOwnerRepository.findByUsername(bungalowDTO.getOwnerUsername());
        bungalowOwner.addNewBungalow(bungalow);
        bungalowOwnerRepository.save(bungalowOwner);

        return BungalowMapper.INSTANCE.bungalowToDto(bungalow);
    }

    @Override
    public ShipDTO addShip(ShipDTO shipDTO) {
        Ship ship = ShipMapper.INSTANCE.dtoToShip(shipDTO);
        shipRepository.save(ship);

        ShipOwner shipOwner = shipOwnerRepository.findByUsername(shipDTO.getOwnerUsername());
        shipOwner.addNewShip(ship);
        shipOwnerRepository.save(shipOwner);

        return ShipMapper.INSTANCE.shipToDto(ship);
    }

    @Override
    public FishingInstructorDTO addFishingInstructor(FishingInstructorDTO fishingInstructorDTO) {
        FishingInstructor fishingInstructor = FishingInstructorMapper.INSTANCE.dtoToInstructor(fishingInstructorDTO);
        fishingInstructorRepository.save(fishingInstructor);

        FishingInstructorOwner fishingInstructorOwner = fishingInstructorOwnerRepository.findByUsername(fishingInstructor.getOwnerUsername());
        fishingInstructorOwner.addInstructor(fishingInstructor);
        fishingInstructorOwnerRepository.save(fishingInstructorOwner);

        return FishingInstructorMapper.INSTANCE.instructorToDto(fishingInstructor);
    }
}
