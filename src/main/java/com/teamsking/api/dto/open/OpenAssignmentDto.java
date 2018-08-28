package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class OpenAssignmentDto extends Dto {

    private Integer userStudentId;

    private String userName;

    private Date endTime;

    private Integer quizCount;

    private Integer commitQuizCount;

    private Integer notCommitQuizCount;

}
