package com.teamsking.api.endpoint.course;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.course.CourseTopicDto;
import com.teamsking.api.dto.course.CourseTopicDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.course.CourseTopic;
import com.teamsking.domain.service.course.CourseTopicService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*@author linhao
*/
@RestController
@Slf4j
@Api(tags = "课程-活动管理接口")
public class CourseTopicController extends BaseController {

    @Autowired
    CourseTopicService courseTopicService;

    /**
     * 获取课程-活动列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程-活动列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course_topics")
    public Result courseTopicList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<CourseTopic> courseTopicList = courseTopicService.list();
        List<CourseTopicDto> courseTopicDtoList = CourseTopicDtoMapper.INSTANCE.entityListToDtoList(courseTopicList);
        return Result.success().addData("pager",warpPage(courseTopicDtoList));
    }

    /**
     * 添加课程-活动
     * @param courseTopic
     * @return
     */
    @ApiOperation(value = "添加课程-活动",consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTopic", value = "课程-活动", required = true, dataType = "CourseTopicDto")
    })
    @PostMapping("/course_topic")
    public Result addCourseTopic(@RequestBody CourseTopicDto courseTopic){

        CourseTopic courseTopicEntity = CourseTopicDtoMapper.INSTANCE.dtoToEntity(courseTopic);
        courseTopicService.save(courseTopicEntity);
        return Result.success();
    }

    /**
     * 删除课程-活动
     * @param id
     * @return
     */
    @ApiOperation(value = "删除课程-活动", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程-活动的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/course_topic/{id}")
    public Result removeCourseTopic(@PathVariable("id") int id){

        courseTopicService.remove(id);
        return Result.success();
    }

    /**
     * 修改课程-活动
     * @param id
     * @param courseTopic
     * @return
     */
    @ApiOperation(value = "删除课程-活动", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTopic", value = "课程-活动", required = true, dataType = "CourseTopicDto")
    })
    @PutMapping("/course_topic/{id}")
    public Result modifyCourseTopic(@PathVariable("id") int id,
                                    @RequestBody CourseTopicDto courseTopic){

        CourseTopic courseTopicEntity = CourseTopicDtoMapper.INSTANCE.dtoToEntity(courseTopic);
        courseTopicEntity.setId(id);
        courseTopicService.modify(courseTopicEntity);
        return Result.success();
    }


}
