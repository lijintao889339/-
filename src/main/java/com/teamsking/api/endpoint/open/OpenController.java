package com.teamsking.api.endpoint.open;


import com.github.pagehelper.Page;
import com.teamsking.api.dto.course.UserStudentDto;
import com.teamsking.api.dto.open.*;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.Open;
import com.teamsking.domain.service.category.CategoryService;
import com.teamsking.domain.service.course.CourseService;
import com.teamsking.domain.service.open.OpenService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "班课管理操作接口")
public class OpenController extends BaseController {

    @Autowired
    OpenService openService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CourseService courseService;


    @ApiOperation(value = "班课管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/opens")
    public Result courseList(@RequestParam int pageNo, @RequestParam int pageSize) {
        return Result.success().addData("pager", warpPage(openService.list(fixPage(pageNo),fixPage(pageSize))));
    }


    /*@ApiOperation(value = "添加班课管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "open", value = "班课管理", required = true, dataType = "OpenDto")
    })
    @PostMapping("/open")
    public Result copyOpen(@RequestBody OpenDto open){
        Open openEntity = OpenDtoMapper.INSTANCE.dtoToEntity(open);
        openService.save(openEntity);
        return Result.success();

    }*/


    @ApiOperation(value = "复制班课", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openCopyDto", value = "复制班课", required = true, dataType = "OpenCopyDto"),
            @ApiImplicitParam(name = "id", value = "班课的主键", required = true, dataType = "Integer")

    })
    @PostMapping("/copy/open/{id}")
    public Result copyOpen(@RequestBody OpenCopyDto openCopyDto,
                           @PathVariable int id){

        openService.copyOpen(openCopyDto,id);
        return Result.success();

    }




    @ApiOperation(value = "删除班课管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班课管理", paramType = "query", required = true, dataType = "Integer")
    })
    @DeleteMapping("/open/{id}")
    public Result removeOpen(@PathVariable int id){

        openService.remove(id);
        return Result.success();

    }

    @ApiOperation(value = "修改班课管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "open", value = "班课管理", required = true, dataType = "OpenDto")
    })
    @PutMapping("/open/{id}")
    public Result modifyOpen(@PathVariable int id,
                             @RequestBody OpenDto open){

        Open openEntity = OpenDtoMapper.INSTANCE.dtoToEntity(open);
        openEntity.setId(id);
        openService.modify(openEntity);
        return Result.success();

    }


    @ApiOperation(value = "课程查看(班课列表)", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", paramType = "query", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "courseId", paramType = "query", value = "课程ID", required = true, dataType = "int")
    })
    @GetMapping("/course/{courseId}/opens")
    public Result courseList(@RequestParam int pageNo, @RequestParam int pageSize, @PathVariable int courseId) {
        return Result.success().addData("pager", warpPage(openService.listByCourseId(fixPage(pageNo), fixPage(pageSize),courseId)));
    }


    @ApiOperation(value = "修改班课状态", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "班课的主键", paramType = "query", required = true, dataType = "int")
    })
    @PutMapping("/open/{id}/status")
    public Result modifyOpenPublishFlag(@PathVariable("id") int id){

        openService.modifyPublishFlagById(id);
        return Result.success();
    }


    @ApiOperation(value = "创建班课", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "addOpenDto", value = "创建班课", required = true, dataType = "AddOpenDto")
    })
    @PostMapping("/add_open")
    public Result addOpen(@RequestBody AddOpenDto addOpenDto){

        openService.addOpen(addOpenDto);

        return Result.success();

    }


    @ApiOperation(value = "创建班课教学模式", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "addOpenDto", value = "创建班课教学模式", required = true, dataType = "AddOpenDto")
    })
    @PutMapping("/open_teaching_model")
    public Result addOpenTeachingModel(@RequestBody AddOpenDto addOpenDto){

        openService.addTeachingModel(addOpenDto);

        return Result.success();

    }



//    @ApiOperation(value = "查询班课信息", consumes= "application/json")
//    @ApiImplicitParams( {
//            @ApiImplicitParam(name = "id", value = "班课主键", required = true, dataType = "Integer")
//    })
//    @GetMapping("/edit_open/{id}")
//    public Result editOpen(@PathVariable int id){
//
//        return Result.success().addData("pager", (openService.editOpen(id)));
//
//    }


