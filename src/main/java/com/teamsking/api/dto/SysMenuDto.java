package com.teamsking.api.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author ynfeng
 */
@Data
public class SysMenuDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String menuTitle;

    private String menuCode;

    private Integer parentId;

    private Integer menuLevel;

    private Integer displayOrder;

}
