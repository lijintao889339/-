package com.teamsking.api.endpoint.sys;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.sys.SysConfigDto;
import com.teamsking.api.dto.sys.SysConfigDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.sys.SysConfig;
import com.teamsking.domain.service.sys.SysConfigService;
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
@Api(tags = "系统配置操作接口")
public class SysConfigController extends BaseController {

    @Autowired
    SysConfigService sysConfigService;

    /**
     * 获取系统配置列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "系统配置列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/sys_configs")
    public Result sysConfigList(int pageNo, int pageSize) {

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<SysConfig> sysConfigList = sysConfigService.list();
        List<SysConfigDto> sysConfigDtoList = SysConfigDtoMapper.INSTANCE.entityListToDtoList(sysConfigList);
        return Result.success().addData("pager", warpPage(sysConfigDtoList));
    }

    /**
     * 添加系统配置
     * @param sysConfig
     * @return
     */
    @ApiOperation(value = "添加系统配置", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysConfig", value = "系统配置", required = true, dataType = "SysConfigDto")
    })
    @PostMapping("/sys_config")
    public Result addSysConfig(@RequestBody SysConfigDto sysConfig) {

        SysConfig sysConfigEntity = SysConfigDtoMapper.INSTANCE.dtoToEntity(sysConfig);
        sysConfigService.save(sysConfigEntity);
        return Result.success();
    }

    /**
     * 删除系统配置
     * @param id
     * @return
     */
    @ApiOperation(value = "删除系统配置", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "配置主键", required = true, dataType = "int")
    })
    @DeleteMapping("/sys_config/{id}")
    public Result removeSysConfig(@PathVariable("id") int id) {

        sysConfigService.remove(id);
        return Result.success();
    }

    /**
     * 修改系统配置
     * @param id
     * @param sysConfig
     * @return
     */
    @ApiOperation(value = "修改系统配置", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysConfig", value = "系统配置", required = true, dataType = "SysConfigDto")
    })
    @PutMapping("/sys_config/{id}")
    public Result modifySysConfig(@PathVariable int id,
                                @RequestBody SysConfigDto sysConfig) {

        SysConfig sysConfigEntity = SysConfigDtoMapper.INSTANCE.dtoToEntity(sysConfig);
        sysConfigEntity.setId(id);
        sysConfigService.modify(sysConfigEntity);
        return Result.success();
    }

}
