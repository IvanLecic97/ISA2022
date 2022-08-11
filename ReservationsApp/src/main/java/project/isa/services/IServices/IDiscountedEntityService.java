package project.isa.services.IServices;

import project.isa.dto.AttractionDTO;
import project.isa.dto.DiscountedEntityDTO;
import project.isa.model.entities.DiscountedEntity;

import java.util.List;

public interface IDiscountedEntityService {

    public void setEntityOnDiscount(DiscountedEntityDTO discountedEntityDTO);
    public List<DiscountedEntityDTO> getAllDiscountedEntities();
}
