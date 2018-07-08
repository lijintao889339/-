package com.teamsking.domain.service.school;

import com.teamsking.domain.entity.school.SchoolAbout;
import com.teamsking.domain.repository.SchoolAboutMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class SchoolAboutService {

    @Autowired
    SchoolAboutMapper schoolAboutMapper;

    /**
     * 获取学校-"关于我们"列表
     * @return
     */
    public List<SchoolAbout> list(){

        return schoolAboutMapper.selectAll();
    }

    /**
     * 添加学校-"关于我们"
     * @param schoolAbout
     * @return
     */
    public int save(SchoolAbout schoolAbout){

        return schoolAboutMapper.insert(schoolAbout);
    }

    /**
     * 删除学校-"关于我们"
     * @param id
     * @return
     */
    public int remove(int id){

        return schoolAboutMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改学校-"关于我们"
     * @param schoolAbout
     * @return
     */
    public int modify(SchoolAbout schoolAbout){

        return schoolAboutMapper.updateByPrimaryKeySelective(schoolAbout);
    }

}
