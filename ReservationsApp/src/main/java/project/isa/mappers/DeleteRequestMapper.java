package project.isa.mappers;



import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;
import project.isa.dto.DeleteRequestDTO;
import project.isa.model.DeleteRequest;

import java.util.List;

@Mapper
public interface DeleteRequestMapper {

    DeleteRequestMapper INSTANCE = Mappers.getMapper(DeleteRequestMapper.class);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    DeleteRequest dtoToRequest(DeleteRequestDTO deleteRequestDTO);

    DeleteRequestDTO requestToDto(DeleteRequest deleteRequest);

    List<DeleteRequestDTO> requestsToDtos(List<DeleteRequest> requestList);

}
