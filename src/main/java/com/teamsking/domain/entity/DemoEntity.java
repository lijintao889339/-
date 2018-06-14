package com.teamsking.domain.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**
 * @author ynfeng
 */
@NameStyle(Style.camelhumpAndLowercase)
@Table(name = "t_demo")
@Data
public class DemoEntity extends Entity {

    @Column
    private String entityName;

}
