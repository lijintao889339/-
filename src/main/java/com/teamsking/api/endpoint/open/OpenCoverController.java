package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenCoverDto;
import com.teamsking.api.dto.open.OpenCoverDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenCover;
import com.teamsking.domain.service.open.OpenCoverService;
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
@Api(tags = "班次-封面管理接口")
public class OpenCoverController extends BaseController {

    @Autowired
    OpenCoverService openCoverService;

    /**
     * 获取班次-封面列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "班次-封面列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_covers")
    public Result openCoverList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenCover> openCoverList = openCoverService.list();
        List<OpenCoverDto> openCoverDtoList = OpenCoverDtoMapper.INSTANCE.entityListToDtoList(openCoverList);
        return Result.success().addData("pager",warpPage(openCoverDtoList));
    }

    /**
     * 添加班次-封面
     * @param openCover
     * @return
     */
    @ApiOperation(value = "添加班次-封面", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openCover", value = "班次-封面", required = true, dataType = "OpenCoverDto")
    })
    @PostMapping("/open_cover")
    public Result addOpenCover(@RequestBody OpenCoverDto openCover){

        OpenCover openCoverEntity = OpenCoverDtoMapper.INSTANCE.dtoToEntity(openCover);
        openCoverService.save(openCoverEntity);
        return Result.success();
    }

    /**
     * 删除班次-封面
     * @param id
     * @return
     */
    @ApiOperation(value = "删除班次-封面", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "班次-封面的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/open_cover/{id}")
    public Result removeOpenCover(@PathVariable("id") int id){

        openCoverService.remove(id);
        return Result.success();
    }

    /**
     * 修改班次-封面
     * @param id
     * @param openCover
     * @return
     */
    @ApiOperation(value = "修改班次-封面", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openCover", value = "班次-封面", required = true, dataType = "OpenCoverDto")
    })
    @PutMapping("/open_cover/{id}")
    public Result modifyOpenCover(@PathVariable("id") int id,
                                  @RequestBody OpenCoverDto openCover){

        OpenCover openCoverEntity = OpenCoverDtoMapper.INSTANCE.dtoToEntity(openCover);
        openCoverEntity.setId(id);
        openCoverService.modify(openCoverEntity);
        return Result.success();
    }

}
