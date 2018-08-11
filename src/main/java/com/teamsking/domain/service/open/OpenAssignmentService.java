package com.teamsking.domain.service.open;

import com.teamsking.api.dto.open.AddOpenAssignmentDto;
import com.teamsking.api.dto.open.OpenAssignmentDtoMapper;
import com.teamsking.domain.entity.open.OpenAssignment;
import com.teamsking.domain.entity.open.OpenAssistant;
import com.teamsking.domain.entity.open.OpenItem;
import com.teamsking.domain.repository.OpenAssignmentMapper;
import java.util.List;

import com.teamsking.domain.repository.OpenItemMapper;
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
    @Autowired
    OpenItemMapper openItemMapper;

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


    /**
     * 根据班课id添加作业信息
     * @param addOpenAssignmentDto
     * @return
     */
    public int addOpenAssignment(AddOpenAssignmentDto addOpenAssignmentDto){

        //向作业表添加信息
        OpenAssignment openAssignmentEntity = OpenAssignmentDtoMapper.INSTANCE.InterDtoEntity(addOpenAssignmentDto);
        openAssignmentEntity.setDeleteStatus(2);
        openAssignmentMapper.insertSelective(openAssignmentEntity);

        OpenItem openItem = new OpenItem();
        openItem.setChapterId(openAssignmentEntity.getChapterId());
        openItem.setSectionId(openAssignmentEntity.getSectionId());
        openItem.setOpenId(openAssignmentEntity.getOpenId());
        openItem.setContentId(openAssignmentEntity.getId());
        //openItemMapper.insertSelective(openItem);

        return openItemMapper.insertSelective(openItem);

    }

}
