package com.teamsking.domain.service.open;

import com.teamsking.domain.entity.open.OpenAssignment;
import com.teamsking.domain.repository.OpenAssignmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @param openAssignment
     * @return
     */
    public List<OpenAssignment> list(OpenAssignment openAssignment){

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
     * @param openAssignment
     * @return
     */
    public int remove(OpenAssignment openAssignment){

        return openAssignmentMapper.delete(openAssignment);
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
