package com.teamsking.api.dto.node;

import com.teamsking.domain.entity.node.NodeFolder;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NodeFolderDtoMapper {

    NodeFolderDtoMapper INSTANCE = Mappers.getMapper(NodeFolderDtoMapper.class);

    List<NodeFolderDto> entityListToDtoList(List<NodeFolder> entityList);

    NodeFolderDto entityToDto(NodeFolder nodeFolder);

    NodeFolder dtoToEntity(NodeFolderDto nodeFolderDto);

}
