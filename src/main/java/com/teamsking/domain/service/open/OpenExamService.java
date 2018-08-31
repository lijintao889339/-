package com.teamsking.domain.service.open;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.OpenExamDto;
import com.teamsking.api.dto.open.OpenExamDtoMapper;
import com.teamsking.api.dto.open.OpenExamNameDto;
import com.teamsking.api.dto.open.OpenExamUserDto;
import com.teamsking.domain.entity.open.OpenExam;
import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.entity.open.OpenUserTeacher;
import com.teamsking.domain.entity.sys.*;
import com.teamsking.domain.repository.*;
import com.teamsking.domain.service.BaseService;
import com.teamsking.domain.service.sys.SysUserService;
import com.teamsking.domain.service.sys.UserStudentService;
import com.teamsking.domain.service.sys.UserTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
public class OpenExamService extends BaseService {

    @Autowired
    OpenExamMapper openExamMapper;
    @Autowired
    OpenUserTeacherMapper openUserTeacherMapper;
    @Autowired
    OpenUserMapper openUserMapper;
    @Autowired
    UserStudentExamMapper userStudentExamMapper;
    @Autowired
    UserTeacherMapper userTeacherMapper;
    @Autowired
    UserTeacherExamMapper userTeacherExamMapper;

    @Autowired
    UserTeacherService userTeacherService;
    @Autowired
    OpenExamService openExamService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    UserStudentService userStudentService;


    /**
     * 根据班课id创建考试
     * @param openExamDto
     * @return
     */
    public int saveOpenExam(OpenExamDto openExamDto){

        OpenExam openExamEntity = OpenExamDtoMapper.INSTANCE.dtoToEntity(openExamDto);
        openExamEntity.setDeleteStatus(2);

        return openExamMapper.insert(openExamEntity);
    }


    /**
     * 根据班课id查询考试列表
     * @param openId
     * @param pageSize
     * @param pageNo
     * @return
     */
    public Page openExamList(int openId,int pageNo,int pageSize){

        PageHelper.startPage(pageNo, pageSize);
        //获取考试信息
        OpenExam openExam = new OpenExam();
        openExam.setDeleteStatus(2);
        openExam.setOpenId(openId);
        List<OpenExam> openExamList = openExamMapper.select(openExam);


        if(0 != openExamList.size()){

            //获取该班课下学生总数量
            OpenUser openUser = new OpenUser();
            openUser.setDeleteStatus(2);
            openUser.setOpenId(openId);
            int allUserNum = openUserMapper.selectCount(openUser);

            //获取该班课下的教学老师
            //1.获取与该班课有关的教学老师Id
            OpenUserTeacher openUserTeacher = new OpenUserTeacher();
            openUserTeacher.setOpenId(openId);
            List<OpenUserTeacher> openUserTeacherList = openUserTeacherMapper.select(openUserTeacher);

            List<Integer> userTeacherIdList = Lists.newArrayList();
            for (OpenUserTeacher openTeacher : openUserTeacherList) {
                userTeacherIdList.add(openTeacher.getUserTeacherId());
            }

            //2.获取教学老师信息
            List<UserTeacher> userTeacherList = userTeacherService.getUserTeacherListByIds(userTeacherIdList);
            List<String> userTeacherNames = Lists.newArrayList();
            for (UserTeacher userTeacher:userTeacherList) {

                userTeacherNames.add(userTeacher.getUserName());

            }

            //数据转换
            List<OpenExamNameDto> openExamNameDtoList = OpenExamDtoMapper.INSTANCE.entityListNameDto(openExamList);

            for (OpenExamNameDto openExamNameDto: openExamNameDtoList) {

                openExamNameDto.setTeacherNames(userTeacherNames);

                //班课考试已提交人数
                UserStudentExam userStudentExam = new UserStudentExam();
                userStudentExam.setDeleteStatus(2);
                userStudentExam.setExamId(openExamNameDto.getId());
                int stopUserCount =userStudentExamMapper.selectCount(userStudentExam);
                openExamNameDto.setStopUserCount(stopUserCount);

                //总人数
                openExamNameDto.setUserCount(allUserNum);

            }

            return convertPage((Page)openExamList,openExamNameDtoList);

        }else {
            Page page =null;
            return page;
        }



    }


