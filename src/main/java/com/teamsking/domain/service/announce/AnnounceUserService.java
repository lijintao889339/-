package com.teamsking.domain.service.announce;

import com.teamsking.domain.entity.announce.AnnounceUser;
import com.teamsking.domain.repository.AnnounceUserMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class AnnounceUserService {

    @Autowired
    AnnounceUserMapper announceUserMapper;

    /**
     * 获取公告阅读记录列表
     * @return 公告阅读记录列表
     */
    public List<AnnounceUser> list(){

        return announceUserMapper.selectAll();
    }

    /**
     * 添加公告阅读记录
     * @param announceUser
     * @return
     */
    public int save(AnnounceUser announceUser){

        return announceUserMapper.insert(announceUser);
    }

    /**
     * 删除公告阅读记录
     * @param id
     * @return
     */
    public int remove(int id){

        return announceUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改公告阅读记录
     * @param announceUser
     * @return
     */
    public int modify(AnnounceUser announceUser){

        return announceUserMapper.updateByPrimaryKeySelective(announceUser);
    }

}
