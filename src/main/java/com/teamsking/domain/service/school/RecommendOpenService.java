package com.teamsking.domain.service.school;

import com.teamsking.domain.entity.school.RecommendOpen;
import com.teamsking.domain.repository.RecommendOpenMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class RecommendOpenService {

    @Autowired
    RecommendOpenMapper recommendOpenMapper;

    /**
     * 获取学校-推荐课程管理列表
     * @return 学校-推荐课程列表
     */
    public List<RecommendOpen> list(){

        return recommendOpenMapper.selectAll();
    }

    /**
     * 添加学校-推荐课程管理
     * @param recommendOpen
     * @return
     */
    public int save(RecommendOpen recommendOpen){

        return recommendOpenMapper.insert(recommendOpen);
    }

    /**
     * 删除学校-推荐课程管理
     * @param id
     * @return
     */
    public int remove(int id){

        return recommendOpenMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改学校-推荐课程管理
     * @param recommendOpen
     * @return
     */
    public int modify(RecommendOpen recommendOpen){

        return recommendOpenMapper.updateByPrimaryKeySelective(recommendOpen);
    }

}
