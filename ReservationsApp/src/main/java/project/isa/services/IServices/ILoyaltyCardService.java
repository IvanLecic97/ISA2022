package project.isa.services.IServices;

import java.util.List;
import project.isa.model.LoyaltyCard;
import project.isa.model.users.RegUser;

public interface ILoyaltyCardService {

        Long checkUsersPoints(LoyaltyCard loyaltyCard);
        void configureLoyaltyType(LoyaltyCard loyaltyCard);
        void addPoint(LoyaltyCard loyaltyCard);

        double checkLoyaltyDiscount(LoyaltyCard loyaltyCard);

        Double applyDiscount(LoyaltyCard loyaltyCard, Double price);

        LoyaltyCard findByUserUsername(String username);

}
