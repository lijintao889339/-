package com.teamsking.domain.service.open;


import com.teamsking.domain.entity.open.OpenExplain;
import com.teamsking.domain.repository.OpenExplainMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OpenExplainService {

    @Autowired
    OpenExplainMapper openExplainMapper;

    /**
     * 获取班次-说明管理列表
     * @return
     */
    public List<OpenExplain> list(){

        return openExplainMapper.selectAll();
    }

    /**
     * 添加班次-说明管理
     * @param openExplain
     * @return
     */
    public int save(OpenExplain openExplain){

        return openExplainMapper.insert(openExplain);

    }


    /**
     * 删除班次-说明管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openExplainMapper.deleteByPrimaryKey(id);

    }

    /**
     * 修改班次-说明管理
     * @param openExplain
     * @return
     */
    public int modify(OpenExplain openExplain){

        return openExplainMapper.updateByPrimaryKeySelective(openExplain);

    }









}
