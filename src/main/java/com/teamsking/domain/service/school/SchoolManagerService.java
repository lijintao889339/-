package com.teamsking.domain.service.school;

import com.teamsking.domain.entity.school.SchoolManager;
import com.teamsking.domain.repository.SchoolManagerMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SchoolManagerService {

    @Autowired
    SchoolManagerMapper schoolManagerMapper;

    /**
     * 获取学校-管理员管理列表
     * @return
     */
    public List<SchoolManager> list(){

        return schoolManagerMapper.selectAll();
    }

    /**
     * 添加学校-管理员
     * @param schoolManager
     * @return
     */
    public int save(SchoolManager schoolManager){

        return schoolManagerMapper.insert(schoolManager);
    }

    /**
     * 删除学校-管理员
     * @param id
     * @return
     */
    public int remove(int id){

        return schoolManagerMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改学校-管理员
     * @param schoolManager
     * @return
     */
    public int modify(SchoolManager schoolManager){

        return schoolManagerMapper.updateByPrimaryKeySelective(schoolManager);
    }

}
