package com.teamsking.api.endpoint;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.SysRoleDto;
import com.teamsking.api.dto.SysRoleDtoMapper;
import com.teamsking.domain.entity.SysRole;
import com.teamsking.domain.service.SysRoleService;
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
@Api(tags = "角色操作接口")
@Slf4j
public class SysRoleController extends BaseController {

    @Autowired
    SysRoleService sysRoleService;

    @ApiOperation(value = "角色列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/sys_roles")
    public Result sysRoleList(int pageNo, int pageSize) {
        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<SysRole> sysRoleList = sysRoleService.list();
        List<SysRoleDto> sysRoleDtoList = SysRoleDtoMapper.INSTANCE.entityListToDtoList(sysRoleList);
        return Result.success().addData("pager", warpPage(sysRoleDtoList));
    }


    @ApiOperation(value = "添加角色", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "sysRole", value = "角色", required = true, dataType = "SysRoleDto")
    })
    @PostMapping("/sys_role")
    public Result addSysRole(@RequestBody SysRoleDto sysRole) {
        sysRoleService.save(SysRoleDtoMapper.INSTANCE.dtoToEntity(sysRole));
        return Result.success();
    }


    @ApiOperation(value = "删除角色",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysRole",value = "角色",required = true,dataType = "SysRoleDto")
    })
    @DeleteMapping("/sys_role/{id}")
    public Result removeSysRole(@PathVariable("id") int id){
        sysRoleService.remove(id);
        return Result.success();
    }

    @ApiOperation(value = "修改角色",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysRole",value = "角色",required = true,dataType = "SysRoleDto")
    })
    @PutMapping("/sys_role/{id}")
    public Result modifySysRole(@RequestBody SysRoleDto sysRole,
                                @PathVariable int id){
        SysRole sysRoleEntity = SysRoleDtoMapper.INSTANCE.dtoToEntity(sysRole);
        sysRoleEntity.setId(id);
        sysRoleService.modify(sysRoleEntity);
        return Result.success();
    }

}
