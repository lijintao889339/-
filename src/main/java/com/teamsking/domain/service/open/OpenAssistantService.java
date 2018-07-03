package com.teamsking.domain.service.open;


import com.teamsking.domain.entity.open.OpenAssistant;
import com.teamsking.domain.repository.OpenAssistantMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OpenAssistantService {

    @Autowired
    OpenAssistantMapper openAssistantMapper;

    /**
     * 获取班次-助教权限管理列表
     *
     * @return
     */
    public List<OpenAssistant> list(){

        return openAssistantMapper.selectAll();

    }

    /**
     * 添加班次-助教权限管理
     * @param openAssistant
     * @return
     */
    public int save(OpenAssistant openAssistant){

        return openAssistantMapper.insert(openAssistant);

    }

    /**
     * 删除班次-助教权限管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openAssistantMapper.deleteByPrimaryKey(id);

    }

    /**
     * 修改班次-助教权限管理
     * @param openAssistant
     * @return
     */
    public int modify(OpenAssistant openAssistant){

        return openAssistantMapper.updateByPrimaryKeySelective(openAssistant);

    }


}
