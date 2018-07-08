package com.teamsking.domain.service.announce;

import com.teamsking.domain.entity.announce.Announce;
import com.teamsking.domain.repository.AnnounceMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class AnnounceService {

    @Autowired
    AnnounceMapper announceMapper;

    /**
     * 获取公告列表
     * @return 公告列表
     */
    public List<Announce> list(){

        return announceMapper.selectAll();
    }

    /**
     * 添加公告
     * @param announce
     * @return
     */
    public int save(Announce announce){

        return announceMapper.insert(announce);
    }

    /**
     * 删除公告
     * @param id
     * @return
     */
    public int remove(int id){

        return announceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改公告
     * @param announce
     * @return
     */
    public int modify(Announce announce){

        return announceMapper.updateByPrimaryKeySelective(announce);
    }

}
