package com.teamsking.domain.service.school;

import com.teamsking.domain.entity.school.School;
import com.teamsking.domain.repository.SchoolMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class SchoolService {

    @Autowired
    SchoolMapper schoolMapper;

    /**
     *学校列表
     * @return
     */
    public List<School> list(){

        return schoolMapper.selectAll();
    }

    /**
     *添加学校
     * @param school
     * @return
     */
    public int save(School school){

        return schoolMapper.insert(school);
    }

    /**
     *删除学校
     * @param id
     * @return
     */
    public int remove(int id){

        return schoolMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改学校
     * @param school
     * @return
     */
    public int modify(School school){

        return schoolMapper.updateByPrimaryKeySelective(school);
    }

}
