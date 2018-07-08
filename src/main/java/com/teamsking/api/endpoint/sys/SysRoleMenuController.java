package com.teamsking.api.endpoint.sys;


import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.sys.SysRoleMenuDto;
import com.teamsking.api.dto.sys.SysRoleMenuDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.sys.SysRoleMenu;
import com.teamsking.domain.service.sys.SysRoleMenuService;
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


@RestController
@Api(tags = "角色权限接口")
@Slf4j
public class SysRoleMenuController extends BaseController {

    @Autowired
    SysRoleMenuService sysRoleMenuService;


    @ApiOperation(value = "角色权限列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/sys_role_menus")
    public Result sysRoleMenuList(int pageNo, int pageSize) {
        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));

        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuService.list();
        List<SysRoleMenuDto> sysRoleMenuDtoList = SysRoleMenuDtoMapper.INSTANCE.entityListToDtoList(sysRoleMenuList);
        return Result.success().addData("pager", warpPage(sysRoleMenuDtoList));
    }


    @ApiOperation(value = "添加角色权限", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "sysRoleMenu", value = "角色权限", required = true, dataType = "SysRoleMenuDto")
    })
    @PostMapping("/sys_role_menu")
    public Result addSysRoleMenu(@RequestBody SysRoleMenuDto sysRoleMenu) {
        SysRoleMenu sysRoleMenuEntity = SysRoleMenuDtoMapper.INSTANCE.dtoToEntity(sysRoleMenu);

        sysRoleMenuService.save(sysRoleMenuEntity);
        return Result.success();
    }

    @ApiOperation(value = "删除角色权限", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "角色权限", required = true, dataType = "Integer")
    })
    @DeleteMapping("/sys_role_menu/{id}")
    public Result removeSysRoleMenu(@PathVariable("id") Integer id){

        sysRoleMenuService.remove(id);
        return Result.success();
    }


    @ApiOperation(value = "修改角色权限", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "sysRoleMenu", value = "角色权限", required = true, dataType = "SysRoleMenuDto")
    })
    @PutMapping("/sys_role_menu/{id}")
    public Result modifySysRoleMenu(@PathVariable int id,
                                    @RequestBody SysRoleMenuDto sysRoleMenu){
        SysRoleMenu sysRoleMenuEntity = SysRoleMenuDtoMapper.INSTANCE.dtoToEntity(sysRoleMenu);
        sysRoleMenuEntity.setId(id);

        sysRoleMenuService.modify(sysRoleMenuEntity);
        return Result.success();
    }

}
