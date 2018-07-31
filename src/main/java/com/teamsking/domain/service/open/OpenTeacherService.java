package com.teamsking.domain.service.open;


import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.AddOpenTeacherDto;
import com.teamsking.api.dto.open.OpenTeacherDtoMapper;
import com.teamsking.api.dto.sys.SysUserDtoMapper;
import com.teamsking.domain.entity.open.OpenTeacher;
import com.teamsking.domain.entity.open.OpenTeacherTag;
import com.teamsking.domain.entity.sys.SysUser;
import com.teamsking.domain.entity.tag.Tag;
import com.teamsking.domain.entity.tag.UserTag;
import com.teamsking.domain.repository.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    @Autowired
    OpenTeacherTagMapper openTeacherTagMapper;


    /**
     * 获取班次-教师管理列表
     * @return
     */
    public List<OpenTeacher> list(){

        OpenTeacher openTeacher = new OpenTeacher();
        openTeacher.setDeleteStatus(2);
        return openTeacherMapper.select(openTeacher);
    }

    /**
     * 添加班次-教师管理
     * @param addOpenTeacherDto
     * @return
     */
    public int save(AddOpenTeacherDto addOpenTeacherDto){

        List<Integer> tagIdList = Lists.newArrayList();

        //添加课程老师信息
        OpenTeacher openTeacher = OpenTeacherDtoMapper.INSTANCE.addDtoToEntity(addOpenTeacherDto);
        openTeacher.setDeleteStatus(2);
        openTeacherMapper.insertSelective(openTeacher);
        //在课程老师标签关系表添加该老师与标签的对应关系
        //1.批量添加标签名称
        String[] tagNames = addOpenTeacherDto.getTagName();

        for (String tagName : tagNames) {
            Tag tag = new Tag();
            tag.setTagName(tagName);
            Tag tagEntity = tagMapper.selectOne(tag);
            if (null == tagEntity){
                Tag newTag = new Tag();
                newTag.setDeleteStatus(2);
                newTag.setTagName(tagName);
                tagMapper.insert(newTag);
                tagIdList.add(newTag.getId());
            }else {
                tagIdList.add(tagEntity.getId());
            }
        }

        //2添加老师和标签的关系
        List<OpenTeacherTag> openTeacherTagList = Lists.newArrayList();
        for (Integer tagId : tagIdList){
            OpenTeacherTag openTeacherTag = new OpenTeacherTag();
            openTeacherTag.setTagId(tagId);
            openTeacherTag.setOpenTeacherId(openTeacher.getId());
            openTeacherTagList.add(openTeacherTag);
        }
        return openTeacherTagMapper.insertOpenTeacherTagByTags(openTeacherTagList);
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

    /**
     * 通过老师Ids获取老师信息列表
     * @param teacherIds
     * @return
     */
    public List<OpenTeacher> getTeacherListByTeacherIds(List<Integer> teacherIds) {

        Example teacherExample = new Example(OpenTeacher.class);
        Example.Criteria cri = teacherExample.createCriteria();
        cri.andIn("id",teacherIds);
        return openTeacherMapper.selectByExample(teacherExample);

    }
}
