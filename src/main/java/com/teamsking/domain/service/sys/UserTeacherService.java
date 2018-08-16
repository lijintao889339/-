package com.teamsking.domain.service.sys;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.sys.UserTeacherDto;
import com.teamsking.api.dto.sys.UserTeacherDtoMapper;
import com.teamsking.domain.entity.open.OpenGroup;
import com.teamsking.domain.entity.open.OpenUserTeacher;
import com.teamsking.domain.entity.school.School;
import com.teamsking.domain.entity.sys.SysUser;
import com.teamsking.domain.entity.sys.UserTeacher;
import com.teamsking.domain.entity.sys.UserTeacherGroup;
import com.teamsking.domain.repository.OpenUserTeacherMapper;
import com.teamsking.domain.repository.SysUserMapper;
import com.teamsking.domain.repository.UserTeacherMapper;
import com.teamsking.domain.service.BaseService;
import com.teamsking.domain.service.open.OpenGroupService;
import com.teamsking.domain.service.open.OpenUserTeacherService;
import com.teamsking.domain.service.school.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
*@author linhao
*/
@Slf4j
@Service
public class UserTeacherService extends BaseService {

    @Autowired
    UserTeacherMapper userTeacherMapper;
    @Autowired
    OpenUserTeacherMapper openUserTeacherMapper;
    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    UserTeacherService userTeacherService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SchoolService schoolService;
    @Autowired
    UserTeacherGroupService userTeacherGroupService;
    @Autowired
    OpenGroupService openGroupService;
    @Autowired
    OpenUserTeacherService openUserTeacherService;

    /**
     * 根据辅导老师idList获取辅导老师信息
     * @param userTeacherIdList
     * @return
     */
    public List<UserTeacher> getUserTeacherListByIds(List<Integer> userTeacherIdList) {

        Example userTeacherExample = new Example(UserTeacher.class);
        userTeacherExample.and().andIn("id",userTeacherIdList);
        return userTeacherMapper.selectByExample(userTeacherExample);
    }

