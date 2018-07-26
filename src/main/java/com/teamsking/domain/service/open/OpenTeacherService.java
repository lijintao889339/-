package com.teamsking.domain.service.open;


import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.AddOpenTeacherDto;
import com.teamsking.api.dto.open.OpenTeacherDtoMapper;
import com.teamsking.api.dto.sys.SysUserDtoMapper;
import com.teamsking.domain.entity.open.OpenTeacher;
import com.teamsking.domain.entity.sys.SysUser;
import com.teamsking.domain.entity.tag.Tag;
import com.teamsking.domain.entity.tag.UserTag;
import com.teamsking.domain.repository.OpenTeacherMapper;
import java.util.List;

import com.teamsking.domain.repository.SysUserMapper;
import com.teamsking.domain.repository.TagMapper;
import com.teamsking.domain.repository.UserTagMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenTeacherService {


    @Autowired
    OpenTeacherMapper openTeacherMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    UserTagMapper userTagMapper;


    /**
     * 获取班次-教师管理列表
     * @return
     */
    public List<OpenTeacher> list(){

        return openTeacherMapper.selectAll();
    }

    /**
     * 添加班次-教师管理
     * @param openTeacher
     * @return
     */
    public int save(AddOpenTeacherDto openTeacher){

        //先在用户表中添加老师基本信息(头像、姓名、微博、微信)
        SysUser sysUser = SysUserDtoMapper.INSTANCE.AddTeacherDtoToEtity(openTeacher);
        sysUser.setAvatar(openTeacher.getTeacherAvatar());
        sysUser.setUserName(openTeacher.getTeacherName());
        sysUser.setDeleteStatus(2);
        sysUserMapper.insertSelective(sysUser);

        //再在用户标签关系表添加该老师(用户)对应的标签
        //1.批量添加标签名称
        String[] tagNames = openTeacher.getTagName();
        List<Tag> tagList = Lists.newArrayList();
        for (String tagName : tagNames) {
            Tag tag = new Tag();
            tag.setDeleteStatus(2);
            tag.setTagName(tagName);
            tagList.add(tag);
        }
        tagMapper.insertTagByTags(tagList);

        //2.讲添加的标签和该老师(用户)关联
        //(1)获取添加的标签Id
        List<Integer> tagIdList = Lists.newArrayList();
        for (Tag tag : tagList) {
            tagIdList.add(tag.getId());
        }

        //(2)添加用户和标签的关系
        List<UserTag> userTagList = Lists.newArrayList();
        for (Integer tagId : tagIdList){
            UserTag userTag = new UserTag();
            userTag.setTagId(tagId);
            userTag.setUserId(sysUser.getId());
            userTag.setDeleteStatus(2);
            userTagList.add(userTag);
        }
        userTagMapper.insertUserTagByUserTags(userTagList);

        //最后在老师表中添加信息(头像、姓名、头衔、简介、用户Id)
        OpenTeacher openTeacherEntity = OpenTeacherDtoMapper.INSTANCE.addDtoToEntity(openTeacher);
        openTeacherEntity.setUserId(sysUser.getId());
        openTeacherEntity.setTeacherAvatar(sysUser.getAvatar());
        openTeacherEntity.setTeacherName(sysUser.getUserName());
        openTeacherEntity.setDeleteStatus(2);
        return openTeacherMapper.insertSelective(openTeacherEntity);
    }

    /**
     * 删除班次-教师管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openTeacherMapper.deleteByPrimaryKey(id);
    }


    /**
     * 修改班次-教师管理
     * @param openTeacher
     * @return
     */
    public int modify(OpenTeacher openTeacher){

        return openTeacherMapper.updateByPrimaryKeySelective(openTeacher);

    }

}
