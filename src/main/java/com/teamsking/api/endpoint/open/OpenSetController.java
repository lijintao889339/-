package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenSetDto;
import com.teamsking.api.dto.open.OpenSetDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenSet;
import com.teamsking.domain.service.open.OpenSetService;
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
@Api(tags = "班次-成绩权重操作接口")
public class OpenSetController extends BaseController {

    @Autowired
    OpenSetService openSetService;


    @ApiOperation(value = "班次-成绩权重", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "班课主键", required = true, dataType = "int")
    })
    @GetMapping("/open_set/{openId}")
    public Result openSet(@PathVariable int openId){

        OpenSet openSet = openSetService.getOpenSet(openId);
        OpenSetDto openSetDto = OpenSetDtoMapper.INSTANCE.entityToDto(openSet);
        return Result.success().addData("openSet", openSetDto);

    }


//    @ApiOperation(value = "根据openId查询成绩", consumes= "application/json")
//    @ApiImplicitParams( {
//            @ApiImplicitParam(name = "openId", value = "班课主键", required = true, dataType = "Integer")
//    })
//    @GetMapping("/query_open_set/{openId}")
//    public Result queryOpenSet(@PathVariable int openId){
//
//        return Result.success().addData("pager", openSetService.queryOpenSet(openId));
//
//    }



    @ApiOperation(value = "添加班次-成绩管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openSet", value = "班次成绩管理", required = true, dataType = "OpenSetDto")
    })
    @PostMapping("/open_set")
    public Result addOpenSet(@RequestBody OpenSetDto openSet){

        OpenSet openSetEntity = OpenSetDtoMapper.INSTANCE.dtoToEntity(openSet);
        openSetService.save(openSetEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除班次-成绩管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班次成绩管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/open_set/{id}")
    public Result removeOpenSet(@PathVariable int id){

        openSetService.remove(id);
        return Result.success();

    }


    @ApiOperation(value = "修改班次-成绩管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openSet", value = "班次成绩管理", required = true, dataType = "OpenSetDto")
    })
    @PutMapping("open_set/{id}")
    public Result modifyOpenSet(@PathVariable int id,
                                @RequestBody OpenSetDto openSet){

        OpenSet openSetEntity = OpenSetDtoMapper.INSTANCE.dtoToEntity(openSet);
        openSetEntity.setId(id);
        openSetService.modify(openSetEntity);

        return Result.success();

    }




}
