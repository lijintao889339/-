package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenAssistantDto;
import com.teamsking.api.dto.open.OpenAssistantDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenAssistant;
import com.teamsking.domain.service.open.OpenAssistantService;
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
@Api(tags = "班次-助教权限管理操作接口")
public class OpenAssistantController extends BaseController {

    @Autowired
    OpenAssistantService openAssistantService;


    @ApiOperation(value = "班次-助教权限管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open/{openId}/open_assistants")
    public Result openAssistantList(@PathVariable int openId,
                                    int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        OpenAssistantDto openAssistant = new OpenAssistantDto();
        openAssistant.setOpenId(openId);

        OpenAssistant openAssistantEntity = OpenAssistantDtoMapper.INSTANCE.dtoToEntity(openAssistant);
        List<OpenAssistant> openAssistantList = openAssistantService.list(openAssistantEntity);
        List<OpenAssistantDto> openAssistantDtoList = OpenAssistantDtoMapper.INSTANCE.entityListToDtoList(openAssistantList);
        return Result.success().addData("pager",warpPage(openAssistantDtoList));

    }



    @ApiOperation(value = "添加班次-助教权限管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openAssistant", value = "班次助教权限管理", required = true, dataType = "OpenAssistantDto")
    })
    @PostMapping("/open/{openId}/open_assistant")
    public Result addOpenAssistant(@PathVariable int openId,
                                   @RequestBody OpenAssistantDto openAssistant){

        OpenAssistant openAssistantEntity = OpenAssistantDtoMapper.INSTANCE.dtoToEntity(openAssistant);
        openAssistantEntity.setOpenId(openId);
        openAssistantService.save(openAssistantEntity);

        return Result.success();

    }


    @ApiOperation(value = "删除班次-助教权限管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openAssistant", value = "班次助教权限管理", required = true, dataType = "OpenAssistantDto")
    })
    @DeleteMapping("/open/{openId}/open_assistant/{id}")
    public Result removeOpenAssistant(@PathVariable int openId,
                                      @PathVariable int id,
                                      @RequestBody OpenAssistantDto openAssistant){

        OpenAssistant openAssistantEntity = OpenAssistantDtoMapper.INSTANCE.dtoToEntity(openAssistant);
        openAssistantEntity.setOpenId(openId);
        openAssistantEntity.setId(id);
        openAssistantService.remove(openAssistantEntity);

        return Result.success();


    }



    @ApiOperation(value = "修改班次-助教权限管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openAssistant", value = "班次助教权限管理", required = true, dataType = "OpenAssistantDto")
    })
    @PutMapping("/open/{openId}/open_assistant/{id}")
    public Result modifyOpenAssistant(@PathVariable int openId,
                                      @PathVariable int id,
                                      @RequestBody OpenAssistantDto openAssistant){
        OpenAssistant openAssistantEntity = OpenAssistantDtoMapper.INSTANCE.dtoToEntity(openAssistant);
        openAssistantEntity.setOpenId(openId);
        openAssistantEntity.setId(id);
        openAssistantService.modify(openAssistantEntity);
        return Result.success();

    }


}
