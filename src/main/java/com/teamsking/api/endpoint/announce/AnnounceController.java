package com.teamsking.api.endpoint.announce;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.announce.AnnounceDto;
import com.teamsking.api.dto.announce.AnnounceDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.announce.Announce;
import com.teamsking.domain.service.announce.AnnounceService;
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
@Api(tags = "公告操作接口")
public class AnnounceController extends BaseController {

    @Autowired
    AnnounceService announceService;

    /**
     * 获取公告列表
     * @param openId
     * @return
     */
    @ApiOperation(value = "获取班课下的公告列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int"),
    })
    @GetMapping("/announces/{openId}")
    public Result announceList(@PathVariable int openId){

        List<AnnounceDto> AnnounceDtoList = announceService.list(openId);
        return Result.success().addData("AnnounceDtoList",AnnounceDtoList);
    }

    /**
     * 添加公告
     * @param announce
     * @return
     */
    @ApiOperation(value = "添加公告", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "announce", value = "公告", required = true, dataType = "AnnounceDto")
    })
    @PostMapping("/Announce")
    public Result addAnnounce(@RequestBody AnnounceDto announce){

        Announce announceEntity = AnnounceDtoMapper.INSTANCE.dtoToEntity(announce);
        announceService.save(announceEntity);
        return Result.success();
    }

    /**
     * 删除公告
     * @param id
     * @return
     */
    @ApiOperation(value = "删除公告", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "公告的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/Announce/{id}")
    public Result removeAnnounce(@PathVariable("id") int id){

        announceService.remove(id);
        return Result.success();
    }

    /**
     * 修改公告
     * @param id
     * @param announce
     * @return
     */
    @ApiOperation(value = "修改公告", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "announce", value = "公告", required = true, dataType = "AnnounceDto")
    })
    @PutMapping("/Announce/{id}")
    public Result modifyAnnounce(@PathVariable("id") int id,
                                 @RequestBody AnnounceDto announce){

        Announce announceEntity = AnnounceDtoMapper.INSTANCE.dtoToEntity(announce);
        announceEntity.setId(id);
        announceService.modify(announceEntity);
        return Result.success();
    }

}
