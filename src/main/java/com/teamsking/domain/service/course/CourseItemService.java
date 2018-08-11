package com.teamsking.domain.service.course;

import com.teamsking.api.dto.course.AddCourseItemDto;
import com.teamsking.api.dto.course.CourseItemDto;
import com.teamsking.api.dto.course.CourseItemDtoMapper;
import com.teamsking.domain.entity.course.CourseItem;
import com.teamsking.domain.entity.course.CourseSection;
import com.teamsking.domain.entity.node.Node;
import com.teamsking.domain.repository.CourseItemMapper;
import java.util.List;

import com.teamsking.domain.repository.CourseSectionMapper;
import com.teamsking.domain.repository.NodeMapper;
import com.teamsking.domain.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class CourseItemService extends BaseService {

    @Autowired
    CourseItemMapper courseItemMapper;
    @Autowired
    NodeMapper nodeMapper;
    @Autowired
    CourseSectionMapper courseSectionMapper;

    /**
     * 获取课程模板中小项的列表
     * @param sectionId
     * @return
     */
    public List<CourseItemDto> list(int sectionId){

        //根据节id查询小项内容
        CourseItem courseItem = new CourseItem();
        courseItem.setSectionId(sectionId);
        List<CourseItem> courseItemList = courseItemMapper.select(courseItem);

        List<CourseItemDto> courseItemDtoList = CourseItemDtoMapper.INSTANCE.entityListToDtoList(courseItemList);
        //CourseItemCategoryDto courseItemCategoryDto = CourseItemDtoMapper.INSTANCE.entityListToCategoryDto(courseItemList);

        //遍历集合
        for (CourseItemDto itemDto : courseItemDtoList){
            //获取小项内容的资源后缀和地址
            Node newNode = nodeMapper.selectByPrimaryKey(itemDto.getRelationId());
            itemDto.setFilePath(newNode.getFilePath());
            itemDto.setSuffixName(newNode.getSuffixName());

            if (20 == itemDto.getItemType()){
                //小项类型为文档
                itemDto.setType(10);

            }else if (10 == itemDto.getItemType()){
                //查询此小项的资源类型
                Node node = nodeMapper.selectByPrimaryKey(itemDto.getRelationId());

                if (20 == node.getNodeType()){
                    //资源类型为视频
                    itemDto.setType(20);
                }else if (30 == node.getNodeType()){
                    //资源类型为音频
                    itemDto.setType(30);
                }else if (40 == node.getNodeType()){
                    //资源类型为图片
                    itemDto.setType(40);
                }
            }
        }
        return courseItemDtoList;
    }

    /**
     * 添加课程模板中的小项
     * @param courseItem
     * @return
     */
    /*public int save(CourseItem courseItem){

        return courseItemMapper.insert(courseItem);
    }*/

    /**
     * 删除课程模板中的小项
     * @param id
     * @return
     */
    public int remove(int id){

        CourseItem courseItem = new CourseItem();
        courseItem.setDeleteStatus(1);
        return courseItemMapper.updateByPrimaryKeySelective(courseItem);
    }

    /**
     * 修改课程模板中的小项的名称
     * @param courseItemDto
     * @param id
     * @return
     */
    public int modify(CourseItemDto courseItemDto , int id){

        //修改小项名称
        CourseItem courseItem = CourseItemDtoMapper.INSTANCE.dtoToEntity(courseItemDto);
        courseItem.setItemName(courseItemDto.getName());
        courseItem.setId(id);
        courseItemMapper.updateByPrimaryKeySelective(courseItem);

        //查询此小项的信息
        CourseItem newCourseItem = courseItemMapper.selectByPrimaryKey(id);

        //修改资源名称
        Node node = new Node();
        node.setTitle(courseItemDto.getName());
        node.setId(newCourseItem.getRelationId());
        return nodeMapper.updateByPrimaryKeySelective(node);

    }

}



