package com.teamsking.api.endpoint.open;


import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenActivityDto;
import com.teamsking.api.dto.open.OpenActivityDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenActivity;
import com.teamsking.domain.service.open.OpenActivityService;
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
@Api(tags = "班次-活动操作接口")
public class OpenActivityController extends BaseController {


    @Autowired
    OpenActivityService openActivityService;


    @ApiOperation(value = "班次-活动管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open/{openId}/open_activities")
    public Result openActivityList(@PathVariable int openId,
                                   int pageNo,int pageSize){
        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        OpenActivityDto openActivity = new OpenActivityDto();
        openActivity.setOpenId(openId);

        OpenActivity openActivityEntity = OpenActivityDtoMapper.INSTANCE.dtoToEntity(openActivity);
        List<OpenActivity> openActivityList = openActivityService.list(openActivityEntity);
        List<OpenActivityDto> openActivityDtoList = OpenActivityDtoMapper.INSTANCE.entityListToDtoList(openActivityList);

        return Result.success().addData("pager",warpPage(openActivityDtoList));

    }


    @ApiOperation(value = "添加班次-活动管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openActivity", value = "班次活动管理", required = true, dataType = "OpenActivityDto")
    })
    @PostMapping("/open/{openId}/open_activity")
    public Result addOpenActivity(@PathVariable int openId,
                                  @RequestBody OpenActivityDto openActivity){

        OpenActivity openActivityEntity = OpenActivityDtoMapper.INSTANCE.dtoToEntity(openActivity);
        openActivityEntity.setOpenId(openId);
        openActivityService.save(openActivityEntity);

        return Result.success();

    }



    @ApiOperation(value = "删除班次-活动管理",consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openActivity",value = "班次活动管理",required = true,dataType = "OpenActivityDto")
    })
    @DeleteMapping("/open/{openId}/open_activity/{id}")
    public Result removeOpenActivity(@PathVariable int openId,
                                     @PathVariable int id,
                                     @RequestBody OpenActivityDto openActivity){

        OpenActivity openActivityEntity = OpenActivityDtoMapper.INSTANCE.dtoToEntity(openActivity);
        openActivityEntity.setOpenId(openId);
        openActivityEntity.setId(id);
        openActivityService.remove(openActivityEntity);

        return Result.success();

    }


    @ApiOperation(value = "修改班次-活动管理",consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openActivity",value = "班次活动管理",required = true,dataType = "OpenActivityDto")
    })
    @PutMapping("/open/{openId}/open_activity/{id}")
    public Result modifyOpenActivity(@PathVariable int openId,
                                     @PathVariable int id,
                                     @RequestBody OpenActivityDto openActivity){

        OpenActivity openActivityEntity = OpenActivityDtoMapper.INSTANCE.dtoToEntity(openActivity);
        openActivityEntity.setOpenId(openId);
        openActivityEntity.setId(id);
        openActivityService.modify(openActivityEntity);

        return Result.success();

    }



}
