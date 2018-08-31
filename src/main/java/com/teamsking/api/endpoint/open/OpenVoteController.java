package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.AddOpenVoteDto;
import com.teamsking.api.dto.open.OpenVoteDto;
import com.teamsking.api.dto.open.OpenVoteDtoMapper;
import com.teamsking.api.dto.open.OpenVoteQueryDto;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenVote;
import com.teamsking.domain.service.open.OpenVoteService;
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
@Slf4j
@Api(tags = "班次-投票操作接口")
public class OpenVoteController extends BaseController {


    @Autowired
    OpenVoteService openVoteService;


    @ApiOperation(value = "班次下的投票详情", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "课程的主键", required = true, dataType = "int"),
            @ApiImplicitParam(name = "voteId", value = "投票的主键", required = true, dataType = "int")
    })
    @GetMapping("/open_vote_info/{openId}/{voteId}")
    public Result openVote(@PathVariable int openId, @PathVariable int voteId){

        OpenVoteDto openVoteDto = openVoteService.getVote(openId,voteId);
        return Result.success().addData("openVote", openVoteDto);
    }



    @ApiOperation(value = "班次下的投票管理列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "课程的主键", required = true, dataType = "int")
    })
    @GetMapping("/open_votes/{openId}")
    public Result openVoteList(@PathVariable int openId){

        List<OpenVoteQueryDto> openVoteQueryDtoList = openVoteService.getVoteList(openId);

        if (null == openVoteQueryDtoList){
            return Result.success().addData("openVoteList", null);
        }else {
            return Result.success().addData("openVoteList", openVoteQueryDtoList);
        }
    }


    @ApiOperation(value = "添加班次-投票管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "addOpenVoteDto", value = "班次投票管理", required = true, dataType = "AddOpenVoteDto"),
            @ApiImplicitParam(name = "openId", value = "班次主键", required = true, dataType = "int")
    })
    @PostMapping("/open_vote/{openId}")
    public Result addOpenVote(@RequestBody AddOpenVoteDto addOpenVoteDto, @PathVariable int openId){

        openVoteService.save(addOpenVoteDto,openId);
        return Result.success();

    }



    @ApiOperation(value = "创建投票的同时发放", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "addOpenVoteDto", value = "班次投票管理", required = true, dataType = "AddOpenVoteDto"),
            @ApiImplicitParam(name = "openId", value = "班次主键", required = true, dataType = "int")
    })
    @PostMapping("/publish_open_vote/{openId}")
    public Result addAndPublishOpenVote(@RequestBody AddOpenVoteDto addOpenVoteDto, @PathVariable int openId){

        openVoteService.saveAndPublishOpenVote(addOpenVoteDto,openId);
        return Result.success();

    }



    @ApiOperation(value = "删除班次-投票管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班次投票管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/open_vote/{id}")
    public Result removeOpenVote(@PathVariable int id){

        openVoteService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "修改班次-投票管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openVote", value = "班次投票管理", required = true, dataType = "OpenVoteDto")
    })
    @PutMapping("/open_vote/{id}")
    public Result modifyOpenVote(@PathVariable int id,
                                 @RequestBody OpenVoteDto openVote){

        OpenVote openVoteEntity = OpenVoteDtoMapper.INSTANCE.dtoToEntity(openVote);
        openVoteEntity.setId(id);
        openVoteService.modify(openVoteEntity);
        return Result.success();

    }


}
