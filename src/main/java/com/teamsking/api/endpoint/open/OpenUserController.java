package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenUserDto;
import com.teamsking.api.dto.open.OpenUserDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.service.open.OpenUserService;
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
@Api(tags = "班次-学生选课管理接口")
public class OpenUserController extends BaseController {

    @Autowired
    OpenUserService openUserService;

    /**
     * 获取班次-学生选课列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "班次-选手选课管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_users")
    public Result openUserList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenUser> openUserList = openUserService.list();
        List<OpenUserDto> openUserDtoList = OpenUserDtoMapper.INSTANCE.entityListToDtoList(openUserList);
        return Result.success().addData("pager",warpPage(openUserDtoList));
    }

    /**
     * 添加班次-学生选课
     * @param openUser
     * @return
     */
    @ApiOperation(value = "添加班次-学生选课", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openUser", value = "班次-学生选课", required = true, dataType = "OpenUserDto")
    })
    @PostMapping("open_user")
    public Result addOpenUser(@RequestBody OpenUserDto openUser){

        OpenUser openUserEntity = OpenUserDtoMapper.INSTANCE.dtoToEntity(openUser);
        openUserService.save(openUserEntity);
        return Result.success();
    }

    /**
     * 删除班次-学生选课
     * @param id
     * @return
     */
    @ApiOperation(value = "删除班次-学生选课", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "班次-学生选课的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/open_user/{id}")
    public Result deleteOpenUser(@PathVariable("id") int id){

        openUserService.remove(id);
        return Result.success();
    }

    /**
     * 修改班次-学生选课
     * @param id
     * @param openUser
     * @return
     */
    @ApiOperation(value = "修改班次-学生选课", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openUser", value = "班次-学生选课", required = true, dataType = "OpenUserDto")
    })
    @PutMapping("/open_user/{id}")
    public Result modifyOpenUser(@PathVariable("id") int id,
                                 @RequestBody OpenUserDto openUser){

        OpenUser openUserEntity = OpenUserDtoMapper.INSTANCE.dtoToEntity(openUser);
        openUserEntity.setId(id);
        openUserService.modify(openUserEntity);
        return Result.success();
    }

}
