package com.teamsking.api.endpoint;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.CourseTestDto;
import com.teamsking.api.dto.CourseTestDtoMapper;
import com.teamsking.domain.entity.CourseTest;
import com.teamsking.domain.service.CourseTestService;
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
@Api(tags = "课程-测验管理接口")
public class CourseTestController extends BaseController{

    @Autowired
    CourseTestService courseTestService;

    /**
     * 获取某一课程下面的测验
     * @param courseId
     * @param sectionId
     * @param chapterId
     * @param schoolId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程-测验列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("school/{schoolId}/course/{courseId}/chapter/{chapterId}/section/{sectionId}/course_tests")
    public Result courseTestList(@PathVariable("schoolId") int schoolId,
                                 @PathVariable("courseId") int courseId,
                                 @PathVariable("chapterId") int chapterId,
                                 @PathVariable("sectionId") int sectionId,

                                 int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        CourseTestDto courseTest = new CourseTestDto();
        courseTest.setCourseId(courseId);
        courseTest.setSectionId(sectionId);
        courseTest.setChapterId(chapterId);
        courseTest.setSchoolId(schoolId);
        CourseTest courseTestEntity = CourseTestDtoMapper.INSTANCE.dtoToEntity(courseTest);
        List<CourseTest> courseTestList = courseTestService.list(courseTestEntity);

        List<CourseTestDto> courseTestDtoList = CourseTestDtoMapper.INSTANCE.entityListToDtoList(courseTestList);
        return Result.success().addData("pager",warpPage(courseTestDtoList));
    }

    /**
     * 给某一课程添加测验
     * @param courseId
     * @param sectionId
     * @param chapterId
     * @param schoolId
     * @param courseTest
     * @return
     */
    @ApiOperation(value = "添加课程-测试", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTest", value = "课程-测验", required = true, dataType = "CourseTestDto")
    })
    @PostMapping("school/{schoolId}/course/{courseId}/chapter/{chapterId}/section/{sectionId}/course_test")
    public Result addCourseTest(@PathVariable("schoolId") int schoolId,
                                @PathVariable("courseId") int courseId,
                                @PathVariable("chapterId") int chapterId,
                                @PathVariable("sectionId") int sectionId,
                                @RequestBody CourseTestDto courseTest){

        CourseTest courseTestEntity = CourseTestDtoMapper.INSTANCE.dtoToEntity(courseTest);
        courseTestEntity.setCourseId(courseId);
        courseTestEntity.setSectionId(sectionId);
        courseTestEntity.setChapterId(chapterId);
        courseTestEntity.setSchoolId(schoolId);
        courseTestService.save(courseTestEntity);
        return Result.success();
    }

    /**
     * 删除某一课程的指定测验
     * @param id
     * @param courseId
     * @param sectionId
     * @param chapterId
     * @param schoolId
     * @param courseTest
     * @return
     */
    @ApiOperation(value = "删除课程-测试", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTest", value = "课程-测验", required = true, dataType = "CourseTestDto")
    })
    @DeleteMapping("school/{schoolId}/course/{courseId}/chapter/{chapterId}/section/{sectionId}/course_test/{id}")
    public Result removeCourseTest(@PathVariable("schoolId") int schoolId,
                                   @PathVariable("courseId") int courseId,
                                   @PathVariable("chapterId") int chapterId,
                                   @PathVariable("sectionId") int sectionId,
                                   @PathVariable("id") int id,
                                   @RequestBody CourseTestDto courseTest){

        CourseTest courseTestEntity = CourseTestDtoMapper.INSTANCE.dtoToEntity(courseTest);
        courseTestEntity.setId(id);
        courseTestEntity.setCourseId(courseId);
        courseTestEntity.setSectionId(sectionId);
        courseTestEntity.setChapterId(chapterId);
        courseTestEntity.setSchoolId(schoolId);
        courseTestService.remove(courseTestEntity);
        return Result.success();
    }

    /**
     * 修改某一课程的指定测验
     * @param id
     * @param courseId
     * @param sectionId
     * @param chapterId
     * @param schoolId
     * @param courseTest
     * @return
     */
    @ApiOperation(value = "修改课程-测试", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTest", value = "课程-测验", required = true, dataType = "CourseTestDto")
    })
    @PutMapping("school/{schoolId}/course/{courseId}/chapter/{chapterId}/section/{sectionId}/course_test/{id}")
    public Result modifyCourseTest(@PathVariable("schoolId") int schoolId,
                                   @PathVariable("courseId") int courseId,
                                   @PathVariable("chapterId") int chapterId,
                                   @PathVariable("sectionId") int sectionId,
                                   @PathVariable("id") int id,
                                   @RequestBody CourseTestDto courseTest){

        CourseTest courseTestEntity = CourseTestDtoMapper.INSTANCE.dtoToEntity(courseTest);
        courseTestEntity.setId(id);
        courseTestEntity.setCourseId(courseId);
        courseTestEntity.setSectionId(sectionId);
        courseTestEntity.setChapterId(chapterId);
        courseTestEntity.setSchoolId(schoolId);
        courseTestService.modify(courseTestEntity);
        return Result.success();
    }

}
