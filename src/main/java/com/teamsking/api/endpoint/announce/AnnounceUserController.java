package com.teamsking.api.endpoint.announce;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.announce.AnnounceUserDto;
import com.teamsking.api.dto.announce.AnnounceUserDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.announce.AnnounceUser;
import com.teamsking.domain.service.announce.AnnounceUserService;
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
@Api(tags = "公告阅读记录操作接口")
public class AnnounceUserController extends BaseController {

    @Autowired
    AnnounceUserService announceUserService;

    /**
     * 获取公告阅读记录列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "公告阅读记录列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/Announce_users")
    public Result announceUserList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<AnnounceUser> announceUserList = announceUserService.list();
        List<AnnounceUserDto> announceUserDtoList = AnnounceUserDtoMapper.INSTANCE.entityListToDtoList(announceUserList);
        return Result.success().addData("pager",warpPage(announceUserDtoList));
    }

    /**
     * 添加公告阅读记录
     * @param announceUser
     * @return
     */
    @ApiOperation(value = "添加公告阅读记录", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "announceUser", value = "公告阅读记录", required = true, dataType = "AnnounceUserDto")
    })
    @PostMapping("/Announce_user")
    public Result addAnnounceUser(@RequestBody AnnounceUserDto announceUser){

        AnnounceUser announceUserEntity = AnnounceUserDtoMapper.INSTANCE.dtoToEntity(announceUser);
        announceUserService.save(announceUserEntity);
        return Result.success();
    }

    /**
     * 删除公告阅读记录
     * @param id
     * @return
     */
    @ApiOperation(value = "删除公告阅读记录", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "公告阅读记录的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/Announce_user/{id}")
    public Result removeAnnounceUser(@PathVariable("id") int id){

        announceUserService.remove(id);
        return Result.success();
    }

    /**
     * 修改公告阅读记录
     * @param id
     * @param announceUser
     * @return
     */
    @ApiOperation(value = "修改公告阅读记录", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "announceUser", value = "公告阅读记录", required = true, dataType = "AnnounceUserDto")
    })
    @PutMapping("/Announce_user/{id}")
    public Result modifyAnnounceUser(@PathVariable("id") int id,
                                 @RequestBody AnnounceUserDto announceUser){

        AnnounceUser announceUserEntity = AnnounceUserDtoMapper.INSTANCE.dtoToEntity(announceUser);
        announceUserEntity.setId(id);
        announceUserService.modify(announceUserEntity);
        return Result.success();
    }



}
