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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "公告列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/Announces")
    public Result announceList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<Announce> announceList = announceService.list();
        List<AnnounceDto> announceDtoList = AnnounceDtoMapper.INSTANCE.entityListToDtoList(announceList);
        return Result.success().addData("pager",warpPage(announceDtoList));
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
