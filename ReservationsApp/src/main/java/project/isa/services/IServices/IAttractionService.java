package project.isa.services.IServices;

import project.isa.dto.*;
import project.isa.model.entities.Attraction;
import project.isa.model.entities.Bungalow;
import project.isa.model.entities.FishingInstructor;
import project.isa.model.entities.Ship;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.List;

public interface IAttractionService  {
    void saveBungalow(Bungalow bungalow);
    void saveShip(Ship ship);
    void saveFishingInstructor(FishingInstructor fishingInstructor);
    List<Bungalow> getAllBungalows();
    List<Ship> getAllShips();
    List<FishingInstructor> getAllFishingInstructors();
    List<Attraction> getAllEntities();
    String getType(Attraction attraction);
    List<String> getAllCountries();
    Attraction getById(Long id);

    BungalowDTO addBungalow(BungalowDTO bungalowDTO);

    ShipDTO addShip(ShipDTO shipDTO);

    FishingInstructorDTO addFishingInstructor(FishingInstructorDTO fishingInstructorDTO);

    List<Bungalow> getUsersBungalows(String username);

    List<Ship> getUsersShips(String username);

    List<FishingInstructor> getUsersInstructors(String username);

    List<Attraction> getUsersAttractions(String username);

    List<Attraction> getAllActiveAttractions();


}
