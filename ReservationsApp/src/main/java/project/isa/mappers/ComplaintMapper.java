package project.isa.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;
import project.isa.dto.ComplaintDTO;
import project.isa.model.Complaint;

import java.util.List;

@Mapper
public interface ComplaintMapper {

    ComplaintMapper INSTANCE = Mappers.getMapper(ComplaintMapper.class);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    ComplaintDTO complaintToDto(Complaint complaint);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<ComplaintDTO> complaintsToDtos(List<Complaint> complaints);

}
