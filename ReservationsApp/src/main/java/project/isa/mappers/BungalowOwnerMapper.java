package project.isa.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.isa.dto.BungalowOwnerDTO;
import project.isa.model.users.BungalowOwner;

@Mapper
public interface BungalowOwnerMapper {

    BungalowOwnerMapper INSTANCE = Mappers.getMapper(BungalowOwnerMapper.class);

    BungalowOwner dtoToOwner(BungalowOwnerDTO bungalowOwnerDTO);
    BungalowOwnerDTO ownerToDto(BungalowOwner bungalowOwner);

}
