package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenAssignmentDto;
import com.teamsking.api.dto.open.OpenAssignmentDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenAssignment;
import com.teamsking.domain.service.open.OpenAssignmentService;
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
@Api(tags = "班次作业管理接口")
public class OpenAssignmentController extends BaseController {

    @Autowired
    OpenAssignmentService openAssignmentService;

    /**
     * 获取班次作业管理列表
     * @param openId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "班次管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open/{openId}/open_assignments")
    public Result OpenAssignmentList(@PathVariable("openId") int openId,
                                     int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        OpenAssignmentDto openAssignment = new OpenAssignmentDto();
        openAssignment.setOpenId(openId);
        OpenAssignment openAssignmentEntity = OpenAssignmentDtoMapper.INSTANCE.dtoToEntity(openAssignment);
        List<OpenAssignment> openAssignmentList = openAssignmentService.list(openAssignmentEntity);

        List<OpenAssignmentDto> openAssignmentDtoList = OpenAssignmentDtoMapper.INSTANCE.entityListToDtoList(openAssignmentList);
        return Result.success().addData("pager",warpPage(openAssignmentDtoList));
    }

    /**
     * 添加班次作业管理
     * @param openId
     * @param openAssignment
     * @return
     */
    @ApiOperation(value = "添加班次作业管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openAssignment", value = "班次作业", required = true, dataType = "OpenAssignmentDto")
    })
    @PostMapping("/open/{openId}/open_assignment")
    public Result addOpenAssignment(@PathVariable("openId") int openId,
                                    @RequestBody OpenAssignmentDto openAssignment){

        OpenAssignment openAssignmentEntity = OpenAssignmentDtoMapper.INSTANCE.dtoToEntity(openAssignment);
        openAssignmentEntity.setOpenId(openId);
        openAssignmentService.save(openAssignmentEntity);
        return Result.success();
    }

    /**
     * 删除班次作业管理
     * @param openId
     * @param id
     * @param openAssignment
     * @return
     */
    @ApiOperation(value = "删除班次管理", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openAssignment", value = "班次作业", required = true, dataType = "OpenAssignmentDto")
    })
    @DeleteMapping("/open/{openId}/open_assignment/{id}")
    public Result removeOpenAssignment(@PathVariable("openId") int openId,
                                       @PathVariable("id") int id,
                                       @RequestBody OpenAssignmentDto openAssignment) {

        OpenAssignment openAssignmentEntity = OpenAssignmentDtoMapper.INSTANCE.dtoToEntity(openAssignment);
        openAssignmentEntity.setOpenId(openId);
        openAssignmentEntity.setId(id);
        openAssignmentService.remove(openAssignmentEntity);
        return Result.success();
    }

    /**
     * 修改班次作业管理
     * @param openId
     * @param id
     * @param openAssignment
     * @return
     */
    @ApiOperation(value = "修改班次作业管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openAssignment", value = "班次作业", required = true, dataType = "OpenAssignmentDto")
    })
    @PutMapping("/open/{openId}/open_assignment/{id}")
    public Result modify(@PathVariable("openId") int openId,
                         @PathVariable("id") int id,
                         @RequestBody OpenAssignmentDto openAssignment){

        OpenAssignment openAssignmentEntity = OpenAssignmentDtoMapper.INSTANCE.dtoToEntity(openAssignment);
        openAssignmentEntity.setOpenId(openId);
        openAssignmentEntity.setId(id);
        openAssignmentService.modify(openAssignmentEntity);
        return Result.success();
    }

}
