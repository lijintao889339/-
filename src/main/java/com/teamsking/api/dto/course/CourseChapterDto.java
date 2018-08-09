package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class CourseChapterDto extends Dto {

    private Integer id;

    private String chapterName;

}
