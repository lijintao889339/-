package com.teamsking.api.endpoint.course;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.course.CourseTestDto;
import com.teamsking.api.dto.course.CourseTestDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.course.CourseTest;
import com.teamsking.domain.service.course.CourseTestService;
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
public class CourseTestController extends BaseController {

    @Autowired
    CourseTestService courseTestService;

    /**
     * 获取课程-测验
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程-测验列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course_tests")
    public Result courseTestList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<CourseTest> courseTestList = courseTestService.list();
        List<CourseTestDto> courseTestDtoList = CourseTestDtoMapper.INSTANCE.entityListToDtoList(courseTestList);
        return Result.success().addData("pager",warpPage(courseTestDtoList));
    }

    /**
     * 添加课程-测验
     * @param courseTest
     * @return
     */
    @ApiOperation(value = "添加课程-测试", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTest", value = "课程-测验", required = true, dataType = "CourseTestDto")
    })
    @PostMapping("/course_test")
    public Result addCourseTest(@RequestBody CourseTestDto courseTest){

        CourseTest courseTestEntity = CourseTestDtoMapper.INSTANCE.dtoToEntity(courseTest);
        courseTestService.save(courseTestEntity);
        return Result.success();
    }

    /**
     * 删除课程-测验
     * @param id
     * @return
     */
    @ApiOperation(value = "删除课程-测试", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程-测验的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/course_test/{id}")
    public Result removeCourseTest(@PathVariable("id") int id){

        courseTestService.remove(id);
        return Result.success();
    }

    /**
     * 修改课程-测验
     * @param id
     * @param courseTest
     * @return
     */
    @ApiOperation(value = "修改课程-测试", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseTest", value = "课程-测验", required = true, dataType = "CourseTestDto")
    })
    @PutMapping("/course_test/{id}")
    public Result modifyCourseTest(@PathVariable("id") int id,
                                   @RequestBody CourseTestDto courseTest){

        CourseTest courseTestEntity = CourseTestDtoMapper.INSTANCE.dtoToEntity(courseTest);
        courseTestEntity.setId(id);
        courseTestService.modify(courseTestEntity);
        return Result.success();
    }

}
