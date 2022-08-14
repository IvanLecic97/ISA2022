package project.isa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.isa.dto.AttractionDTO;
import project.isa.dto.DiscountedEntityDTO;
import project.isa.model.entities.Attraction;
import project.isa.model.entities.DiscountedEntity;
import project.isa.repository.DiscountedEntityRepository;
import project.isa.repository.RegUserRepository;
import project.isa.services.IServices.IDiscountedEntityService;

import java.util.ArrayList;
import java.util.List;


@Service
public class DiscountedEntityService implements IDiscountedEntityService {

    @Autowired
    private DiscountedEntityRepository discountedEntityRepository;

    @Autowired
    private AttractionService attractionService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private RegUserRepository regUserRepository;

    @Override
    public void setEntityOnDiscount(DiscountedEntityDTO discountedEntityDTO) {
        DiscountedEntity discountedEntity = new DiscountedEntity();


        discountedEntity.setAttractionId(discountedEntityDTO.getAttractionId());
        discountedEntity.setOldPrice(discountedEntityDTO.getOldPrice());
        discountedEntity.setNewPrice(discountedEntityDTO.getNewPrice());

        discountedEntityRepository.save(discountedEntity);

        //emailSenderService.sendSimpleEmail(discountedEntityDTO.getUsername(), "Made a reservation on discounted entity", "Discounted reservation");

    }

    @Override
    public List<DiscountedEntityDTO> getAllDiscountedEntities() {
        List<DiscountedEntity> discountedEntities = discountedEntityRepository.findAll();
        List<DiscountedEntityDTO> retList = new ArrayList<>();
        for(DiscountedEntity d : discountedEntities){
            Attraction attraction = attractionService.getById(d.getAttractionId());
            DiscountedEntityDTO discountedEntityDTO = new DiscountedEntityDTO();
            discountedEntityDTO.setOldPrice(d.getOldPrice());
            discountedEntityDTO.setNewPrice(d.getNewPrice());
            discountedEntityDTO.setUsername(attraction.getOwnerUsername());
            discountedEntityDTO.setAttractionName(attraction.getName());
            discountedEntityDTO.setAttractionId(d.getAttractionId());
            retList.add(discountedEntityDTO);
        }

        return retList;
    }
}
