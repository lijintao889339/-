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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "班次管理操作接口")
public class OpenController extends BaseController {

    @Autowired
    OpenService openService;


    @ApiOperation(value = "班次管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/school/{schoolId}/opens/course/{courseId}")
    public Result openList(@PathVariable int schoolId,
                           @PathVariable int courseId,
                           int pageNo,int pageSize){
        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        OpenDto open = new OpenDto();
        open.setCourseId(courseId);
        open.setSchoolId(schoolId);
        Open openEntity = OpenDtoMapper.INSTANCE.dtoToEntity(open);
        List<Open> openList = openService.list(openEntity);
        List<OpenDto> openDtoList = OpenDtoMapper.INSTANCE.entityListToDtoList(openList);
        return Result.success().addData("pager", warpPage(openDtoList));

    }


    @ApiOperation(value = "添加班次管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "open", value = "班次管理", required = true, dataType = "OpenDto")
    })
    @PostMapping("/school/{schoolId}/open/course/{courseId}")
    public Result addOpen(@PathVariable int schoolId,
                          @PathVariable int courseId,
                          @RequestBody OpenDto open){
        Open openEntity = OpenDtoMapper.INSTANCE.dtoToEntity(open);
        openEntity.setSchoolId(schoolId);
        openEntity.setCourseId(courseId);
        openService.save(openEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除班次管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "open", value = "班次管理", required = true, dataType = "OpenDto")
    })
    @DeleteMapping("/school/{schoolId}/open/{id}/course/{courseId}")
    public Result removeOpen(@PathVariable int id,
                             @PathVariable int schoolId,
                             @PathVariable int courseId,
                             @RequestBody OpenDto open){

        Open openEntity = OpenDtoMapper.INSTANCE.dtoToEntity(open);
        openEntity.setSchoolId(schoolId);
        openEntity.setCourseId(courseId);
        openEntity.setId(id);
        openService.remove(openEntity);
        return Result.success();

    }

    @ApiOperation(value = "修改班次管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "open", value = "班次管理", required = true, dataType = "OpenDto")
    })
    @PutMapping("/school/{schoolId}/open/{id}/course/{courseId}")
    public Result modifyOpen(@PathVariable int id,
                             @PathVariable int schoolId,
                             @PathVariable int courseId,
                             @RequestBody OpenDto open){

        Open openEntity = OpenDtoMapper.INSTANCE.dtoToEntity(open);
        openEntity.setSchoolId(schoolId);
        openEntity.setCourseId(courseId);
        openEntity.setId(id);
        openService.modify(openEntity);
        return Result.success();

    }




}
