package com.teamsking.domain.service.open;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.OpenGroupDto;
import com.teamsking.api.dto.open.OpenGroupDtoMapper;
import com.teamsking.api.dto.open.OpenGroupNameDto;
import com.teamsking.domain.entity.open.OpenGroup;
import com.teamsking.domain.entity.sys.UserTeacher;
import com.teamsking.domain.entity.sys.UserTeacherGroup;
import com.teamsking.domain.repository.OpenGroupMapper;
import java.util.List;

import com.teamsking.domain.repository.UserTeacherGroupMapper;
import com.teamsking.domain.service.BaseService;
import com.teamsking.domain.service.sys.UserTeacherGroupService;
import com.teamsking.domain.service.sys.UserTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Slf4j
@Service
public class OpenGroupService extends BaseService {

    @Autowired
    OpenGroupMapper openGroupMapper;
    @Autowired
    UserTeacherGroupMapper userTeacherGroupMapper;


    @Autowired
    UserTeacherService userTeacherService;
    @Autowired
    UserTeacherGroupService userTeacherGroupService;

    /**
     * 获取班次下的学生组列表
     * @param pageNo
     * @param pageSize
     * @param openId
     * @return
     */
    public Page list(int pageNo, int pageSize, int openId){

        PageHelper.startPage(pageNo,pageSize);

        //根据班课Id获取学生组信息
        OpenGroup openGroup = new OpenGroup();
        openGroup.setOpenId(openId);
        openGroup.setDeleteStatus(2);
        List<OpenGroup> openGroupList = openGroupMapper.select(openGroup);

        List<Integer> groupIds = Lists.newArrayList();
        for (OpenGroup group : openGroupList){
            groupIds.add(group.getId());
        }

        //获学生组的辅导老师信息
        //1.先获取学生组与有关的辅导老师ids
        List<UserTeacherGroup>  userTeacherGroupList = userTeacherGroupService.getTeacherGroupInfoByGroupIds(groupIds);
        List<Integer> userTeacherIds = Lists.newArrayList();
        for (UserTeacherGroup userTeacherGroup : userTeacherGroupList){
            userTeacherIds.add(userTeacherGroup.getUserTeacherId());
        }

        //2.根据辅导老师Ids获取辅导老师信息
        List<UserTeacher> userTeacherList = userTeacherService.getUserTeacherListByIds(userTeacherIds);

        //转换数据
        List<OpenGroupDto> openGroupDtoList = OpenGroupDtoMapper.INSTANCE.entityListToDtoList(openGroupList);

        //遍历集合
        for (OpenGroupDto openGroupDto : openGroupDtoList){

            List<String> userNameList = Lists.newArrayList();
            for (UserTeacherGroup userTeacherGroup : userTeacherGroupList){
                if (openGroupDto.getId() == userTeacherGroup.getGroupId().intValue()){
                    for (UserTeacher userTeacher : userTeacherList){
                        if (userTeacherGroup.getUserTeacherId().intValue() == userTeacher.getId().intValue()){
                            userNameList.add(userTeacher.getUserName());
                            openGroupDto.setUserNameList(userNameList);
                        }
                    }
                }
            }
        }

        return convertPage((Page)openGroupList ,openGroupDtoList);

    }

    /**
     * 根据条件搜索班组信息
     * @param pageNo
     * @param pageSize
     * @param openId
     * @param groupName
     * @return
     */
    public Page searchingOpenGroupByOpenId(int pageNo, int pageSize, int openId, String groupName) {

        PageHelper.startPage(pageNo,pageSize);

        Example openGroupExample = new Example(OpenGroup.class);
        Example.Criteria cri = openGroupExample.createCriteria();
        cri.andEqualTo("openId",openId);
        cri.andEqualTo("deleteStatus",2);
        if ("" != groupName){
            cri.andLike("groupName","%" + groupName + "%");
        }
        List<OpenGroup> openGroupList = openGroupMapper.selectByExample(openGroupExample);

        if (0 != openGroupList.size()){

            List<Integer> groupIds = Lists.newArrayList();
            for (OpenGroup group : openGroupList){
                groupIds.add(group.getId());
            }

            //获学生组的辅导老师信息
            //1.先获取学生组与有关的辅导老师ids
            List<UserTeacherGroup>  userTeacherGroupList = userTeacherGroupService.getTeacherGroupInfoByGroupIds(groupIds);
            List<Integer> userTeacherIds = Lists.newArrayList();
            for (UserTeacherGroup userTeacherGroup : userTeacherGroupList){
                userTeacherIds.add(userTeacherGroup.getUserTeacherId());
            }

            //2.根据辅导老师Ids获取辅导老师信息
            List<UserTeacher> userTeacherList = userTeacherService.getUserTeacherListByIds(userTeacherIds);

            //转换数据
            List<OpenGroupDto> openGroupDtoList = OpenGroupDtoMapper.INSTANCE.entityListToDtoList(openGroupList);

            //遍历集合
            for (OpenGroupDto openGroupDto : openGroupDtoList){

                List<String> userNameList = Lists.newArrayList();
                for (UserTeacherGroup userTeacherGroup : userTeacherGroupList){
                    if (openGroupDto.getId() == userTeacherGroup.getGroupId().intValue()){
                        for (UserTeacher userTeacher : userTeacherList){
                            if (userTeacherGroup.getUserTeacherId().intValue() == userTeacher.getId().intValue()){
                                userNameList.add(userTeacher.getUserName());
                                openGroupDto.setUserNameList(userNameList);
                            }
                        }
                    }
                }
            }

            return convertPage((Page)openGroupList ,openGroupDtoList);
        }else {
            Page page = null;
            return page;
        }

    }

    /**
     * 添加班次-学生组
     * @param openGroup
     * @return
     */
    public int save(OpenGroup openGroup){

        return openGroupMapper.insert(openGroup);

    }


    /**
     * 删除班次-学生组
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openGroupMapper.deleteByPrimaryKey(id);

    }

    /**
     * 修改班次-学生组
     * @param openGroup
     * @return
     */
    public int modify(OpenGroup openGroup){

        return openGroupMapper.updateByPrimaryKeySelective(openGroup);

    }

    /**
     * 根据分组ids获取分组信息
     * @param groupIds
     * @return
     */
    public List<OpenGroup> getOpenGroupByGroupIds(List<Integer> groupIds) {

        Example openGroupExample = new Example(OpenGroup.class);
        openGroupExample.and().andIn("id",groupIds);
        return openGroupMapper.selectByExample(openGroupExample);
    }

    /**
     * 获取某一班课下的所有班组名称
     * @param openId
     * @return
     */
    public List<OpenGroup> getGroupNameByOpenId(int openId) {

        OpenGroup openGroup = new OpenGroup();
        openGroup.setDeleteStatus(2);
        openGroup.setOpenId(openId);
        return openGroupMapper.select(openGroup);


    }

    /**
     * 给辅导老师添加班组
     * @param userTeacherId
     * @param openGroupNameDto
     * @return
     */
    public int saveOpenGroupByUserTeacherId(int userTeacherId, OpenGroupNameDto openGroupNameDto) {

        UserTeacherGroup userTeacherGroup = new UserTeacherGroup();
        userTeacherGroup.setUserTeacherId(userTeacherId);
        userTeacherGroup.setGroupId(openGroupNameDto.getId());
        return userTeacherGroupMapper.insertSelective(userTeacherGroup);
    }

}
