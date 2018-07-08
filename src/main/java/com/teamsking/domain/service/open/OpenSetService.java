package com.teamsking.domain.service.open;


import com.teamsking.domain.entity.open.OpenSet;
import com.teamsking.domain.repository.OpenSetMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenSetService {


    @Autowired
    OpenSetMapper openSetMapper;


    /**
     * 获取班次-成绩管理列表
     * @return
     */
    public List<OpenSet> list(){

        return openSetMapper.selectAll();

    }


    /**
     * 添加班次-成绩管理
     * @param openSet
     * @return
     */
    public int save(OpenSet openSet){

        return openSetMapper.insert(openSet);

    }


    /**
     * 删除班次-成绩管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openSetMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改班次-成绩管理
     * @param openSet
     * @return
     */
    public int modify(OpenSet openSet){

        return openSetMapper.updateByPrimaryKeySelective(openSet);

    }

}
