package com.teamsking.domain.service.school;

import com.teamsking.domain.entity.school.RecommendTeacher;
import com.teamsking.domain.repository.RecommendTeacherMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class RecommendTeacherService {

    @Autowired
    RecommendTeacherMapper recommendTeacherMapper;

    /**
     * 获取学校-推荐老师管理列表
     * @return 学校-推荐老师列表
     */
    public List<RecommendTeacher> list(){

        return recommendTeacherMapper.selectAll();
    }

    /**
     * 添加学校-推荐老师管理
     * @param recommendTeacher
     * @return
     */
    public int save(RecommendTeacher recommendTeacher){

        return recommendTeacherMapper.insert(recommendTeacher);
    }

    /**
     * 删除学校-推荐老师管理
     * @param id
     * @return
     */
    public int remove(int id){

        return recommendTeacherMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改学校-推荐老师管理
     * @param recommendTeacher
     * @return
     */
    public int modify(RecommendTeacher recommendTeacher){

        return recommendTeacherMapper.updateByPrimaryKeySelective(recommendTeacher);
    }

}
