package com.teamsking.domain.service.open;

import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.AddOpenAssignmentDto;
import com.teamsking.api.dto.open.OpenAssignmentDtoMapper;
import com.teamsking.api.dto.open.OpenAssignmentNameDto;
import com.teamsking.domain.entity.node.NodeFolder;
import com.teamsking.domain.entity.open.OpenAssignment;
import com.teamsking.domain.entity.open.OpenItem;
import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.entity.sys.UserStudent;
import com.teamsking.domain.entity.sys.UserStudentAssignment;
import com.teamsking.domain.repository.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    @Autowired
    NodeFolderMapper nodeFolderMapper;
    @Autowired
    OpenUserMapper openUserMapper;
    @Autowired
    UserStudentAssignmentMapper userStudentAssignmentMapper;

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
        openItem.setDeleteStatus(2);

        return openItemMapper.insertSelective(openItem);

    }


    /**
     * 根据节id添加作业信息
     * @param addOpenAssignmentDto
     * @param sectionId
     * @return
     */
    public int addOpenAssignmentBySectionId(AddOpenAssignmentDto addOpenAssignmentDto,Integer sectionId){

        //给作业表添加信息
        OpenAssignment openAssignmentEntity = OpenAssignmentDtoMapper.INSTANCE.InterDtoEntity(addOpenAssignmentDto);
        openAssignmentEntity.setDeleteStatus(2);
        openAssignmentMapper.insertSelective(openAssignmentEntity);

        //给项表中添加信息
        OpenItem openItem = new OpenItem();
        openItem.setChapterId(openAssignmentEntity.getChapterId());
        openItem.setSectionId(sectionId);
        openItem.setOpenId(openAssignmentEntity.getOpenId());
        openItem.setContentId(openAssignmentEntity.getId());
        openItem.setDeleteStatus(2);

        return openItemMapper.insertSelective(openItem);

    }




    /**
     * 根据openId获取作业内容信息(教学管理作业功能)
     * @param openId
     * @return
     */
    public List<OpenAssignmentNameDto> getOpenAssignmentListByOpenId(Integer openId){

        OpenAssignment openAssignment = new OpenAssignment();
        openAssignment.setOpenId(openId);
        openAssignment.setDeleteStatus(2);
        List<OpenAssignment> openAssignmentList = openAssignmentMapper.select(openAssignment);

        //遍历集合
//        List<Integer> userStudentIds = Lists.newArrayList();
//        for (OpenAssignment openAssignment1 : openAssignmentList){
//            userStudentIds.add(openAssignment1.getCreateId());
//
//        }


        //获取该班课下学生数量
        OpenUser openUser = new OpenUser();
        openUser.setDeleteStatus(2);
        openUser.setOpenId(openId);
        int allUserNum = openUserMapper.selectCount(openUser);


        //转换数据
        List<OpenAssignmentNameDto> openAssignmentDtoList = OpenAssignmentDtoMapper.INSTANCE.entityListToNameDtoList(openAssignmentList);

        for (OpenAssignmentNameDto openAssignmentDto : openAssignmentDtoList){


            //班课作业的已提交人数
            UserStudentAssignment userStudentAssignment = new UserStudentAssignment();
            userStudentAssignment.setAssignmentId(openAssignmentDto.getId());
            userStudentAssignment.setDeleteStatus(2);
            int stopUserCount = userStudentAssignmentMapper.selectCount(userStudentAssignment);
            openAssignmentDto.setStopUserCount(stopUserCount);
            //总人数
            openAssignmentDto.setUserCount(allUserNum);
            //未提交人数
            int notUserCount = allUserNum - stopUserCount;

            openAssignmentDto.setNotUserCount(notUserCount);

        }


        return openAssignmentDtoList;

    }


}
