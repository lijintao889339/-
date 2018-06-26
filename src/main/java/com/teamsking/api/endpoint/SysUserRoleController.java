package com.teamsking.api.endpoint;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.SysUserRoleDto;
import com.teamsking.api.dto.SysUserRoleDtoMapper;
import com.teamsking.domain.entity.SysUserRole;
import com.teamsking.domain.service.SysUserRoleService;
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
@Api
public class SysUserRoleController extends BaseController{

    @Autowired
    SysUserRoleService sysUserRoleService;

    /**
     * 用户权限列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "用户权限列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/sys_users/{userId}/sys_role")
    public Result sysUsersRoleList(@PathVariable("userId") int userId,
                                   int pageNo, int pageSize) {

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        SysUserRole entity = new SysUserRole();
        entity.setUserId(userId);

        List<SysUserRole> sysUserRoleList = sysUserRoleService.list(entity);
        List<SysUserRoleDto> sysUserRoleDtoList = SysUserRoleDtoMapper.INSTANCE.entityListToDtoList(sysUserRoleList);
        return Result.success().addData("pager", warpPage(sysUserRoleDtoList));
    }

    /**
     * 用户权限添加操作
     * @param sysUserRole
     * @return
     */
    @ApiOperation(value = "添加系统用户", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "sysRoleUser", value = "系统用户", required = true, dataType = "SysUserRoleDto")
    })
    @PostMapping("/sys_user/{userId}/sys_role")
    public Result addSysUserRole(@PathVariable("userId") int userId,
                                 @RequestBody SysUserRoleDto sysUserRole
                                  ) {

        SysUserRole entity = SysUserRoleDtoMapper.INSTANCE.dtoToEntity(sysUserRole);
        entity.setUserId(userId);
        sysUserRoleService.save(entity);
        return Result.success();
    }

    /**
     * 用户权限删除操作
     * @param sysUserRole
     * @return
     */
    @ApiOperation(value = "删除系统用户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户主键", required = true, dataType = "Integer")
    })
    @DeleteMapping("/sys_user/{id}/sys_role/{userId}")
    public Result deleteSysUserRole(@PathVariable("id") int id,
                                    @PathVariable("userId") int userId,
                                    @RequestBody SysUserRoleDto sysUserRole){

        SysUserRole entity = SysUserRoleDtoMapper.INSTANCE.dtoToEntity(sysUserRole);
        entity.setId(id);
        entity.setUserId(userId);
        sysUserRoleService.remove(entity);
        return Result.success();
    }

    /**
     * 用户权限修改
     * @param sysUserRole
     * @return
     */
    @ApiOperation(value = "修改用户权限", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "sysUserRole", value = "系统用户", required = true, dataType = "SysUserRoleDto")
    })
    @PutMapping("/sys_user/{id}/sys_role/{userId}")
    public Result modifySysUserRole(@PathVariable("id") int id,
                                    @PathVariable("userId") int userId,
                                    @RequestBody SysUserRoleDto sysUserRole) {

        SysUserRole entity = SysUserRoleDtoMapper.INSTANCE.dtoToEntity(sysUserRole);
        entity.setId(id);
        entity.setUserId(userId);
        sysUserRoleService.modify(entity);
        return Result.success();
    }


}
