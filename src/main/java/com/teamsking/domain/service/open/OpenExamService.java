package com.teamsking.domain.service.open;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.OpenExamDto;
import com.teamsking.api.dto.open.OpenExamDtoMapper;
import com.teamsking.api.dto.open.OpenExamNameDto;
import com.teamsking.domain.entity.open.OpenExam;
import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.entity.open.OpenUserTeacher;
import com.teamsking.domain.entity.sys.SysUser;
import com.teamsking.domain.entity.sys.UserStudentExam;
import com.teamsking.domain.entity.sys.UserTeacher;
import com.teamsking.domain.repository.OpenExamMapper;
import com.teamsking.domain.repository.OpenUserMapper;
import com.teamsking.domain.repository.OpenUserTeacherMapper;
import com.teamsking.domain.repository.UserStudentExamMapper;
import com.teamsking.domain.service.BaseService;
import com.teamsking.domain.service.sys.SysUserService;
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
    UserTeacherService userTeacherService;
    @Autowired
    OpenExamService openExamService;
    @Autowired
    SysUserService sysUserService;


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


}
