package com.teamsking.domain.service.tag;


import com.teamsking.domain.entity.tag.Tag;
import com.teamsking.domain.repository.TagMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TagService {

    @Autowired
    TagMapper tagMapper;

    /**
     * 获取标签管理列表
     * @return
     */
    public List<Tag> list(){

        return tagMapper.selectAll();

    }

    /**
     * 添加标签管理
     * @param tag
     * @return
     */
    public int save(Tag tag){

        return tagMapper.insert(tag);

    }

    /**
     * 删除标签管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return tagMapper.deleteByPrimaryKey(id);

    }

    /**
     * 修改标签管理
     * @param tag
     * @return
     */
    public int modify(Tag tag){

        return tagMapper.updateByPrimaryKeySelective(tag);

    }

}
