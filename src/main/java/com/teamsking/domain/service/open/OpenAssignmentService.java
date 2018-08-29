package com.teamsking.domain.service.open;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.sun.tools.corba.se.idl.InterfaceGen;
import com.teamsking.api.dto.open.AddOpenAssignmentDto;
import com.teamsking.api.dto.open.OpenAssignmentDto;
import com.teamsking.api.dto.open.OpenAssignmentDtoMapper;
import com.teamsking.api.dto.open.OpenAssignmentNameDto;
import com.teamsking.domain.entity.node.NodeFolder;
import com.teamsking.domain.entity.open.OpenAssignment;
import com.teamsking.domain.entity.open.OpenAssignmentQuiz;
import com.teamsking.domain.entity.open.OpenItem;
import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.entity.sys.StudentAssignmentQuiz;
import com.teamsking.domain.entity.sys.UserStudent;
import com.teamsking.domain.entity.sys.UserStudentAssignment;
import com.teamsking.domain.repository.*;

import java.util.List;

import com.teamsking.domain.service.BaseService;
import com.teamsking.domain.service.sys.UserStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
*@author linhao
*/
@Service
@Slf4j
public class OpenAssignmentService extends BaseService {

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
    @Autowired
    OpenAssignmentQuizMapper openAssignmentQuizMapper;
    @Autowired
    StudentAssignmentQuizMapper studentAssignmentQuizMapper;

    @Autowired
    UserStudentService userStudentService;

    /**
     * 获取作业下的学生提交题信息列表
     * @param pageNo
     * @param pageSize
     * @param id
     * @return
     */
    public Page list(int pageNo, int pageSize, int id ){

        //获取作业信息
        OpenAssignment openAssignment = openAssignmentMapper.selectByPrimaryKey(id);

        //分页
        PageHelper.startPage(pageNo,pageSize);

        //获取已提交该作业学生信息
        UserStudentAssignment userStudentAssignment = new UserStudentAssignment();
        userStudentAssignment.setAssignmentId(id);
        userStudentAssignment.setDeleteStatus(2);
        List<UserStudentAssignment> userStudentAssignmentList = userStudentAssignmentMapper.select(userStudentAssignment);

        if (0 != userStudentAssignmentList.size()){

            List<Integer> userStudentIds = Lists.newArrayList();
            for (UserStudentAssignment studentAssignment : userStudentAssignmentList){
                userStudentIds.add(studentAssignment.getUserStudentId());
            }
            //获取学生信息
            List<UserStudent> userStudentList = userStudentService.getUserStudentListByIds(userStudentIds);

            //获取该作业的应提交提数
            OpenAssignmentQuiz openAssignmentQuiz = new OpenAssignmentQuiz();
            openAssignmentQuiz.setDeleteStatus(2);
            openAssignmentQuiz.setAssignmentId(id);
            Integer quizCount = openAssignmentQuizMapper.selectCount(openAssignmentQuiz);

            //数据转换
            List<OpenAssignmentDto> openAssignmentDtoList = OpenAssignmentDtoMapper.INSTANCE.entityListToDtoList(userStudentAssignmentList);

            //遍历集合
            for (OpenAssignmentDto openAssignmentDto : openAssignmentDtoList){

                //该作业提数和提交截止时间
                openAssignmentDto.setQuizCount(quizCount);
                openAssignmentDto.setEndTime(openAssignment.getEndTime());

                for (UserStudent userStudent : userStudentList){
                    if (openAssignmentDto.getUserStudentId().intValue() == userStudent.getId().intValue()){

                        //获取学生姓名
                        openAssignmentDto.setUserName(userStudent.getUserName());

                        //获取学生已提交提数
                        StudentAssignmentQuiz studentAssignmentQuiz = new StudentAssignmentQuiz();
                        studentAssignmentQuiz.setDeleteStatus(2);
                        studentAssignmentQuiz.setAssignmentId(id);
                        studentAssignmentQuiz.setUserStudentId(userStudent.getId());
                        int commitQuizCount = studentAssignmentQuizMapper.selectCount(studentAssignmentQuiz);
                        openAssignmentDto.setCommitQuizCount(commitQuizCount);


                        //获取学生未提交提数
                        int notCommitQuizCount = quizCount - commitQuizCount;
                        openAssignmentDto.setNotCommitQuizCount(notCommitQuizCount);
                    }
                }

            }
            return convertPage((Page)userStudentAssignmentList, openAssignmentDtoList);

        }else {
            Page page = null;
            return page;
        }
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
    public int addOpenAssignment(AddOpenAssignmentDto addOpenAssignmentDto, Integer openId){

        //向作业表添加信息
        OpenAssignment openAssignmentEntity = OpenAssignmentDtoMapper.INSTANCE.InterDtoEntity(addOpenAssignmentDto);
        openAssignmentEntity.setDeleteStatus(2);
        openAssignmentEntity.setOpenId(openId);
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
