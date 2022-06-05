package project.isa.services.IServices;

import project.isa.dto.AttractionDTO;
import project.isa.dto.EntityFilterDTO;
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
    List<AttractionDTO> filterAttraction(EntityFilterDTO entityFilterDTO);
    List<Attraction> filterByCountry(List<Attraction> attractions, String country);
    List<Attraction> filterByPrice(List<Attraction> attractions, double price);
    List<Attraction> filterByStartDate(List<Attraction> attractions, String startDate);
    List<Attraction> filterByEndDate(List<Attraction> attractions, String endDate);
    List<Attraction> filterByRating(List<Attraction> attractions, double rating);
    List<String> getAllCountries();

}
