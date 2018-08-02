package com.teamsking.domain.service.open;

import com.teamsking.domain.entity.open.OpenAssignment;
import com.teamsking.domain.entity.open.OpenAssistant;
import com.teamsking.domain.repository.OpenAssignmentMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class OpenAssignmentService {

    @Autowired
    OpenAssignmentMapper openAssignmentMapper;

    /**
     * 获取班次作业管理列表
     * @return
     */
    public List<OpenAssignment> list( ){

        return openAssignmentMapper.selectAll();
    }

    /**
     * 根据班课id查询作业信息
     * @param openId
     * @return
     */
    public List<OpenAssignment> query(Integer openId){

        OpenAssignment openAssignment = new OpenAssignment();
        openAssignment.setOpenId(openId);
        openAssignment.setDeleteStatus(2);
        return openAssignmentMapper.select(openAssignment);



    }

    /**
     *添加班管理次作业
     * @param openAssignment
     * @return
     */
    public int save(OpenAssignment openAssignment){

        return openAssignmentMapper.insert(openAssignment);
    }

    /**
     *删除班管理次作业
     * @param id
     * @return
     */
    public int remove(int id){

        return openAssignmentMapper.deleteByPrimaryKey(id);
    }

    /**
     *修改班管理次作业
     * @param openAssignment
     * @return
     */
    public int modify(OpenAssignment openAssignment){

        return openAssignmentMapper.updateByPrimaryKeySelective(openAssignment);
    }

}
