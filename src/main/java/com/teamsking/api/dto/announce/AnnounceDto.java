package com.teamsking.api.dto.announce;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class AnnounceDto extends Dto {

    private Integer id;

    private String title;

    private Integer createId;

    private Date createTime;

    private String content;

    private String userName;

    private Integer readUserNums;

    private Integer allUserNums;
}
