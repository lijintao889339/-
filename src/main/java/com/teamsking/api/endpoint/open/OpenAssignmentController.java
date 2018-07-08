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
@Api(tags = "班次作业管理接口")
public class OpenAssignmentController extends BaseController {

    @Autowired
    OpenAssignmentService openAssignmentService;

    /**
     * 获取班次作业管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "班次管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_assignments")
    public Result OpenAssignmentList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenAssignment> openAssignmentList = openAssignmentService.list();
        List<OpenAssignmentDto> openAssignmentDtoList = OpenAssignmentDtoMapper.INSTANCE.entityListToDtoList(openAssignmentList);
        return Result.success().addData("pager",warpPage(openAssignmentDtoList));
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

}
