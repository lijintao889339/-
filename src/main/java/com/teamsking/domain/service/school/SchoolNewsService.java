package com.teamsking.domain.service.school;

import com.teamsking.domain.entity.school.SchoolNews;
import com.teamsking.domain.repository.SchoolNewsMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class SchoolNewsService {

    @Autowired
    SchoolNewsMapper schoolNewsMapper;

    /**
     * 获取学校-新闻管理列表
     * @return
     */
    public List<SchoolNews> list(){

        return schoolNewsMapper.selectAll();
    }

    /**
     * 添加学校-新闻管理
     * @param schoolNews
     * @return
     */
    public int save(SchoolNews schoolNews){

        return schoolNewsMapper.insert(schoolNews);
    }

    /**
     * 删除学校-新闻管理
     * @param id
     * @return
     */
    public int remove(int id){

        return schoolNewsMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改学校-新闻管理
     * @param schoolNews
     * @return
     */
    public int modify(SchoolNews schoolNews){

        return schoolNewsMapper.updateByPrimaryKeySelective(schoolNews);
    }

}