//    @ApiOperation(value = "编辑保存班课", consumes= "application/json")
//    @ApiImplicitParams( {
//            @ApiImplicitParam(name = "editOpenDto", value = "编辑保存班课", required = true, dataType = "EditOpenDto")
//    })
//    @PutMapping("/edit_preservation_open")
//    public Result editPreservationOpen(@RequestBody EditOpenDto editOpenDto){
//
//        openService.editPreservationOpen(editOpenDto);
//
//        return Result.success();
//
//    }



      @ApiOperation(value = "编辑班课前获取班课信息", consumes= "application/json")
      @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班课主键", required = true, dataType = "Integer")
      })
      @GetMapping("/edit_open/{id}")
      public Result getEditOpen(@PathVariable int id){

          return Result.success().addData("open",openService.getOpenAndTeacherById(id));

      }


    @ApiOperation(value = "编辑保存班课信息", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openEditInsertDto", value = "编辑保存班课", required = true, dataType = "OpenEditInsertDto")
    })
    @PutMapping("/edit_preservation_open")
    public Result editPreservationOpen(@RequestBody OpenEditInsertDto openEditInsertDto){

        openService.editPreservationOpen(openEditInsertDto);

        return Result.success();

    }



    @ApiOperation(value = "搜索框根据班课名称模糊查询列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", paramType = "query", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "openName", value = "班课名称", required = true, dataType = "String")
    })
    @GetMapping("/searching_opens")
    public Result getOpenListReaching(@RequestParam int pageNo,
                                      @RequestParam int pageSize,
                                      @RequestParam String openName){

        Page page = openService.listByReaching(fixPage(pageNo), fixPage(pageSize),openName);

        if (null == page){
            return Result.success().addData("page",null);
        }else {
            return Result.success().addData("pager", warpPage(page));
        }
    }



    @ApiOperation(value = "根据课程模板创建班课前查询", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "courseId", value = "课程模板主键", required = true, dataType = "Integer")
    })
    @GetMapping("/open_course/{courseId}")
    public Result getOpenInfoBeforeCreate(@PathVariable int courseId){

        AddOpenByCourseDto openDto = openService.getOpenInfoByCourseId(courseId);
        return Result.success().addData("openDto",openDto);
    }



    @ApiOperation(value = "根据课程模板创建班课", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "addOpenCourseDto", value = "班课信息", required = true, dataType = "AddOpenCourseDto"),
            @ApiImplicitParam(name = "courseId", value = "课程的主键", required = true, dataType = "int")
    })
    @PostMapping("/add_open_course/{courseId}")
    public Result addOpenByCourse(@RequestBody AddOpenCourseDto addOpenCourseDto,
                                  @PathVariable int courseId){

        openService.saveOpenByCourse(addOpenCourseDto,courseId);
        return Result.success();

    }


    @ApiOperation(value = "班课下的用户列表", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班课的主键", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_users/{id}")
    public Result getOpenUser(@PathVariable int id, @RequestParam int pageNo, @RequestParam int pageSize){

        List<UserStudentDto> userStudentDtos = openService.getOpenUserById(id,fixPage(pageNo),fixPage(pageSize));
        return Result.success().addData("pager",warpPage(userStudentDtos));
    }


    @ApiOperation(value = "根据条件搜索所有班课下的用户列表", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班课的主键", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "realName", value = "真是姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "studentNo", value = "学号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/searching_open_users/{id}")
    public Result getOpenUserBySearching(@PathVariable int id,@RequestParam String realName, @RequestParam String studentNo,
                                         @RequestParam int pageNo, @RequestParam int pageSize){

        Page page = openService.getOpenUserBySearching(id,realName,studentNo,fixPage(pageNo),fixPage(pageSize));
        if (null == page){
            return Result.success().addData("pager",null);
        }else {
            return Result.success().addData("pager", warpPage(page));
        }
    }


    @ApiOperation(value = "批量删除班课下的用户信息", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "ids", value = "班课和用户关系的主键", required = true, dataType = "Integer[]")
    })
    @DeleteMapping("/open_users/multi_delete")
    public Result removeMultiOpenUser(@RequestParam Integer[] ids){

        openService.removeMultiOpenUserByIds(ids);
        return Result.success();
    }


    @ApiOperation(value = "获取班级成员的学习授权", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班课的主键", required = true, dataType = "Integer")
    })
    @GetMapping("/open_study_authorize/{id}")
    public Result getOpenStudyAuthorize(@PathVariable int id){

        OpenStudyAuthorizeDto openStudyAuthorizeDto = openService.getOpenAuthorizeById(id);
        return Result.success().addData("openStudyAuthorizeDto",openStudyAuthorizeDto);
    }


    @ApiOperation(value = "修改班级成员的学习授权", consumes = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班课的主键", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "openStudyAuthorizeDto", value = "班课的学习权限信息", required = true, dataType = "OpenStudyAuthorizeDto")
    })
    @PutMapping("/open_study_authorize/{id}")
    public Result editOpenStudyAuthorize(@PathVariable int id,
                                         @RequestBody OpenStudyAuthorizeDto openStudyAuthorizeDto){

        openService.deitOpenAuthorizeById(id,openStudyAuthorizeDto);
        return Result.success();
    }

}
