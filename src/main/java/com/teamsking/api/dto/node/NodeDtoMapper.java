package com.teamsking.api.dto.node;

import com.teamsking.domain.entity.node.Node;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NodeDtoMapper {

    NodeDtoMapper INSTANCE = Mappers.getMapper(NodeDtoMapper.class);

    List<NodeDto> entityListToDtoList(List<Node> entityList);

    NodeDto entityToDto(Node node);

    Node dtoToEntity(NodeDto nodeDto);

    Node dotToEntityVideo(NodeVideoDto nodeVideoDto);

    List<NodeVideoDto> entityVideoListToDto(List<Node> nodeList);

}
