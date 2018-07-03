package com.teamsking.api.endpoint.course;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.course.CourseItemDto;
import com.teamsking.api.dto.course.CourseItemDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.course.CourseItem;
import com.teamsking.domain.service.course.CourseItemService;
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
public class CourseItemController extends BaseController {

    @Autowired
    CourseItemService courseItemService;

    /**
     * 获取课程中章节项的列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程中章节项的列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course_items")
    public Result courseItemList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<CourseItem> courseItemList = courseItemService.list();
        List<CourseItemDto> courseItemDtoList = CourseItemDtoMapper.INSTANCE.entityListToDtoList(courseItemList);
        return Result.success().addData("pager",warpPage(courseItemDtoList));
    }

    /**
     * 添加课程中的章节项
     * @param courseItemDto
     * @return
     */
    @ApiOperation(value = "添加课程中的章节项", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseItem", value = "课程的章节项", required = true, dataType = "CourseItemDto")
    })
    @PostMapping("/course_item")
    public Result addCourseItem(@RequestBody CourseItemDto courseItemDto){

        CourseItem courseItemEntity = CourseItemDtoMapper.INSTANCE.dtoToEntity(courseItemDto);
        courseItemService.save(courseItemEntity);
        return Result.success();
    }

    /**
     * 删除课程的章节项
     * @param id
     * @return
     */
    @ApiOperation(value = "删除课程中的章节项", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程-章节项的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/course_item/{id}")
    public Result removeCourseItem(@PathVariable("id") int id){

        courseItemService.remove(id);
        return Result.success();
    }

    /**
     * 修改课程中的章节项
     * @param id
     * @param courseItemDto
     * @return
     */
    @ApiOperation(value = "修改课程中的章节项", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseItem", value = "课程的章节项", required = true, dataType = "CourseItemDto")
    })
    @PutMapping("/course_item/{id}")
    public Result modifyCourseItem(@PathVariable("id") int id,
                                   @RequestBody CourseItemDto courseItemDto){

        CourseItem courseItemEntity = CourseItemDtoMapper.INSTANCE.dtoToEntity(courseItemDto);
        courseItemEntity.setId(id);
        courseItemService.modify(courseItemEntity);
        return Result.success();
    }
}
