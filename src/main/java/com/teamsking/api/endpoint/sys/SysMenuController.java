package com.teamsking.api.endpoint.sys;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.sys.SysMenuDto;
import com.teamsking.api.dto.sys.SysMenuDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.sys.SysMenu;
import com.teamsking.domain.service.sys.SysMenuService;
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
 * @author ynfeng
 */
@RestController
@Api(tags = "菜单操作接口")
@Slf4j
public class SysMenuController extends BaseController {

    @Autowired
    SysMenuService sysMenuService;

    @ApiOperation(value = "菜单列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
        @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
        @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/sys_menus")
    public Result sysMenuList(int pageNo, int pageSize) {
        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<SysMenu> sysMenuList = sysMenuService.list();
        List<SysMenuDto> sysMenuDtoList = SysMenuDtoMapper.INSTANCE.entityListToDtoList(sysMenuList);
        return Result.success().addData("pager", warpPage(sysMenuDtoList));
    }

    @ApiOperation(value = "添加系统菜单", consumes= "application/json")
    @ApiImplicitParams( {
        @ApiImplicitParam(name = "sysMenu", value = "系统菜单", required = true, dataType = "SysMenuDto")
    })
    @PostMapping("/sys_menu")
    public Result addSysMenu(@RequestBody SysMenuDto sysMenu) {
        SysMenu sysMenuEntity = SysMenuDtoMapper.INSTANCE.dtoToEntity(sysMenu);
        sysMenuService.save(sysMenuEntity);
        return Result.success();
    }

    @ApiOperation(value = "删除系统菜单",consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "系统菜单",required = true,dataType = "Integer")
    })
    @DeleteMapping("/sys_menu/{id}")
    public Result removeSysMenu(@PathVariable("id") int id){
        sysMenuService.remove(id);
        return Result.success();
    }

    @ApiOperation(value = "修改系统菜单",consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysMenu",value = "系统菜单",required = true,dataType = "SysMenuDto")
    })
    @PutMapping("/sys_menu/{id}")
    public Result modifySysMenu(@RequestBody SysMenuDto sysMenu,
                                @PathVariable int id){
        SysMenu sysMenuEntity = SysMenuDtoMapper.INSTANCE.dtoToEntity(sysMenu);
        sysMenuEntity.setId(id);
        sysMenuService.modify(sysMenuEntity);

        return Result.success();
    }
}
