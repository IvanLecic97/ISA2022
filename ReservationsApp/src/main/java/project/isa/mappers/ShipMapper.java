package project.isa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.isa.dto.ShipDTO;
import project.isa.model.entities.Ship;

@Mapper
public interface ShipMapper {

    ShipMapper INSTANCE = Mappers.getMapper(ShipMapper.class);

    Ship dtoToShip(ShipDTO shipDTO);
    ShipDTO shipToDto(Ship ship);
}
