package project.isa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.isa.dto.BungalowDTO;
import project.isa.model.entities.Bungalow;

@Mapper
public interface BungalowMapper {

    BungalowMapper INSTANCE = Mappers.getMapper(BungalowMapper.class);

    BungalowDTO bungalowToDto(Bungalow bungalow);
    Bungalow dtoToBungalow(BungalowDTO bungalowDTO);


}
