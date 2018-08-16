package com.teamsking.api.endpoint.open;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenGroupDto;
import com.teamsking.api.dto.open.OpenGroupDtoMapper;
import com.teamsking.api.dto.open.OpenGroupNameDto;
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
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@Api(tags = "班次-学生组操作接口")
public class OpenGroupController extends BaseController {


    @Autowired
    OpenGroupService openGroupService;


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


    @ApiOperation(value = "班次下的班组列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int")
    })
    @GetMapping("/open_groups/{openId}")
    public Result openGroupList(@RequestParam int pageNo, @RequestParam int pageSize,
                                @PathVariable int openId){

        return Result.success().addData("pager",warpPage(openGroupService.list(fixPage(pageNo),fixPage(pageSize),openId)));
    }



    @ApiOperation(value = "根据条件搜索班次下的班组列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int"),
            @ApiImplicitParam(name = "groupName", value = "班组名称", required = true, dataType = "String")
    })
    @GetMapping("/searching_open_groups/{openId}")
    public Result searchingOpenGroupList(@RequestParam int pageNo, @RequestParam int pageSize,
                                @PathVariable int openId, @RequestParam String groupName){

        Page page = openGroupService.searchingOpenGroupByOpenId(fixPage(pageNo),fixPage(pageSize),openId,groupName);
        if (null == page){
            return Result.success().addData("pager",null);
        }else {
            return Result.success().addData("pager",warpPage(page));
        }

    }



    @ApiOperation(value = "班次下的班组名称列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int")
    })
    @GetMapping("/open_group_names/{openId}")
    public Result getOpenGroupName(@PathVariable int openId){

        List<OpenGroup> openGroupList = openGroupService.getGroupNameByOpenId(openId);
        List<OpenGroupNameDto> openGroupNameDtoList = OpenGroupDtoMapper.INSTANCE.entityListToGroupNameDtoList(openGroupList);
        return Result.success().addData("openGroupNameDtoList",openGroupNameDtoList);
    }



    @ApiOperation(value = "给辅导老师添加班组", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userTeacherId", value = "辅导老师的主键", required = true, dataType = "int"),
            @ApiImplicitParam(name = "openGroupNameDto", value = "班组信息", required = true, dataType = "OpenGroupNameDto")
    })
    @PostMapping("/open_group/{userTeacherId}")
    public Result saveOpenGroup(@PathVariable int userTeacherId, @RequestBody OpenGroupNameDto openGroupNameDto){

        openGroupService.saveOpenGroupByUserTeacherId(userTeacherId, openGroupNameDto);
        return Result.success();
    }


    @ApiOperation(value = "批量删除班课下班组", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "班组的主键", required = true, dataType = "Integer[]")
    })
    @DeleteMapping("/open_groups/multi_delete")
    public Result removeMultiOpenGroup(@RequestParam Integer[] ids){

        openGroupService.removeMultiOpenGroupByIds(ids);
        return Result.success();
    }


}
