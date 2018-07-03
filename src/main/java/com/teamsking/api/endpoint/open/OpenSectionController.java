package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenSectionDto;
import com.teamsking.api.dto.open.OpenSectionDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenSection;
import com.teamsking.domain.service.open.OpenSectionService;
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
@Api(tags = "班次-节管理操作接口")
public class OpenSectionController extends BaseController {

    @Autowired
    OpenSectionService openSectionService;

    /**
     * 获取班次-节管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "班次-节管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_sections")
    public Result openSectionList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenSection> openSectionList = openSectionService.list();
        List<OpenSectionDto> openSectionDtoList = OpenSectionDtoMapper.INSTANCE.entityListToDtoList(openSectionList);
        return Result.success().addData("pager",warpPage(openSectionDtoList));
    }

    /**
     * 添加班次-节管理
     * @param openSection
     * @return
     */
    @ApiOperation(value = "添加班次-节", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openSection", value = "课程-节", required = true, dataType = "OpenSectionDto")
    })
    @PostMapping("/open_section")
    public Result addOpenSection(@RequestBody OpenSectionDto openSection){

        OpenSection openSectionEntity = OpenSectionDtoMapper.INSTANCE.dtoToEntity(openSection);
        openSectionService.save(openSectionEntity);
        return Result.success();
    }

    /**
     * 删除班次-节管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除班次-节", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "班次-节的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/open_section/{id}")
    public Result removeOpenSection(@PathVariable("id") int id){

        openSectionService.remove(id);
        return Result.success();
    }

    /**
     * 修改班次-节管理
     * @param id
     * @param openSection
     * @return
     */
    @ApiOperation(value = "修改班次-节", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openSection", value = "课程-节", required = true, dataType = "OpenSectionDto")
    })
    @PutMapping("/open_section/{id}")
    public Result modifyOpenSection(@PathVariable("id") int id,
                                    @RequestBody OpenSectionDto openSection){

        OpenSection openSectionEntity = OpenSectionDtoMapper.INSTANCE.dtoToEntity(openSection);
        openSectionEntity.setId(id);
        openSectionService.modify(openSectionEntity);
        return Result.success();
    }
}
