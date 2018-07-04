package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenVoteOptionDto;
import com.teamsking.api.dto.open.OpenVoteOptionDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenVoteOption;
import com.teamsking.domain.service.open.OpenVoteOptionService;
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
@Api(tags = "班次-投票选项管理操作接口")
public class OpenVoteOptionController extends BaseController {

    @Autowired
    OpenVoteOptionService openVoteOptionService;

    /**
     * 获取班次-投票选项管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "班次-投票选项管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_vote_options")
    public Result openVoteOptionList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenVoteOption> openVoteOptionList = openVoteOptionService.list();
        List<OpenVoteOptionDto> openVoteOptionDtoList = OpenVoteOptionDtoMapper.INSTANCE.entityListToDtoList(openVoteOptionList);
        return Result.success().addData("pager",warpPage(openVoteOptionDtoList));
    }

    /**
     * 添加班次-投票选项管理
     * @param openVoteOption
     * @return
     */
    @ApiOperation(value = "添加班次-投票选项管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openVoteOption", value = "班次-投票选项", required = true, dataType = "OpenVoteOptionDto")
    })
    @PostMapping("open_vote_option")
    public Result addOpenVoteOption(@RequestBody OpenVoteOptionDto openVoteOption){

        OpenVoteOption openVoteOptionEntity = OpenVoteOptionDtoMapper.INSTANCE.dtoToEntity(openVoteOption);
        openVoteOptionService.save(openVoteOptionEntity);
        return Result.success();
    }

    /**
     * 删除班次-投票选项管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除班次-投票选项管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "班次-投票选项的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/open_vote_option/{id}")
    public Result removeOpenVoteOption(@PathVariable("id") int id){

        openVoteOptionService.remove(id);
        return Result.success();
    }

    /**
     * 修改班次-投票选项管理
     * @param id
     * @param openVoteOption
     * @return
     */
    @ApiOperation(value = "修改班次-投票选项管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openVoteOption", value = "班次-投票选项", required = true, dataType = "OpenVoteOptionDto")
    })
    @PutMapping("/open_vote_option/{id}")
    public Result modifyOpenVoteOption(@PathVariable("id") int id,
                                       @RequestBody OpenVoteOptionDto openVoteOption){

        OpenVoteOption openVoteOptionEntity = OpenVoteOptionDtoMapper.INSTANCE.dtoToEntity(openVoteOption);
        openVoteOptionEntity.setId(id);
        openVoteOptionService.modify(openVoteOptionEntity);
        return Result.success();
    }

}