    /**
     * 根据教学老师，考试类型，考试状态查询考试信息列表
     * @param openId
     * @param pageNo
     * @param pageSize
     * @param state
     * @param teacherName
     * @return
     */
    public Page openExamByStateList(int openId,int pageNo,int pageSize,String state,String teacherName){


        List<Integer> examIds = Lists.newArrayList();
        if ("" != teacherName){

            //获取教学老师id
            UserTeacher newUserTeacher = new UserTeacher();
            newUserTeacher.setDeleteStatus(2);
            newUserTeacher.setUserName(teacherName);
            UserTeacher userTeacher = userTeacherMapper.selectOne(newUserTeacher);

            //获取考试IDS
            UserTeacherExam userTeacherExam = new UserTeacherExam();
            userTeacherExam.setDeleteStatus(2);
            userTeacherExam.setUserTeacherId(userTeacher.getId());
            List<UserTeacherExam> teacherExamList = userTeacherExamMapper.select(userTeacherExam);

            //遍历集合
            for (UserTeacherExam teacherExam : teacherExamList){
                examIds.add(teacherExam.getExamId());
            }
        }

        //根据考试状态和教学老师查询考试信息
        Example openExamExample = new Example(OpenExam.class);
        //openExamExample.and().andEqualTo("deleteStatus",2);
        Example.Criteria cri = openExamExample.createCriteria();
        cri.andEqualTo("openId",openId);
        cri.andEqualTo("deleteStatus",2);
        if ("" != state){
            cri.andEqualTo("state", state);
        }
        if ("" != teacherName){
            cri.andIn("id", examIds);
        }

        /*if ("" != teacherName){
            openExamExample.and().andLike("teacherName","%" + teacherName + "%");

        }*/

        //分页操作
        PageHelper.startPage(pageNo, pageSize);

        List<OpenExam> openExamList = openExamMapper.selectByExample(openExamExample);

        if(0 != openExamList.size()){

            //获取该班课下学生总数量
            OpenUser openUser = new OpenUser();
            openUser.setDeleteStatus(2);
            openUser.setOpenId(openId);
            int allUserNum = openUserMapper.selectCount(openUser);

            List<OpenExamNameDto> openExamNameDtoList = OpenExamDtoMapper.INSTANCE.entityListNameDto(openExamList);

            for (OpenExamNameDto openExamNameDto:openExamNameDtoList) {


                //班课考试已提交人数
                UserStudentExam userStudentExam = new UserStudentExam();
                userStudentExam.setDeleteStatus(2);
                userStudentExam.setExamId(openExamNameDto.getId());
                int stopUserCount =userStudentExamMapper.selectCount(userStudentExam);
                //int submitCount = allUserNum / stopUserCount;
                openExamNameDto.setStopUserCount(stopUserCount);

                //总人数
                openExamNameDto.setUserCount(allUserNum);

            }

            return convertPage((Page)openExamList,openExamNameDtoList);
        }else {
            Page page = null;
            return page;
        }


    }


    /**
     * 根据考试标题模糊查询考试列表
     * @param openId
     * @param pageNo
     * @param pageSize
     * @param title
     * @param type
     * @return
     */
    public Page openExamListByName(int openId,int pageNo,int pageSize,String title,String type){


        Integer examType;
        if ("" != type){

            if ("正考".equals(type)){
                examType = 1;
            }else if ("补考".equals(type)){
                examType = 2;
            }
        }



        //根据考试标题模糊查询考试信息
        Example openExamExample = new Example(OpenExam.class);
        openExamExample.and().andEqualTo("deleteStatus",2);

        if ("" != title){
            openExamExample.and().andLike("title","%" + title + "%");

        }/*else {

            return openExamService.openExamList(openId,pageSize,pageNo);

        }*/

//        if ("" != type){
//            if (1 == examType){
//                openExamExample.and().andEqualTo("examType",1);
//            }else if (2 == examType){
//                openExamExample.and().andEqualTo("examType",2);
//            }
//
//        }

        /*List<Integer> teacherIds = Lists.newArrayList();

        if ("" != teacherName){
            //查询搜索的教学老师的ids
            List<Integer> sysUserIds = Lists.newArrayList();
            List<SysUser> sysUserList = sysUserService.getByLikeName(teacherName);
            if (0 != sysUserList.size()){
                for (SysUser sysUser : sysUserList){
                    sysUserIds.add(sysUser.getId());
                }

                //获取教师Ids
                List<UserTeacher> userTeacherList = userTeacherService.getUserTeacherListByUserIds(sysUserIds);
                for (UserTeacher userTeacher : userTeacherList){
                    teacherIds.add(userTeacher.getId());
                }
            }
        }

        if (0 != teacherIds.size() ) {
            //cri.andIn("userTeacherId", teacherIds);
            openExamExample.and().andEqualTo("userTeacherId", teacherIds);
        }*/

        //分页操作
        PageHelper.startPage(pageNo, pageSize);

        List<OpenExam> openExamList = openExamMapper.selectByExample(openExamExample);

        if (0 != openExamList.size()){

            //获取该班课下学生总数量
            OpenUser openUser = new OpenUser();
            openUser.setDeleteStatus(2);
            openUser.setOpenId(openId);
            int allUserNum = openUserMapper.selectCount(openUser);


            //数据转换
            List<OpenExamNameDto> openExamNameDtoList = OpenExamDtoMapper.INSTANCE.entityListNameDto(openExamList);

            for (OpenExamNameDto openExamNameDto:openExamNameDtoList) {


                //班课考试已提交人数
                UserStudentExam userStudentExam = new UserStudentExam();
                userStudentExam.setDeleteStatus(2);
                userStudentExam.setExamId(openExamNameDto.getId());
                int stopUserCount =userStudentExamMapper.selectCount(userStudentExam);
                openExamNameDto.setStopUserCount(stopUserCount);

                //总人数
                openExamNameDto.setUserCount(allUserNum);



            }

            return convertPage((Page)openExamList,openExamNameDtoList);

        }else {
            Page page = null;
            return page;
        }


    }


