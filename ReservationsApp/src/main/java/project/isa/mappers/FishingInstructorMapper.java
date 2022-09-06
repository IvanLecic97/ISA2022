package project.isa.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;
import project.isa.dto.FishingInstructorDTO;
import project.isa.model.entities.FishingInstructor;

@Mapper
public interface FishingInstructorMapper {

    FishingInstructorMapper INSTANCE = Mappers.getMapper(FishingInstructorMapper.class);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    FishingInstructor dtoToInstructor(FishingInstructorDTO fishingInstructorDTO);
    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    FishingInstructorDTO instructorToDto(FishingInstructor fishingInstructor);
}