    /**
     * 分页获取班课下的辅导老师信息列表
     * @param openId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page getOpenUserTeacherById(int openId, int pageNo, int pageSize) {

        //根据班课Id获取辅导老师与班课关系信息
        OpenUserTeacher openUserTeacher = new OpenUserTeacher();
        openUserTeacher.setOpenId(openId);
        List<OpenUserTeacher> openUserTeacherList = openUserTeacherMapper.select(openUserTeacher);

        //遍历集合，获取辅导老师Ids
        List<Integer> userTeacherIds = Lists.newArrayList();
        for (OpenUserTeacher userTeacher : openUserTeacherList){
            userTeacherIds.add(userTeacher.getUserTeacherId());
        }

        //分页
        PageHelper.startPage(pageNo,pageSize);

        //根据辅导老师Ids获取辅导老师信息
        List<UserTeacher> userTeacherList = userTeacherService.getUserTeacherListByIds(userTeacherIds);

        //根据辅导老师Ids获取分组与老师关系信息
        List<UserTeacherGroup> userTeacherGroupList = userTeacherGroupService.getTeacherGroupInfoByTeacherIds(userTeacherIds);

        //遍历集合
        List<Integer> groupIds = Lists.newArrayList();
        for (UserTeacherGroup userTeacherGroup : userTeacherGroupList){
            groupIds.add(userTeacherGroup.getGroupId());
        }

        //获取分组信息
        List<OpenGroup> openGroupList = openGroupService.getOpenGroupByGroupIds(groupIds);

        //遍历集合
        List<Integer> schoolIds = Lists.newArrayList();
        List<Integer> userIds = Lists.newArrayList();
        for (UserTeacher userTeacher : userTeacherList){
            userIds.add(userTeacher.getUserId());
            schoolIds.add(userTeacher.getSchoolId());
        }

        //获取学校信息
        List<School> schoolList = schoolService.getSchoolByShcoolIdList(schoolIds);

        //获取用户信息
        List<SysUser> sysUserList = sysUserService.getSysUserByUserIdList(userIds);

        //数据转换
        List<UserTeacherDto> userTeacherDtos = UserTeacherDtoMapper.INSTANCE.entityDtoToTeacherDtoList(userTeacherList);

        //组装数据
        for (UserTeacherDto userTeacherDto : userTeacherDtos){

            for (School school : schoolList){
                if (userTeacherDto.getSchoolId().intValue() == school.getId().intValue()){
                    userTeacherDto.setSchoolName(school.getSchoolName());
                }
            }

            for (SysUser sysUser : sysUserList){
               if (userTeacherDto.getUserId().intValue() == sysUser.getId().intValue()){
                   userTeacherDto.setEmail(sysUser.getEmail());
               }
            }

            List<String> groupNameList = Lists.newArrayList();
            for (UserTeacherGroup userTeacherGroup : userTeacherGroupList){
                if (userTeacherDto.getId().intValue() == userTeacherGroup.getUserTeacherId()){
                    for (OpenGroup openGroup : openGroupList){
                        if (userTeacherGroup.getGroupId().intValue() == openGroup.getId().intValue()){
                            groupNameList.add(openGroup.getGroupName());
                            userTeacherDto.setGroupNameList(groupNameList);
                        }
                    }
                }
            }

        }
        return convertPage((Page)userTeacherList,userTeacherDtos);
    }

    /**
     * 根据条件搜索班课下的辅导老师信息
     * @param openId
     * @param userName
     * @param email
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page searcingUserTeacherByOpenId(int openId, String userName, String email, int pageNo, int pageSize) {

        //根据班课Id获取辅导老师与班课关系信息
        OpenUserTeacher openUserTeacher = new OpenUserTeacher();
        openUserTeacher.setOpenId(openId);
        List<OpenUserTeacher> openUserTeacherList = openUserTeacherMapper.select(openUserTeacher);

        //遍历集合，获取辅导老师Ids
        List<Integer> userTeacherIds = Lists.newArrayList();
        for (OpenUserTeacher userTeacher : openUserTeacherList){
            userTeacherIds.add(userTeacher.getUserTeacherId());
        }

        //分页
        PageHelper.startPage(pageNo,pageSize);

        //根据辅导老师Ids获取辅导老师信息

        Example userTeacherExample = new Example(UserTeacher.class);
        userTeacherExample.and().andIn("id",userTeacherIds);
        if ("" != userName){
            userTeacherExample.and().andLike("userName","%" + userName + "%");
        }else if ("" != email){
            userTeacherExample.and().andLike("email","%" + email + "%");
        }
        List<UserTeacher> userTeacherList = userTeacherMapper.selectByExample(userTeacherExample);

        if (0 != userTeacherList.size()){

            //根据辅导老师Ids获取分组与老师关系信息
            List<UserTeacherGroup> userTeacherGroupList = userTeacherGroupService.getTeacherGroupInfoByTeacherIds(userTeacherIds);

            //遍历集合
            List<Integer> groupIds = Lists.newArrayList();
            for (UserTeacherGroup userTeacherGroup : userTeacherGroupList){
                groupIds.add(userTeacherGroup.getGroupId());
            }

            //获取分组信息
            List<OpenGroup> openGroupList = openGroupService.getOpenGroupByGroupIds(groupIds);

            //遍历集合
            List<Integer> schoolIds = Lists.newArrayList();
            List<Integer> userIds = Lists.newArrayList();
            for (UserTeacher userTeacher : userTeacherList){
                userIds.add(userTeacher.getUserId());
                schoolIds.add(userTeacher.getSchoolId());
            }

            //获取学校信息
            List<School> schoolList = schoolService.getSchoolByShcoolIdList(schoolIds);

            //获取用户信息
            List<SysUser> sysUserList = sysUserService.getSysUserByUserIdList(userIds);

            //数据转换
            List<UserTeacherDto> userTeacherDtos = UserTeacherDtoMapper.INSTANCE.entityDtoToTeacherDtoList(userTeacherList);

            //组装数据
            for (UserTeacherDto userTeacherDto : userTeacherDtos){

                for (School school : schoolList){
                    if (userTeacherDto.getSchoolId().intValue() == school.getId().intValue()){
                        userTeacherDto.setSchoolName(school.getSchoolName());
                    }
                }

                for (SysUser sysUser : sysUserList){
                    if (userTeacherDto.getUserId().intValue() == sysUser.getId().intValue()){
                        userTeacherDto.setEmail(sysUser.getEmail());
                    }
                }

                List<String> groupNameList = Lists.newArrayList();
                for (UserTeacherGroup userTeacherGroup : userTeacherGroupList){
                    if (userTeacherDto.getId().intValue() == userTeacherGroup.getUserTeacherId()){
                        for (OpenGroup openGroup : openGroupList){
                            if (userTeacherGroup.getGroupId().intValue() == openGroup.getId().intValue()){
                                groupNameList.add(openGroup.getGroupName());
                                userTeacherDto.setGroupNameList(groupNameList);
                            }
                        }
                    }
                }

            }
            return convertPage((Page)userTeacherList,userTeacherDtos);

        }else {
            Page page = null;
            return page;
        }
    }

    /**
     * 批量删除班课与辅导老师关系信息
     * @param userTeacherIdsds
     * @param openId
     * @return
     */
    public int removeMultiOpenUserByIds(Integer[] userTeacherIdsds, int openId) {

        List<Integer> idList = Lists.newArrayList();
        for (Integer id : userTeacherIdsds){
            idList.add(id);
        }

        Example userTeacherExample = new Example(OpenUserTeacher.class);
        userTeacherExample.and().andEqualTo("openId",openId);
        userTeacherExample.and().andIn("userTeacherId",idList);
        return openUserTeacherMapper.deleteByExample(userTeacherExample);
    }


}
