package com.teamsking.api.endpoint.open;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.AddOpenAssignmentDto;
import com.teamsking.api.dto.open.OpenAssignmentDto;
import com.teamsking.api.dto.open.OpenAssignmentDtoMapper;
import com.teamsking.api.dto.open.OpenAssignmentNameDto;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenAssignment;
import com.teamsking.domain.service.open.OpenAssignmentService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
*@author linhao
*/
@RestController
@Slf4j
@Api(tags = "班次作业管理接口")
public class OpenAssignmentController extends BaseController {

    @Autowired
    OpenAssignmentService openAssignmentService;

    /**
     * 获取作业下的学生提交题信息列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取作业下的学生提交题信息列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "id", value = "作业的主键", required = true, dataType = "int"),
    })
    @GetMapping("/assignment_quiz_students/{id}")
    public Result OpenAssignmentList(@RequestParam int pageNo, @RequestParam int pageSize, @PathVariable int id){

        Page page = openAssignmentService.list(fixPage(pageNo),fixPage(pageSize),id);
        if (null == page){
            return Result.success().addData("pager",null);
        }else {
            return Result.success().addData("pager",warpPage(page));
        }

    }


    @ApiOperation(value = "根据班课id查询作业信息", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班课id", required = true, dataType = "Integer")
    })
    @GetMapping("/query_open_assignment/{openId}")
    public Result queryVideo(@PathVariable int openId){

        return Result.success().addData("list",openAssignmentService.query(openId));

    }



    /**
     * 添加班次作业管理
     * @param openAssignment
     * @return
     */
    @ApiOperation(value = "添加班次作业管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openAssignment", value = "班次作业", required = true, dataType = "OpenAssignmentDto")
    })
    @PostMapping("/open_assignment")
    public Result addOpenAssignment(@RequestBody OpenAssignmentDto openAssignment){

        OpenAssignment openAssignmentEntity = OpenAssignmentDtoMapper.INSTANCE.dtoToEntity(openAssignment);
        openAssignmentService.save(openAssignmentEntity);
        return Result.success();
    }

    /**
     * 删除班次作业管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除班次管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "班次作业的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/open_assignment/{id}")
    public Result removeOpenAssignment(@PathVariable("id") int id) {

        openAssignmentService.remove(id);
        return Result.success();
    }

    /**
     * 修改班次作业管理
     * @param id
     * @param openAssignment
     * @return
     */
    @ApiOperation(value = "修改班次作业管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openAssignment", value = "班次作业", required = true, dataType = "OpenAssignmentDto")
    })
    @PutMapping("/open_assignment/{id}")
    public Result modify(@PathVariable("id") int id,
                         @RequestBody OpenAssignmentDto openAssignment){

        OpenAssignment openAssignmentEntity = OpenAssignmentDtoMapper.INSTANCE.dtoToEntity(openAssignment);
        openAssignmentEntity.setId(id);
        openAssignmentService.modify(openAssignmentEntity);
        return Result.success();
    }



    @ApiOperation(value = "根据班课id添加作业", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addOpenAssignmentDto", value = "班次作业", required = true, dataType = "AddOpenAssignmentDto"),
            @ApiImplicitParam(name = "openId", value = "班课主键", required = true, dataType = "Integer")
    })
    @PostMapping("/add_open/{openId}/assignment")
    public Result addOpenAssignmentByOpenId(@RequestBody AddOpenAssignmentDto addOpenAssignmentDto,
                                    @PathVariable int openId){

        openAssignmentService.addOpenAssignment(addOpenAssignmentDto,openId);

        return Result.success();

    }




    @ApiOperation(value = "根据节id添加作业", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addOpenAssignmentDto", value = "班次作业", required = true, dataType = "AddOpenAssignmentDto"),
            @ApiImplicitParam(name = "sectionId", value = "节id", required = true, dataType = "Integer")
    })
    @PostMapping("/add_open_assignment/{sectionId}")
    public Result addOpenAssignmentBySectionId(@RequestBody AddOpenAssignmentDto addOpenAssignmentDto,
                                               @PathVariable int sectionId){

        openAssignmentService.addOpenAssignmentBySectionId(addOpenAssignmentDto,sectionId);

        return Result.success();

    }




    @ApiOperation(value = "根据班课id获取作业列表", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openId", value = "班课的id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_assignment/{openId}")
    public Result getOpenAssignmentByOpenIdList(@PathVariable int openId, @RequestParam int pageNo, @RequestParam int pageSize){

        Page page = openAssignmentService.getOpenAssignmentListByOpenId(openId,fixPage(pageNo),fixPage(pageSize));
        if (null == page){
            return Result.success().addData("pager",null);
        }else {
            return Result.success().addData("pager", warpPage(page));
        }
    }

}
