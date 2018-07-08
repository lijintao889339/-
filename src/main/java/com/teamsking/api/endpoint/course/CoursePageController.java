package com.teamsking.api.endpoint.course;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.course.CoursePageDto;
import com.teamsking.api.dto.course.CoursePageDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.course.CoursePage;
import com.teamsking.domain.service.course.CoursePageService;
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
@Api(tags = "课程-章节页面管理接口")
public class CoursePageController extends BaseController {

    @Autowired
    CoursePageService coursePageService;

    /**
     * 获取课程-章节页面管理的列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程-章节页面列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course_pages")
    public Result coursePageList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<CoursePage> coursePageList = coursePageService.list();
        List<CoursePageDto> coursePageDtoList = CoursePageDtoMapper.INSTANCE.entityListToDtoList(coursePageList);
        return Result.success().addData("pager",warpPage(coursePageDtoList));
    }

    /**
     * 添加课程-章节页面
     * @param coursePage
     * @return
     */
    @ApiOperation(value = "添加课程-章节页面", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coursePage", value = "课程-章节页面", required = true, dataType = "CoursePageDto")
    })
    @PostMapping("/course_page")
    public Result addCoursePage(@RequestBody CoursePageDto coursePage){

        CoursePage  coursePageEntity = CoursePageDtoMapper.INSTANCE.dtoToEntity(coursePage);
        coursePageService.save(coursePageEntity);
        return Result.success();
    }

    /**
     * 删除课程-章节页面
     * @param id
     * @return
     */
    @ApiOperation(value = "删除课程-章节页面", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程-章节页面的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/course_page/{id}")
    public Result removeCoursePage(@PathVariable("id") int id){

        coursePageService.remove(id);
        return Result.success();
    }

    /**
     * 修改课程-章节页面
     * @param id
     * @param coursePage
     * @return
     */
    @ApiOperation(value = "修改课程-章节页面", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coursePage", value = "课程-章节页面", required = true, dataType = "CoursePageDto")
    })
    @PutMapping("/course_page/{id}")
    public Result modifyCoursePage(@PathVariable("id") int id,
                                   @RequestBody CoursePageDto coursePage){

        CoursePage  coursePageEntity = CoursePageDtoMapper.INSTANCE.dtoToEntity(coursePage);
        coursePageEntity.setId(id);
        coursePageService.modify(coursePageEntity);
        return Result.success();
    }

}
