package com.teamsking.api.endpoint.sys;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.sys.SysUserDto;
import com.teamsking.api.dto.sys.SysUserDtoMapper;
import com.teamsking.api.dto.sys.UserDto;
import com.teamsking.api.dto.sys.UserTeacherDto;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.sys.SysUser;
import com.teamsking.domain.service.sys.SysUserService;
import com.teamsking.domain.service.sys.UserTeacherService;
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
@Api(tags = "用户操作接口")
@Slf4j
public class SysUserController extends BaseController {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    UserTeacherService userTeacherService;

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
    @ApiOperation(value = "添加系统用户", consumes = "application/json")
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
    @ApiOperation(value = "删除系统用户", consumes = "application/json")
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
    @ApiOperation(value = "修改系统用户", consumes = "application/json")
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


    @ApiOperation(value = "创建课程模板：获取所有的导学老师列表", produces = "application/json")
    @GetMapping("/role_user")
    public Result getUserName(){

        List<UserDto> userDtoList = SysUserDtoMapper.INSTANCE.entityDtoToUserDtoList(sysUserService.getUserNameByRoleId());
        return Result.success().addData("userDtoList",userDtoList);
    }



    @ApiOperation(value = "创建班课：获取所有的教学老师列表", produces = "application/json")
    @GetMapping("/open_role_user")
    public Result getOpenUserName(){

        List<UserDto> userDtoList = SysUserDtoMapper.INSTANCE.entityDtoToUserDtoList(sysUserService.getOpenUserNameByRoleId());
        return Result.success().addData("userDtoList",userDtoList);

    }



    @ApiOperation(value = "获取班课的辅导老师列表", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班课的主键", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_user_teachers/{id}")
    public Result getOpenUser(@PathVariable int id, @RequestParam int pageNo, @RequestParam int pageSize){

        List<UserTeacherDto> userTeacherDtos = userTeacherService.getOpenUserTeacherById(id,fixPage(pageNo),fixPage(pageSize));
        return Result.success().addData("pager",warpPage(userTeacherDtos));
    }



    @ApiOperation(value = "根据条件搜索班课的辅导老师列表", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班课的主键", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userName", value = "老师姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/searching_user_teachers/{id}")
    public Result searchingUserTeacher(@PathVariable int id, @RequestParam String userName, @RequestParam String email,
                              @RequestParam int pageNo, @RequestParam int pageSize){

        Page page = userTeacherService.searcingUserTeacherByOpenId(id,userName,email,fixPage(pageNo),fixPage(pageSize));
        if (null == page){
            return Result.success().addData("pager",null);
        }else {
            return Result.success().addData("pager",warpPage(page));
        }

    }



    @ApiOperation(value = "批量删除班课的辅导老师")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "userTeacherIds", value = "辅导老师的主键", required = true, dataType = "Integer[]"),
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/open_user_teachers/multi_delete/{openId}")
    public Result removeMultiOpenUser(@RequestParam Integer[] userTeacherIds, @PathVariable int openId){

        userTeacherService.removeMultiOpenUserByIds(userTeacherIds,openId);
        return Result.success();
    }

}
