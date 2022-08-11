package project.isa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.isa.dto.FishingInstructorOwnerDTO;
import project.isa.model.users.FishingInstructorOwner;

@Mapper
public interface FishingInstructorOwnerMapper {

    FishingInstructorOwnerMapper INSTANCE = Mappers.getMapper(FishingInstructorOwnerMapper.class);

    FishingInstructorOwnerDTO ownerToDto(FishingInstructorOwner fishingInstructorOwner);
    FishingInstructorOwner dtoToOwner(FishingInstructorOwnerDTO fishingInstructorOwnerDTO);
}
