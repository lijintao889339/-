package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenGroupDto;
import com.teamsking.api.dto.open.OpenGroupDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenGroup;
import com.teamsking.domain.service.open.OpenGroupService;
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


@RestController
@Slf4j
@Api(tags = "班次-学生组操作接口")
public class OpenGroupController extends BaseController {


    @Autowired
    OpenGroupService openGroupService;


    @ApiOperation(value = "班次-学生组列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_groups")
    public Result openGroupList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenGroup> openGroupList = openGroupService.list();
        List<OpenGroupDto> openGroupDtoList = OpenGroupDtoMapper.INSTANCE.entityListToDtoList(openGroupList);
        return Result.success().addData("pager",warpPage(openGroupDtoList));

    }



    @ApiOperation(value = "添加班次-学生组", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openGroup", value = "班次学生组", required = true, dataType = "OpenGroupDto")
    })
    @PostMapping("/open_group")
    public Result addOpenGroup(@RequestBody OpenGroupDto openGroup){

        OpenGroup openGroupEntity = OpenGroupDtoMapper.INSTANCE.dtoToEntity(openGroup);
        openGroupService.save(openGroupEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除班次-学生组", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班次学生组", required = true, dataType = "Integer")
    })
    @DeleteMapping("/open_group/{id}")
    public Result removeOpenGroup(@PathVariable int id){

        openGroupService.remove(id);
        return Result.success();

    }


    @ApiOperation(value = "修改班次-学生组", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openGroup", value = "班次学生组", required = true, dataType = "OpenGroupDto")
    })
    @PutMapping("/open_group/{id}")
    public Result modifyOpenGroup(@PathVariable int id,
                                  @RequestBody OpenGroupDto openGroup){
        OpenGroup openGroupEntity = OpenGroupDtoMapper.INSTANCE.dtoToEntity(openGroup);
        openGroupEntity.setId(id);
        openGroupService.modify(openGroupEntity);
        return Result.success();

    }


}
