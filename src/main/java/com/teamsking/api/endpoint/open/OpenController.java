package com.teamsking.api.endpoint.open;


import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenDto;
import com.teamsking.api.dto.open.OpenDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.Open;
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
@Api(tags = "班次管理操作接口")
public class OpenController extends BaseController {

    @Autowired
    OpenService openService;


    @ApiOperation(value = "班次管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/opens")
    public Result courseList(@RequestParam int pageNo, @RequestParam int pageSize) {
        return Result.success().addData("pager", warpPage(openService.list(fixPage(pageNo),fixPage(pageSize))));
    }


    @ApiOperation(value = "添加班次管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "open", value = "班次管理", required = true, dataType = "OpenDto")
    })
    @PostMapping("/open")
    public Result addOpen(@RequestBody OpenDto open){
        Open openEntity = OpenDtoMapper.INSTANCE.dtoToEntity(open);
        openService.save(openEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除班次管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班次管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/open/{id}")
    public Result removeOpen(@PathVariable int id){

        openService.remove(id);
        return Result.success();

    }

    @ApiOperation(value = "修改班次管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "open", value = "班次管理", required = true, dataType = "OpenDto")
    })
    @PutMapping("/open/{id}")
    public Result modifyOpen(@PathVariable int id,
                             @RequestBody OpenDto open){

        Open openEntity = OpenDtoMapper.INSTANCE.dtoToEntity(open);
        openEntity.setId(id);
        openService.modify(openEntity);
        return Result.success();

    }


    @ApiOperation(value = "课程查看(班次列表)", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", paramType = "query", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "courseId", paramType = "query", value = "课程ID", required = true, dataType = "int")
    })
    @GetMapping("/course/{courseId}/opens")
    public Result courseList(@RequestParam int pageNo, @RequestParam int pageSize, @PathVariable int courseId) {
        return Result.success().addData("pager", warpPage(openService.listByCourseId(fixPage(pageNo), fixPage(pageSize),courseId)));
    }


}
