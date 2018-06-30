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
     * @param courseId
     * @param forumId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程-活动列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course/{courseId}/forum/{forumId}/course_topics")
    public Result courseTopicList(@PathVariable("courseId") int courseId,
                                  @PathVariable("forumId") int forumId,
                                  int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        CourseTopicDto courseTopic = new CourseTopicDto();
        courseTopic.setCourseId(courseId);
        courseTopic.setForumId(forumId);
        CourseTopic courseTopicEntity = CourseTopicDtoMapper.INSTANCE.dtoToEntity(courseTopic);
        List<CourseTopic> courseTopicList = courseTopicService.list(courseTopicEntity);

        List<CourseTopicDto> courseTopicDtoList = CourseTopicDtoMapper.INSTANCE.entityListToDtoList(courseTopicList);
        return Result.success().addData("pager",warpPage(courseTopicDtoList));
    }

    /**
     * 添加课程-活动
     * @param courseId
     * @param forumId
     * @param courseTopic
     * @return
     */
    @ApiOperation(value = "添加课程-活动",consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTopic", value = "课程-活动", required = true, dataType = "CourseTopicDto")
    })
    @PostMapping("/course/{courseId}/forum/{forumId}/course_topic")
    public Result addCourseTopic(@PathVariable("courseId") int courseId,
                                 @PathVariable("forumId") int forumId,
                                 @RequestBody CourseTopicDto courseTopic){

        CourseTopic courseTopicEntity = CourseTopicDtoMapper.INSTANCE.dtoToEntity(courseTopic);
        courseTopicEntity.setCourseId(courseId);
        courseTopicEntity.setForumId(forumId);
        courseTopicService.save(courseTopicEntity);
        return Result.success();
    }

    /**
     * 删除课程-活动
     * @param courseId
     * @param forumId
     * @param id
     * @param courseTopic
     * @return
     */
    @ApiOperation(value = "删除课程-活动", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTopic", value = "课程-活动", required = true, dataType = "CourseTopicDto")
    })
    @DeleteMapping("/course/{courseId}/forum/{forumId}/course_topic/{id}")
    public Result removeCourseTopic(@PathVariable("courseId") int courseId,
                                    @PathVariable("forumId") int forumId,
                                    @PathVariable("id") int id,
                                    @RequestBody CourseTopicDto courseTopic){

        CourseTopic courseTopicEntity = CourseTopicDtoMapper.INSTANCE.dtoToEntity(courseTopic);
        courseTopicEntity.setCourseId(courseId);
        courseTopicEntity.setForumId(forumId);
        courseTopicEntity.setId(id);
        courseTopicService.remove(courseTopicEntity);
        return Result.success();
    }

    /**
     * 修改课程-活动
     * @param courseId
     * @param forumId
     * @param id
     * @param courseTopic
     * @return
     */
    @ApiOperation(value = "删除课程-活动", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTopic", value = "课程-活动", required = true, dataType = "CourseTopicDto")
    })
    @PutMapping("/course/{courseId}/forum/{forumId}/course_topic/{id}")
    public Result modifyCourseTopic(@PathVariable("courseId") int courseId,
                                    @PathVariable("forumId") int forumId,
                                    @PathVariable("id") int id,
                                    @RequestBody CourseTopicDto courseTopic){

        CourseTopic courseTopicEntity = CourseTopicDtoMapper.INSTANCE.dtoToEntity(courseTopic);
        courseTopicEntity.setCourseId(courseId);
        courseTopicEntity.setForumId(forumId);
        courseTopicEntity.setId(id);
        courseTopicService.modify(courseTopicEntity);
        return Result.success();
    }


}