    /**批量删除班课考试
     * ping
     * @param ids
     * @return
     */
    public int removeOpenExamByIds(Integer[] ids){

        List<Integer> idList = Lists.newArrayList();
        for (Integer id : ids) {
            idList.add(id);
        }

        OpenExam openExam = new OpenExam();
        openExam.setDeleteStatus(1);

        Example openExamExample = new Example(OpenExam.class);
        Example.Criteria cri = openExamExample.createCriteria();
        cri.andIn("id", idList);

        return openExamMapper.updateByExampleSelective(openExam,openExamExample);

    }


    /**
     * 根据考试id查询学生考试记录
     * @param pageNo
     * @param pageSize
     * @param id
     * @param openId
     * @return
     */
    public Page list(int pageNo, int pageSize, int id,Integer openId){

        //分页
        PageHelper.startPage(pageNo,pageSize);

        //获取考试列表
        OpenExam openExamEntity = new OpenExam();
        openExamEntity.setDeleteStatus(2);
        openExamEntity.setOpenId(openId);
        openExamEntity.setId(id);

        List<OpenExam> openExamList = openExamMapper.select(openExamEntity);

        if (0 != openExamList.size()){

            /*//获取学生信息
            List<Integer> userStudentIds = Lists.newArrayList();
            for (OpenExam openExam : openExamList){
                userStudentIds.add(openExam.getUserStudentId());
            }*/

            UserStudentExam userStudentExam = new UserStudentExam();
            userStudentExam.setExamId(id);
            userStudentExam.setDeleteStatus(2);
            List<UserStudentExam> userStudentExamList = userStudentExamMapper.select(userStudentExam);

            List<Integer> userStudentIds = Lists.newArrayList();
            for (UserStudentExam userStudentExam1:userStudentExamList){
                userStudentIds.add(userStudentExam1.getUserStudentId());
            }

            List<UserStudent> userStudentList = userStudentService.getUserStudentListByIds(userStudentIds);

            //通过学生信息列表获取用户信息
            List<Integer> userIds = Lists.newArrayList();
            for (UserStudent userStudent : userStudentList){
                userIds.add(userStudent.getUserId());
            }
            List<SysUser> sysUserList = sysUserService.getSysUserByUserIdList(userIds);

            //数据转换
            List<OpenExamUserDto> openExamUserDtoList = OpenExamDtoMapper.INSTANCE.entityListUserDto(openExamList);

            for (OpenExamUserDto openExamUserDto:openExamUserDtoList) {

                //获取学生姓名
                for (UserStudent student : userStudentList){
                    if (openExamUserDto.getUserStudentId().intValue() == student.getId().intValue()){
                        for (SysUser sysUser : sysUserList){
                            if (student.getUserId().intValue() == sysUser.getId().intValue()){
                                openExamUserDto.setUserName(sysUser.getUserName());
                            }
                        }
                    }
                }

            }

            return convertPage((Page)openExamList,openExamUserDtoList);

        }else {

            Page page = null;
            return page;
        }


    }


}
