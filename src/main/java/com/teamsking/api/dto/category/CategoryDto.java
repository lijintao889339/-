package com.teamsking.api.dto.category;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class CategoryDto extends Dto {

    private Integer id;
    private String categoryName;
    private String shortName;
    private Integer parentId;
    private String description;
    private Integer displayOrder;
    private Integer schoolId;
    private String status;
    private Integer createId;
    private Date createdTime;
    private Date updatedTime;
    private Integer deleteStatus;
    private String imageName;
    private String imageUrl;
    private String adDesc;


}
