package com.teamsking.domain.service.open;


import com.teamsking.domain.entity.open.Open;
import com.teamsking.domain.repository.OpenMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OpenService {

    @Autowired
    OpenMapper openMapper;

    /**
     * 获取班次管理列表
     * @param open
     * @return
     */
    public List<Open> list(Open open){

        return openMapper.select(open);

    }

    /**
     * 添加班次管理
     * @param open
     * @return
     */
    public int save(Open open){
        return openMapper.insert(open);
    }

    /**
     * 删除班次管理
     * @param open
     * @return
     */
    public int remove(Open open){
        return openMapper.delete(open);
    }

    /**
     *  修改班次管理
     * @param open
     * @return
     */
    public int modify(Open open){
        return openMapper.updateByPrimaryKeySelective(open);
    }

}
