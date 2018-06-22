package com.teamsking.api.endpoint;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.SysUserDto;
import com.teamsking.api.dto.SysUserDtoMapper;
import com.teamsking.domain.entity.SysUser;
import com.teamsking.domain.service.SysUserService;
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
@Api(tags="用户操作接口")
@Slf4j
public class SysUserController extends BaseController {

    @Autowired
    SysUserService sysUserService;

    /**
     * 查询用户列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "用户列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/sys_users_list")
    public Result sysUserList(int pageNo, int pageSize) {
        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<SysUser> sysUserList = sysUserService.list();
        List<SysUserDto> sysUserDtoList = SysUserDtoMapper.INSTANCE.entityListToDtoList(sysUserList);
        return Result.success().addData("pager", warpPage(sysUserDtoList));
    }

    /**
     * 用户添加操作
     * @param sysUser
     * @return
     */
    @ApiOperation(value = "添加系统用户", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "sysUser", value = "系统用户", required = true, dataType = "SysUserDto")
    })
    @PostMapping("/sys_users")
    public Result addSysMenu(@RequestBody SysUserDto sysUser) {
        sysUserService.save(SysUserDtoMapper.INSTANCE.dtoToEntity(sysUser));
        return Result.success();
    }

    /**
     * 用户删除操作
     * @param sysUser
     * @return
     */
    @ApiOperation(value = "删除系统用户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysUser", value = "系统用户", required = true, dataType = "SysUserDto")
    })
    @DeleteMapping("/sys_users/{id}")
    public Result deleteSysUser(@RequestBody SysUserDto sysUser){

        sysUserService.remove(SysUserDtoMapper.INSTANCE.dtoToEntity(sysUser));
        return Result.success();
    }

    /**
     * 用户修改
     * @param sysUser
     * @return
     */
    @ApiOperation(value = "修改系统用户", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "sysUser", value = "系统用户", required = true, dataType = "SysUserDto")
    })
    @PutMapping("/sys_users/{id}")
    public Result modifySysUser(@RequestBody SysUserDto sysUser) {
        sysUserService.modify(SysUserDtoMapper.INSTANCE.dtoToEntity(sysUser));
        return Result.success();
    }


}
