package com.teamsking.domain.service.school;

import com.teamsking.domain.entity.school.RecommendCooperation;
import com.teamsking.domain.repository.RecommendCooperationMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class RecommendCooperationService {

    @Autowired
    RecommendCooperationMapper recommendCooperationMapper;

    /**
     * 获取学校-推荐合作课程管理列表
     * @return 学校-推荐合作课程列表
     */
    public List<RecommendCooperation> list(){

        return recommendCooperationMapper.selectAll();
    }

    /**
     * 添加学校-推荐合作课程管理
     * @param recommendCooperation
     * @return
     */
    public int save(RecommendCooperation recommendCooperation){

        return recommendCooperationMapper.insert(recommendCooperation);
    }

    /**
     * 删除学校-推荐合作课程管理
     * @param id
     * @return
     */
    public int remove(int id){

        return recommendCooperationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改学校-推荐合作课程管理
     * @param recommendCooperation
     * @return
     */
    public int modify(RecommendCooperation recommendCooperation){

        return recommendCooperationMapper.updateByPrimaryKeySelective(recommendCooperation);
    }

}
