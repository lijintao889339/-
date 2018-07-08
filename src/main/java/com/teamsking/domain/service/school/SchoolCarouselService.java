package com.teamsking.domain.service.school;

import com.teamsking.domain.entity.school.SchoolCarousel;
import com.teamsking.domain.repository.SchoolCarouselMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class SchoolCarouselService {

    @Autowired
    SchoolCarouselMapper schoolCarouselMapper;

    /**
     * 获取学校-轮播图管理列表
     * @return
     */
    public List<SchoolCarousel> list(){

        return schoolCarouselMapper.selectAll();
    }

    /**
     *添加学校-轮播图管理
     * @param schoolCarousel
     * @return
     */
    public int save(SchoolCarousel schoolCarousel){

        return schoolCarouselMapper.insert(schoolCarousel);
    }

    /**
     *删除学校-轮播图管理
     * @param id
     * @return
     */
    public int remove(int id){

        return schoolCarouselMapper.deleteByPrimaryKey(id);
    }

    /**
     *修改学校-轮播图管理
     * @param schoolCarousel
     * @return
     */
    public int modify(SchoolCarousel schoolCarousel){

        return schoolCarouselMapper.updateByPrimaryKeySelective(schoolCarousel);
    }
}
