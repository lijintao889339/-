package com.teamsking.domain.service.open;


import com.teamsking.domain.entity.open.OpenActivity;
import com.teamsking.domain.repository.OpenActivityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OpenActivityService {


    @Autowired
    OpenActivityMapper openActivityMapper;

    /**
     * 获取班次-活动列表
     *
     * @return
     */
    public List<OpenActivity> list(){
        return openActivityMapper.selectAll();
    }

    /**
     * 添加班次-活动
     * @param openActivity
     * @return
     */
    public int save(OpenActivity openActivity){

        return openActivityMapper.insert(openActivity);

    }

    /**
     * 删除班次-活动
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openActivityMapper.deleteByPrimaryKey(id);

    }

    /**
     * 修改班次-活动
     * @param openActivity
     * @return
     */
    public int modify(OpenActivity openActivity){

        return openActivityMapper.updateByPrimaryKeySelective(openActivity);

    }

}
