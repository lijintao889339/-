package com.teamsking.domain.entity;

import java.io.Serializable;
import javax.persistence.Id;

/**
 * @author ynfeng
 */
public abstract class Entity implements Serializable {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
