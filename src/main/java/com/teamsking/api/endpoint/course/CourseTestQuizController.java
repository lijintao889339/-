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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param schoolId
     * @param courseId
     * @param sectionId
     * @param testId
     * @param quizId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程-测验与试题关系列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("school/{schoolId}/course/{courseId}/section/{sectionId}/test/{testId}/quiz/{quizId}/course_test_quizs")
    public Result courseTestQuizList(@PathVariable("schoolId") int schoolId,
                                     @PathVariable("courseId") int courseId,
                                     @PathVariable("sectionId") int sectionId,
                                     @PathVariable("testId") int testId,
                                     @PathVariable("quizId") int quizId,
                                     int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        CourseTestQuizDto courseTestQuiz = new CourseTestQuizDto();
        courseTestQuiz.setSchoolId(schoolId);
        courseTestQuiz.setCourseId(courseId);
        courseTestQuiz.setSectionId(sectionId);
        courseTestQuiz.setTestId(testId);
        courseTestQuiz.setQuizId(quizId);
        CourseTestQuiz courseTestQuizEntity = CourseTestQuizDtoMapper.INSTANCE.dtoToEntity(courseTestQuiz);
        List<CourseTestQuiz> courseTestQuizList = courseTestQuizService.list(courseTestQuizEntity);

        List<CourseTestQuizDto> courseTestQuizDtoList = CourseTestQuizDtoMapper.INSTANCE.entityListToDtoList(courseTestQuizList);
        return Result.success().addData("pager",warpPage(courseTestQuizDtoList));
    }

    /**
     * 添加课程-测验试题关系
     * @param schoolId
     * @param courseId
     * @param sectionId
     * @param testId
     * @param quizId
     * @param courseTestQuiz
     * @return
     */
    @ApiOperation(value = "添加课程-测验与试题关系", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTestQuiz", value = "课程-测验与试题关系", required = true, dataType = "CourseTestQuizDto")
    })
    @PostMapping("school/{schoolId}/course/{courseId}/section/{sectionId}/test/{testId}/quiz/{quizId}/course_test_quiz")
    public Result addCourseTestQuiz(@PathVariable("schoolId") int schoolId,
                                    @PathVariable("courseId") int courseId,
                                    @PathVariable("sectionId") int sectionId,
                                    @PathVariable("testId") int testId,
                                    @PathVariable("quizId") int quizId,
                                    @RequestBody CourseTestQuizDto courseTestQuiz){

        CourseTestQuiz courseTestQuizEntity = CourseTestQuizDtoMapper.INSTANCE.dtoToEntity(courseTestQuiz);
        courseTestQuizEntity.setSchoolId(schoolId);
        courseTestQuizEntity.setCourseId(courseId);
        courseTestQuizEntity.setSectionId(sectionId);
        courseTestQuizEntity.setTestId(testId);
        courseTestQuizEntity.setQuizId(quizId);
        courseTestQuizService.save(courseTestQuizEntity);
        return Result.success();
    }

    /**
     * 删除课程-测验试题关系
     * @param schoolId
     * @param courseId
     * @param sectionId
     * @param testId
     * @param quizId
     * @param id
     * @param courseTestQuiz
     * @return
     */
    @ApiOperation(value = "删除课程-测验与试题关系", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTestQuiz", value = "课程-测验与试题关系", required = true, dataType = "CourseTestQuizDto")
    })
    @DeleteMapping("school/{schoolId}/course/{courseId}/section/{sectionId}/test/{testId}/quiz/{quizId}/course_test_quiz/{id}")
    public Result removeCourseTestQuiz(@PathVariable("schoolId") int schoolId,
                                       @PathVariable("courseId") int courseId,
                                       @PathVariable("sectionId") int sectionId,
                                       @PathVariable("testId") int testId,
                                       @PathVariable("quizId") int quizId,
                                       @PathVariable("id") int id,
                                       @RequestBody CourseTestQuizDto courseTestQuiz){

        CourseTestQuiz courseTestQuizEntity = CourseTestQuizDtoMapper.INSTANCE.dtoToEntity(courseTestQuiz);
        courseTestQuizEntity.setSchoolId(schoolId);
        courseTestQuizEntity.setCourseId(courseId);
        courseTestQuizEntity.setSectionId(sectionId);
        courseTestQuizEntity.setTestId(testId);
        courseTestQuizEntity.setQuizId(quizId);
        courseTestQuizEntity.setId(id);
        courseTestQuizService.remove(courseTestQuizEntity);
        return Result.success();
    }

    /**
     * 修改课程-测验试题关系
     * @param schoolId
     * @param courseId
     * @param sectionId
     * @param testId
     * @param quizId
     * @param id
     * @param courseTestQuiz
     * @return
     */
    @ApiOperation(value = "修改课程-测验与试题关系", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTestQuiz", value = "课程-测验与试题关系", required = true, dataType = "CourseTestQuizDto")
    })
    @PutMapping("school/{schoolId}/course/{courseId}/section/{sectionId}/test/{testId}/quiz/{quizId}/course_test_quiz/{id}")
    public Result modifCourseTestQuiz(@PathVariable("schoolId") int schoolId,
                                       @PathVariable("courseId") int courseId,
                                       @PathVariable("sectionId") int sectionId,
                                       @PathVariable("testId") int testId,
                                       @PathVariable("quizId") int quizId,
                                       @PathVariable("id") int id,
                                       @RequestBody CourseTestQuizDto courseTestQuiz){

        CourseTestQuiz courseTestQuizEntity = CourseTestQuizDtoMapper.INSTANCE.dtoToEntity(courseTestQuiz);
        courseTestQuizEntity.setSchoolId(schoolId);
        courseTestQuizEntity.setCourseId(courseId);
        courseTestQuizEntity.setSectionId(sectionId);
        courseTestQuizEntity.setTestId(testId);
        courseTestQuizEntity.setQuizId(quizId);
        courseTestQuizEntity.setId(id);
        courseTestQuizService.modify(courseTestQuizEntity);
        return Result.success();
    }

}
