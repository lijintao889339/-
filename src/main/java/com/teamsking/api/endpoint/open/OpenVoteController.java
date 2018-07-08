package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenVoteDto;
import com.teamsking.api.dto.open.OpenVoteDtoMapper;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@Api(tags = "班次-投票操作接口")
public class OpenVoteController extends BaseController {


    @Autowired
    OpenVoteService openVoteService;


    @ApiOperation(value = "班次-投票管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_votes")
    public Result openVoteList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));

        List<OpenVote> openVoteList = openVoteService.list();

        List<OpenVoteDto> openVoteDtoList = OpenVoteDtoMapper.INSTANCE.entityListToDtoList(openVoteList);

        return Result.success().addData("pager", warpPage(openVoteDtoList));

    }


    @ApiOperation(value = "添加班次-投票管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openVote", value = "班次投票管理", required = true, dataType = "OpenVoteDto")
    })
    @PostMapping("/open_vote")
    public Result addOpenVote(@RequestBody OpenVoteDto openVote){

        OpenVote openVoteEntity = OpenVoteDtoMapper.INSTANCE.dtoToEntity(openVote);
        openVoteService.save(openVoteEntity);
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
