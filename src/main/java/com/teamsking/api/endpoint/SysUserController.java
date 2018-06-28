package com.teamsking.api.endpoint;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.SysUserDto;
import com.teamsking.api.dto.SysUserDtoMapper;
import com.teamsking.api.dto.SysUserRoleDtoMapper;
import com.teamsking.domain.entity.SysUser;
import com.teamsking.domain.entity.SysUserRole;
import com.teamsking.domain.repository.SysUserMapper;
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
@Api(tags = "用户操作接口")
@Slf4j
public class SysUserController extends BaseController {

    @Autowired
    SysUserService sysUserService;

    /**
     * 查询用户列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "用户列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/sys_users")
    public Result sysUserList(int pageNo, int pageSize) {

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<SysUser> sysUserList = sysUserService.list();
        List<SysUserDto> sysUserDtoList = SysUserDtoMapper.INSTANCE.entityListToDtoList(sysUserList);
        return Result.success().addData("pager", warpPage(sysUserDtoList));
    }

    /**
     * 用户添加操作
     *
     * @param sysUser
     * @return
     */
    @ApiOperation(value = "添加系统用户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysUser", value = "系统用户", required = true, dataType = "SysUserDto")
    })
    @PostMapping("/sys_user")
    public Result addSysUser(@RequestBody SysUserDto sysUser) {

        SysUser sysUserEntity = SysUserDtoMapper.INSTANCE.dtoToEntity(sysUser);
        sysUserService.save(sysUserEntity);
        return Result.success();
    }

    /**
     * 用户删除操作
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除系统用户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户主键", required = true, dataType = "Integer")
    })
    @DeleteMapping("/sys_user/{id}")
    public Result deleteSysUser(@PathVariable("id") int id) {

        sysUserService.remove(id);
        return Result.success();
    }

    /**
     * 用户修改操作
     *
     * @param sysUser
     * @return
     */
    @ApiOperation(value = "修改系统用户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysUser", value = "系统用户", required = true, dataType = "SysUserDto")
    })
    @PutMapping("/sys_user/{id}")
    public Result modifySysUser(@PathVariable int id,
                                @RequestBody SysUserDto sysUser) {

        SysUser sysUserEntity = SysUserDtoMapper.INSTANCE.dtoToEntity(sysUser);
        sysUserEntity.setId(id);
        sysUserService.modify(sysUserEntity);
        return Result.success();
    }


}
