package com.teamsking.domain.service.open;

import com.teamsking.domain.entity.open.OpenCover;
import com.teamsking.domain.repository.OpenCoverMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class OpenCoverService {

    @Autowired
    OpenCoverMapper openCoverMapper;

    /**
     * 获取班次封面列表
     * @return
     */
    public List<OpenCover> list(){
        return openCoverMapper.selectAll();
    }

    /**
     * 添加班次封面
     * @param openCover
     * @return
     */
    public int save(OpenCover openCover){

        return openCoverMapper.insert(openCover);
    }

    /**
     * 删除班次封面
     * @param id
     * @return
     */
    public int remove(int id){

        return openCoverMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改班次封面
     * @param openCover
     * @return
     */
    public int modify(OpenCover openCover){

        return openCoverMapper.updateByPrimaryKeySelective(openCover);
    }
}
