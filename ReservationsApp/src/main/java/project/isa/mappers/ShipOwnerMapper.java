package project.isa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.isa.dto.ShipOwnerDTO;
import project.isa.model.users.ShipOwner;

@Mapper
public interface ShipOwnerMapper {

    ShipOwnerMapper INSTANCE = Mappers.getMapper(ShipOwnerMapper.class);

    ShipOwner dtoToOwner(ShipOwnerDTO shipOwnerDTO);
    ShipOwnerDTO ownerToDto(ShipOwner shipOwner);
}
