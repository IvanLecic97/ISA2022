package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.isa.model.entities.Attraction;

import java.time.LocalDate;
import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    public boolean findByCountryEquals(String country);
    public List<Attraction> findAllByPriceLessThanEqual(double price);
    public List<Attraction> findAllByStartDateIsGreaterThanEqual(LocalDate startDate);
    public Attraction findByIdEquals(Long id);
    public List<Attraction> findAllByReservedEquals(boolean value);

    @Query("SELECT DISTINCT a.country from Attraction a")
    public List<String> getAllDistinctCountries();


}
