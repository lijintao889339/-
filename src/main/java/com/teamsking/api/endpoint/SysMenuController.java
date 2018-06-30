package com.teamsking.api.endpoint;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.SysMenuDto;
import com.teamsking.api.dto.SysMenuDtoMapper;
import com.teamsking.domain.entity.SysMenu;
import com.teamsking.domain.service.SysMenuService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @GetMapping("/sys_mesysMenuListnus")
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
}
