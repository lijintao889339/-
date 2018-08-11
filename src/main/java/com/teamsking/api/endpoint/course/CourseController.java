package com.teamsking.api.endpoint.course;


import com.github.pagehelper.Page;
import com.teamsking.api.dto.course.AddCourseItemDto;
import com.teamsking.api.dto.course.ChapterSectionDto;
import com.teamsking.api.dto.course.CourseChapterSectionDto;
import com.teamsking.api.dto.course.CourseInsertDto;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.course.Course;
import com.teamsking.domain.service.category.CategoryService;
import com.teamsking.domain.service.course.CourseService;
import com.teamsking.domain.service.course.CourseTeacherService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "课程模板操作接口")
@Slf4j
public class CourseController extends BaseController {

    @Autowired
    CourseService courseService;
    @Autowired
    CourseTeacherService courseTeacherService;

    @Autowired
    CategoryService categoryService;

    @ApiOperation(value = "课程模板列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
        @ApiImplicitParam(name = "pageNo", paramType = "query", value = "页码", required = true, example = "1"),
        @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/courses")
    public Result courseList(@RequestParam int pageNo, @RequestParam int pageSize) {
        return Result.success().addData("pager", warpPage(courseService.list(fixPage(pageNo), fixPage(pageSize))));
    }



    @ApiOperation(value = "添加课程模板", consumes = "application/json")
    @ApiImplicitParams( {
        @ApiImplicitParam(name = "courseInsertDto", value = "课程模板", required = true, dataType = "CourseInsertDto")
    })
    @PostMapping("/course")
    public Result addCourse(@RequestBody CourseInsertDto courseInsertDto) {

        Course course = courseService.save(courseInsertDto);
        return Result.success().addData("course",course);
    }


    @ApiOperation(value = "编辑课程模板前获取课程", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程模板的主键", required = true, dataType = "int")
    })
    @GetMapping("/course/{id}")
    public Result getCourseBeforeEdit(@PathVariable int id){

        return Result.success().addData("course",courseService.getCourseAndTeacherById(id));
    }

    @ApiOperation(value = "编辑课程模板", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseInsertDto", value = "课程模板及其相关信息", required = true, dataType = "CourseInsertDto")
    })
    @PutMapping("/course")
    public Result modify(@RequestBody CourseInsertDto courseInsertDto){

        Course course = courseService.modify(courseInsertDto);
        return Result.success().addData("course",course);
    }


    @ApiOperation(value = "修改课程模板状态", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程模板的主键", required = true)
    })
    @PutMapping("/course/{id}/status")
    public Result modifyCourseSatus(@PathVariable("id") int id){

        courseService.modifyCourseSatusById(id);
        return Result.success();
    }


    @ApiOperation(value = "批量删除课程模板",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "课程模板的主键", required = true)
    })
    @DeleteMapping("/courses/multi_delete")
    public Result removeMultiCourse(@RequestParam Integer[] ids){

        courseService.romoveCourseByIds(ids);
        return Result.success();

    }

    /*@ApiOperation(value = "一级分类下的课程列表",produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "一级分类的主键", required = true ,dataType = "Integer")
    })
    @GetMapping("/first_category/{id}/courses")
    public Result courseOpenList(@PathVariable int id){

        return Result.success().addData("pager",courseService.getCourseListByCategoryId(id));

    }*/


    @ApiOperation(value = "添加课程模板(添加章和节)", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "courseChapterSectionList", value = "课程模板的章和节", required = true),
            @ApiImplicitParam(name = "courseId", value = "课程模板的主键", required = true, dataType = "int")
    })
    @PostMapping("/course/{courseId}/chapter_sections")
    public Result addCourse(@RequestBody CourseChapterSectionDto[] courseChapterSections,
                            @PathVariable int courseId) {

        List<ChapterSectionDto> chapterSectionDtoList = courseService.saveChapterAndSection(courseChapterSections,courseId);
        return Result.success().addData("courseChapterSectionDtos",chapterSectionDtoList);
    }


    @ApiOperation(value = "添加小项内容", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "addCourseItemDto", value = "课程模板的小项内容", required = true, dataType = "AddCourseItemDto[]"),
            @ApiImplicitParam(name = "sectionId", value = "课程模板的小项主键", required = true, dataType = "int")
    })
    @PostMapping("/course_section/{sectionId}/items")
    public Result addCourse(@RequestBody AddCourseItemDto[] addCourseItemDto,
                            @PathVariable int sectionId) {

        courseService.saveCourseSectionItems(addCourseItemDto,sectionId);
        return Result.success();
    }



    @ApiOperation(value = "搜索课程模板列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", paramType = "query", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "courseName", value = "课程模板名称", required = false, dataType = "String")
    })
    @GetMapping("/reaching_courses")
    public Result courseListByReaching(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String courseName) {

        Page page = courseService.listByReaching(fixPage(pageNo), fixPage(pageSize),courseName);

        if (null == page){
            return Result.success().addData("pager",null);
        }else {
            return Result.success().addData("pager", warpPage(page));
        }

    }



}
