package project.isa.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.isa.Categories;
import project.isa.model.LoyaltyCard;
import project.isa.model.users.RegUser;
import project.isa.repository.LoyaltyCardRepository;
import project.isa.repository.RegUserRepository;
import project.isa.services.IServices.ILoyaltyCardService;


@Service
@AllArgsConstructor
public class LoyaltyCardService implements ILoyaltyCardService {

    private LoyaltyCardRepository loyaltyCardRepository;

    private RegUserRepository regUserRepository;

    @Override
    public Long checkUsersPoints(LoyaltyCard loyaltyCard) {
        return loyaltyCard.getPoints();
    }

    @Override
    public LoyaltyCard findByUserUsername(String username) {
        return loyaltyCardRepository.findByUserUsername(username);
    }

    @Override
    public void configureLoyaltyType(LoyaltyCard loyaltyCard) {
        if(loyaltyCard.getPoints() < 5){
            loyaltyCard.setCategory(Categories.REGULAR);
        } else if((loyaltyCard.getPoints() >= 5) && (loyaltyCard.getPoints() < 11)) {
            loyaltyCard.setCategory(Categories.SILVER);
        } else if((loyaltyCard.getPoints() >= 11) && (loyaltyCard.getPoints() < 25)){
            loyaltyCard.setCategory(Categories.GOLD);
        } else if(loyaltyCard.getPoints() >= 25) {
            loyaltyCard.setCategory(Categories.PLATINUM);
        }

    }

    @Override
    public void addPoint(LoyaltyCard loyaltyCard) {
        Long points = loyaltyCard.getPoints();
        loyaltyCard.setPoints(points+1);
        configureLoyaltyType(loyaltyCard);
        loyaltyCardRepository.save(loyaltyCard);
    }


    @Override
    public double checkLoyaltyDiscount(LoyaltyCard loyaltyCard) {
        double retVal = 0.0;
        switch (loyaltyCard.getCategory()) {
            case Categories.REGULAR:
                retVal = 0.0;
                break;
            case Categories.SILVER:
                retVal = 7.5;
                break;
            case Categories.GOLD:
                retVal = 15.0;
                break;
            case Categories.PLATINUM:
                retVal = 25.0;
                break;
        }
        return retVal;
    }

    @Override
    public Double applyDiscount(LoyaltyCard loyaltyCard, Double price) {
        double discount = checkLoyaltyDiscount(loyaltyCard);
        return (price - (price * (discount/100)));
    }
}
