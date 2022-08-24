package project.isa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.isa.dto.ComplaintDTO;
import project.isa.model.Complaint;

@Mapper
public interface ComplaintMapper {

    ComplaintMapper INSTANCE = Mappers.getMapper(ComplaintMapper.class);

    ComplaintDTO complaintToDto(Complaint complaint);
}
