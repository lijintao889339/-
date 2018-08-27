package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import java.util.List;

import com.teamsking.domain.entity.open.OpenQuestion;
import lombok.Data;

@Data
public class OpenActivityDto extends Dto {

    private Integer id;

    private Integer type;

    List<OpenVoteQueryDto> openVoteDtoQuerys;

    //List<OpenQuestionQueryDto> openQuestionQUertDtos;

}
