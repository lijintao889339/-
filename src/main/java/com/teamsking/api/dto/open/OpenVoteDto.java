package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import java.util.List;

import com.teamsking.domain.entity.open.OpenVoteOption;
import lombok.Data;


@Data
public class OpenVoteDto extends Dto {

    private Integer id;

    private String title;

    private List<OpenVoteOptionDto> openVoteOptionDtos;
}
