package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class AddOpenPageDto extends Dto {


    private Integer id;

    private Integer openId;

    private Integer chapterId;

    private Integer sectionId;

    private Integer contentId;

    //页面标题
    private String title;

    //页面形式
    private Integer pageType;

    //跳转链接(1链接，2文本)
    private String pageUrl;


}
