package com.teamsking.api.endpoint;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.CourseItemDto;
import com.teamsking.api.dto.CourseItemDtoMapper;
import com.teamsking.domain.entity.CourseItem;
import com.teamsking.domain.service.CourseItemService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
*@author linhao
*/
@RestController
@Api(tags = "课程的章节项管理接口")
@Slf4j
public class CourseItemController extends BaseController{

    @Autowired
    CourseItemService courseItemService;

    /**
     * 获取某一课程的某一章节的所有项的列表
     * @param courseId
     * @param chapterId
     * @param sectionId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程中章节项的列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course/{courseId}/chapter/{chapterId}/section/{sectionId}/course_items")
    public Result courseItemList(@PathVariable("courseId") int courseId,
                                 @PathVariable("chapterId") int chapterId,
                                 @PathVariable("sectionId") int sectionId,
                                 int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        CourseItemDto courseItem = new CourseItemDto();
        courseItem.setCourseId(courseId);
        courseItem.setChapterId(chapterId);
        courseItem.setSectionId(sectionId);
        CourseItem courseItemEntity = CourseItemDtoMapper.INSTANCE.dtoToEntity(courseItem);
        List<CourseItem> courseItemList = courseItemService.list(courseItemEntity);

        List<CourseItemDto> courseItemDtoList = CourseItemDtoMapper.INSTANCE.entityListToDtoList(courseItemList);
        return Result.success().addData("pager",warpPage(courseItemDtoList));
    }

    /**
     * 添加某一课程的某一章节的对应项
     * @param courseId
     * @param chapterId
     * @param sectionId
     * @param courseItemDto
     * @return
     */
    @ApiOperation(value = "添加课程中的章节项", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseItem", value = "课程的章节项", required = true, dataType = "CourseItemDto")
    })
    @PostMapping("/course/{courseId}/chapter/{chapterId}/section/{sectionId}/course_item")
    public Result addCourseItem(@PathVariable("courseId") int courseId,
                                   @PathVariable("chapterId") int chapterId,
                                   @PathVariable("sectionId") int sectionId,
                                   @RequestBody CourseItemDto courseItemDto){

        CourseItem courseItemEntity = CourseItemDtoMapper.INSTANCE.dtoToEntity(courseItemDto);
        courseItemEntity.setCourseId(courseId);
        courseItemEntity.setChapterId(chapterId);
        courseItemEntity.setSectionId(sectionId);
        courseItemService.save(courseItemEntity);
        return Result.success();
    }

    /**
     * 删除某一课程的某一章节的对应项
     * @param id
     * @param courseId
     * @param chapterId
     * @param sectionId
     * @param courseItemDto
     * @return
     */
    @ApiOperation(value = "删除课程中的章节项", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseItem", value = "课程的章节项", required = true, dataType = "CourseItemDto")
    })
    @DeleteMapping("/course/{courseId}/chapter/{chapterId}/section/{sectionId}/course_item/{id}")
    public Result removeCourseItem(@PathVariable("id") int id,
                                   @PathVariable("courseId") int courseId,
                                   @PathVariable("chapterId") int chapterId,
                                   @PathVariable("sectionId") int sectionId,
                                   @RequestBody CourseItemDto courseItemDto){

        CourseItem courseItemEntity = CourseItemDtoMapper.INSTANCE.dtoToEntity(courseItemDto);
        courseItemEntity.setId(id);
        courseItemEntity.setCourseId(courseId);
        courseItemEntity.setChapterId(chapterId);
        courseItemEntity.setSectionId(sectionId);
        courseItemService.remove(courseItemEntity);
        return Result.success();
    }

    /**
     * 修改某一课程的某一章节的对应项
     * @param id
     * @param courseId
     * @param chapterId
     * @param sectionId
     * @param courseItemDto
     * @return
     */
    @ApiOperation(value = "修改课程中的章节项", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseItem", value = "课程的章节项", required = true, dataType = "CourseItemDto")
    })
    @PutMapping("/course/{courseId}/chapter/{chapterId}/section/{sectionId}/course_item/{id}")
    public Result modifyCourseItem(@PathVariable("id") int id,
                                   @PathVariable("courseId") int courseId,
                                   @PathVariable("chapterId") int chapterId,
                                   @PathVariable("sectionId") int sectionId,
                                   @RequestBody CourseItemDto courseItemDto){

        CourseItem courseItemEntity = CourseItemDtoMapper.INSTANCE.dtoToEntity(courseItemDto);
        courseItemEntity.setId(id);
        courseItemEntity.setCourseId(courseId);
        courseItemEntity.setChapterId(chapterId);
        courseItemEntity.setSectionId(sectionId);
        courseItemService.modify(courseItemEntity);
        return Result.success();
    }
}
