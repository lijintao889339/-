package com.teamsking.api.dto.sys;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
 * @author ynfeng
 */
@Data
public class SysMenuDto extends Dto {

    private String menuTitle;

    private String menuCode;

    private Integer parentId;

    private Integer menuLevel;

    private Integer displayOrder;

}
