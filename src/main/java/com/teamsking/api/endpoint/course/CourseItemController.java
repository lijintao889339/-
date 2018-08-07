package com.teamsking.api.endpoint.course;

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
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
*@author linhao
*/
@RestController
@Api(tags = "课程模板的小项项管理接口")
@Slf4j
public class CourseItemController extends BaseController {

    @Autowired
    CourseItemService courseItemService;

    /**
     * 获取课程中章节项的列表
     * @param sectionId
     * @return
     */
    @ApiOperation(value = "课程模板小项的列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sectionId", value = "节的主键", required = true, dataType = "int")
    })
    @GetMapping("/course_items/{sectionId}")
    public Result courseItemList(@PathVariable int sectionId){

         List<CourseItemDto> courseItemDtoList = courseItemService.list(sectionId);
        return Result.success().addData("courseItemDtoList",courseItemDtoList);
    }

    /**
     * 添加课程中的章节项
     * @param courseItemDto
     * @return
     */
    /*@ApiOperation(value = "添加课程中的章节项", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseItem", value = "课程的章节项", required = true, dataType = "CourseItemDto")
    })
    @PostMapping("/course_item")
    public Result addCourseItem(@RequestBody CourseItemDto courseItemDto){

        CourseItem courseItemEntity = CourseItemDtoMapper.INSTANCE.dtoToEntity(courseItemDto);
        courseItemService.save(courseItemEntity);
        return Result.success();
    }*/

    /**
     * 删除课程的章节项
     * @param id
     * @return
     */
    @ApiOperation(value = "删除课程模板中的章节项", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程模板-小项的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/course_item/{id}")
    public Result removeCourseItem(@PathVariable("id") int id){

        courseItemService.remove(id);
        return Result.success();
    }

    /**
     * 修改课程中的章节项
     * @param courseItemDto
     * @return
     */
    @ApiOperation(value = "修改课程模板中的章节项", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseItem", value = "课程模板的章节项", required = true, dataType = "CourseItemDto")
    })
    @PutMapping("/course_item")
    public Result modifyCourseItem(@RequestBody CourseItemDto courseItemDto){

        CourseItem courseItemEntity = CourseItemDtoMapper.INSTANCE.dtoToEntity(courseItemDto);
        courseItemService.modify(courseItemEntity);
        return Result.success();
    }
}
