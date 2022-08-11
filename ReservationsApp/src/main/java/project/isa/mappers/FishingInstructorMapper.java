package project.isa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.isa.dto.FishingInstructorDTO;
import project.isa.model.entities.FishingInstructor;

@Mapper
public interface FishingInstructorMapper {

    FishingInstructorMapper INSTANCE = Mappers.getMapper(FishingInstructorMapper.class);

    FishingInstructor dtoToInstructor(FishingInstructorDTO fishingInstructorDTO);
    FishingInstructorDTO instructorToDto(FishingInstructor fishingInstructor);
}
