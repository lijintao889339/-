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
@Api(tags = "班次-活动操作接口")
public class OpenActivityController extends BaseController {


    @Autowired
    OpenActivityService openActivityService;


    /*@ApiOperation(value = "获取班课下进行中的活动", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int")
            //@ApiImplicitParam(name = "isPublish", value = "是否发放", required = true, dataType = "Boolean")
    })
    @GetMapping("/open_activities_starting/{openId}")
    public Result openActivityList(@PathVariable int openId *//*,@PathVariable Boolean isPublish*//*){


        List<OpenActivityDto> openActivityDtos = openActivityService.listByStarting(openId);
        return Result.success().addData("OpenActivityList",openActivityDtos);

    }*/


    @ApiOperation(value = "添加班次-活动管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openActivity", value = "班次活动管理", required = true, dataType = "OpenActivityDto")
    })
    @PostMapping("/open_activity")
    public Result addOpenActivity(@RequestBody OpenActivityDto openActivity){

        OpenActivity openActivityEntity = OpenActivityDtoMapper.INSTANCE.dtoToEntity(openActivity);
        openActivityService.save(openActivityEntity);

        return Result.success();

    }



    @ApiOperation(value = "删除班次-活动管理",consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "班次活动管理",required = true,dataType = "Integer")
    })
    @DeleteMapping("/open_activity/{id}")
    public Result removeOpenActivity(@PathVariable int id){

        openActivityService.remove(id);

        return Result.success();

    }


    @ApiOperation(value = "修改班次-活动管理",consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openActivity",value = "班次活动管理",required = true,dataType = "OpenActivityDto")
    })
    @PutMapping("/open_activity/{id}")
    public Result modifyOpenActivity(@PathVariable int id,
                                     @RequestBody OpenActivityDto openActivity){

        OpenActivity openActivityEntity = OpenActivityDtoMapper.INSTANCE.dtoToEntity(openActivity);
        openActivityEntity.setId(id);
        openActivityService.modify(openActivityEntity);

        return Result.success();

    }



}
