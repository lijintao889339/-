package com.teamsking.domain.service.course;

import com.teamsking.api.dto.course.CourseItemDto;
import com.teamsking.api.dto.course.CourseItemDtoMapper;
import com.teamsking.domain.entity.course.CourseItem;
import com.teamsking.domain.entity.node.Node;
import com.teamsking.domain.repository.CourseItemMapper;
import java.util.List;

import com.teamsking.domain.repository.NodeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class CourseItemService {

    @Autowired
    CourseItemMapper courseItemMapper;
    @Autowired
    NodeMapper nodeMapper;

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
            if (20 == itemDto.getItemType()){
                //小项类型为文档
                itemDto.setType(20);

            }else if (10 == itemDto.getItemType()){
                //查询此小项的资源类型
                Node node = nodeMapper.selectByPrimaryKey(itemDto.getRelationId());
                if (50 == node.getNodeType()){
                    //资源类型为视频
                    itemDto.setType(50);

                }else if (60 == node.getNodeType()){
                    //资源类型为音频
                    itemDto.setType(60);
                }else if (70 == node.getNodeType()){
                    //资源类型为图片
                    itemDto.setType(70);
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
        return courseItemMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改课程模板中的小项
     * @param courseItem
     * @return
     */
    public int modify(CourseItem courseItem){

        return courseItemMapper.updateByPrimaryKeySelective(courseItem);
    }

}



