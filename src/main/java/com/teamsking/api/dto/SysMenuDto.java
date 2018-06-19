package com.teamsking.api.dto;

import lombok.Data;

/**
 * @author ynfeng
 */
@Data
public class SysMenuDto extends Dto {
    private Integer id;

    private String menuTitle;

    private String menuCode;

    private Integer parentId;

    private Integer menuLevel;

    private Integer displayOrder;
}
