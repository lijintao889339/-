package com.teamsking.domain.service.open;


import com.teamsking.domain.entity.open.OpenForum;
import com.teamsking.domain.repository.OpenForumMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenForumService {

    @Autowired
    OpenForumMapper openForumMapper;

    /**
     * 获取班次-讨论管理列表
     * @return
     */
    public List<OpenForum> list(){

        return openForumMapper.selectAll();

    }

    /**
     * 添加班次-讨论管理
     * @param openForum
     * @return
     */
    public int save(OpenForum openForum){

        return openForumMapper.insert(openForum);

    }


    /**
     * 删除班次-讨论管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openForumMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改班次-讨论管理
     * @param openForum
     * @return
     */
    public int modify(OpenForum openForum){

        return openForumMapper.updateByPrimaryKeySelective(openForum);

    }

}
