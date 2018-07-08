package com.teamsking.api.endpoint.course;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.course.CourseTestQuizDto;
import com.teamsking.api.dto.course.CourseTestQuizDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.course.CourseTestQuiz;
import com.teamsking.domain.service.course.CourseTestQuizService;
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
@Slf4j
@Api(tags = "课程-测验与试题的关系接口")
public class CourseTestQuizController extends BaseController {

    @Autowired
    CourseTestQuizService courseTestQuizService;

    /**
     * 获取课程-测验与试题关系的列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程-测验与试题关系列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course_test_quizs")
    public Result courseTestQuizList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<CourseTestQuiz> courseTestQuizList = courseTestQuizService.list();

        List<CourseTestQuizDto> courseTestQuizDtoList = CourseTestQuizDtoMapper.INSTANCE.entityListToDtoList(courseTestQuizList);
        return Result.success().addData("pager",warpPage(courseTestQuizDtoList));
    }

    /**
     * 添加课程-测验试题关系
     * @param courseTestQuiz
     * @return
     */
    @ApiOperation(value = "添加课程-测验与试题关系", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTestQuiz", value = "课程-测验与试题关系", required = true, dataType = "CourseTestQuizDto")
    })
    @PostMapping("/course_test_quiz")
    public Result addCourseTestQuiz(@RequestBody CourseTestQuizDto courseTestQuiz){

        CourseTestQuiz courseTestQuizEntity = CourseTestQuizDtoMapper.INSTANCE.dtoToEntity(courseTestQuiz);
        courseTestQuizService.save(courseTestQuizEntity);
        return Result.success();
    }

    /**
     * 删除课程-测验试题关系
     * @param id
     * @return
     */
    @ApiOperation(value = "删除课程-测验与试题关系", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程-测验与试题关系的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/course_test_quiz/{id}")
    public Result removeCourseTestQuiz(@PathVariable("id") int id){

        courseTestQuizService.remove(id);
        return Result.success();
    }

    /**
     * 修改课程-测验试题关系
     * @param id
     * @param courseTestQuiz
     * @return
     */
    @ApiOperation(value = "修改课程-测验与试题关系", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTestQuiz", value = "课程-测验与试题关系", required = true, dataType = "CourseTestQuizDto")
    })
    @PutMapping("/course_test_quiz/{id}")
    public Result modifCourseTestQuiz(@PathVariable("id") int id,
                                       @RequestBody CourseTestQuizDto courseTestQuiz){

        CourseTestQuiz courseTestQuizEntity = CourseTestQuizDtoMapper.INSTANCE.dtoToEntity(courseTestQuiz);
        courseTestQuizEntity.setId(id);
        courseTestQuizService.modify(courseTestQuizEntity);
        return Result.success();
    }

}
