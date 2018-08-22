package com.teamsking.domain.service.study;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.study.StudyScoreDto;
import com.teamsking.api.dto.study.StudyScoreDtoMapper;
import com.teamsking.domain.entity.study.StudyScore;
import com.teamsking.domain.entity.sys.SysUser;
import com.teamsking.domain.entity.sys.UserStudent;
import com.teamsking.domain.repository.StudyScoreMapper;
import java.util.List;

import com.teamsking.domain.service.BaseService;
import com.teamsking.domain.service.sys.SysUserService;
import com.teamsking.domain.service.sys.UserStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Slf4j
@Service
public class StudyScoreService extends BaseService {

    @Autowired
    StudyScoreMapper studyScoreMapper;

    @Autowired
    UserStudentService userStudentService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    StudyScoreService studyScoreService;

    /**
     * 获取学习-成绩列表
     * @param pageNo
     * @param pageSize
     * @param openId
     * @return
     */
    public Page list(int pageNo, int pageSize, int openId){

        //分页
        PageHelper.startPage(pageNo,pageSize);

        //获取班课下的成绩表
        StudyScore studyScore = new StudyScore();
        studyScore.setOpenId(openId);
        studyScore.setDeleteStatus(2);
        List<StudyScore> studyScoreList = studyScoreMapper.select(studyScore);

        if (0 != studyScoreList.size()){

            //获取学生信息
            List<Integer> userStudentIds = Lists.newArrayList();
            for (StudyScore score : studyScoreList){
                userStudentIds.add(score.getUserStudentId());
            }
            List<UserStudent> userStudentList = userStudentService.getUserStudentListByIds(userStudentIds);

            //通过学生信息列表获取用户信息
            List<Integer> userIds = Lists.newArrayList();
            for (UserStudent userStudent : userStudentList){
                userIds.add(userStudent.getUserId());
            }
            List<SysUser> sysUserList = sysUserService.getSysUserByUserIdList(userIds);

            //数据转换
            List<StudyScoreDto> studyScoreDtoList = StudyScoreDtoMapper.INSTANCE.entityListToDtoList(studyScoreList);

            for (StudyScoreDto studyScoreDto : studyScoreDtoList){

                //获取学生姓名和认证状态
                for (UserStudent student : userStudentList){
                    if (studyScoreDto.getUserStudentId().intValue() == student.getId().intValue()){
                    for (SysUser sysUser : sysUserList){
                        if (student.getUserId().intValue() == sysUser.getId().intValue()){
                            studyScoreDto.setActivationStatus(sysUser.getActivationStatus());
                            studyScoreDto.setUserName(sysUser.getUserName());
                        }
                    }
                    }
                }
            }

            return convertPage((Page)studyScoreList,studyScoreDtoList);

        }else {
            Page page = null;
            return page;
        }
    }


    /**
     * 模糊搜索学习成绩列表
     * @param pageNo
     * @param pageSize
     * @param openId
     * @param userName
     * @return
     */
    public Page listBySearching(int pageNo, int pageSize, int openId, String userName){

        List<Integer> studentIds = Lists.newArrayList();

        if ("" != userName){
            //查询搜索的学生名称的ids
            List<Integer> sysUserIds = Lists.newArrayList();
            List<SysUser> sysUserList = sysUserService.getSysUserByLikeName(userName);
            if (0 != sysUserList.size()){
                for (SysUser sysUser : sysUserList){
                    sysUserIds.add(sysUser.getId());
                }

                //获取学生Ids
                List<UserStudent> userStudentList = userStudentService.getUserStudentListByUserIds(sysUserIds);
                for (UserStudent userStudent : userStudentList){
                    studentIds.add(userStudent.getId());
                }
            }
        }else {

            return studyScoreService.list(pageNo,pageSize,openId);
        }

        //分页
        PageHelper.startPage(pageNo,pageSize);

        //获取班课下的成绩表

        Example studyScoreExample = new Example(StudyScore.class);
        Example.Criteria cri = studyScoreExample.createCriteria();
        cri.andEqualTo("openId",openId);
        cri.andEqualTo("deleteStatus",2);

        if (0 != studentIds.size() ) {
            cri.andIn("userStudentId", studentIds);

            List<StudyScore> studyScoreList = studyScoreMapper.selectByExample(studyScoreExample);

            if (0 != studyScoreList.size() ) {

                //获取学生信息
                List<Integer> userStudentIds = Lists.newArrayList();
                for (StudyScore score : studyScoreList) {
                    userStudentIds.add(score.getUserStudentId());
                }
                List<UserStudent> userStudentList = userStudentService.getUserStudentListByIds(userStudentIds);

                //通过学生信息列表获取用户信息
                List<Integer> userIds = Lists.newArrayList();
                for (UserStudent userStudent : userStudentList) {
                    userIds.add(userStudent.getUserId());
                }
                List<SysUser> sysUserList = sysUserService.getSysUserByUserIdList(userIds);

                //数据转换
                List<StudyScoreDto> studyScoreDtoList = StudyScoreDtoMapper.INSTANCE.entityListToDtoList(studyScoreList);

                for (StudyScoreDto studyScoreDto : studyScoreDtoList) {

                    //获取学生姓名和认证状态
                    for (UserStudent student : userStudentList) {
                        if (studyScoreDto.getUserStudentId().intValue() == student.getId().intValue()) {
                            for (SysUser sysUser : sysUserList) {
                                if (student.getUserId().intValue() == sysUser.getId().intValue()) {
                                    studyScoreDto.setActivationStatus(sysUser.getActivationStatus());
                                    studyScoreDto.setUserName(sysUser.getUserName());
                                }
                            }
                        }
                    }
                }

                return convertPage((Page) studyScoreList, studyScoreDtoList);
            } else {
                Page page = null;
                return page;
            }
        }
        Page page = null;
        return page;
    }


    /**
     * 添加学习-成绩
     * @param studyScore
     * @return
     */
    public int save(StudyScore studyScore){

        return studyScoreMapper.insert(studyScore);

    }


    /**
     * 删除学习-成绩
     * @param id
     * @return
     */
    public int remove(Integer id){

        return studyScoreMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改学习-成绩
     * @param studyScore
     * @return
     */
    public int modify(StudyScore studyScore){

        return studyScoreMapper.updateByPrimaryKeySelective(studyScore);

    }

}
