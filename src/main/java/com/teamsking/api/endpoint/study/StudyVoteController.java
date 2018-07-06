package com.teamsking.api.endpoint.study;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.study.StudyVoteDto;
import com.teamsking.api.dto.study.StudyVoteDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.study.StudyVote;
import com.teamsking.domain.service.study.StudyVoteService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@Api("学习-投票操作接口")
public class StudyVoteController extends BaseController {

    @Autowired
    StudyVoteService studyVoteService;


    @ApiOperation(value = "学习-投票管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/study_votes")
    public Result studyVoteList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<StudyVote> studyVoteList = studyVoteService.list();
        List<StudyVoteDto> studyVoteDtoList = StudyVoteDtoMapper.INSTANCE.entityListToDtoList(studyVoteList);
        return Result.success().addData("pager", warpPage(studyVoteDtoList));

    }


    @ApiOperation(value = "添加学习-投票管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "studyVote", value = "学习投票管理", required = true, dataType = "StudyVoteDto")
    })
    @PostMapping("/study_vote")
    public Result addStudyVote(@RequestBody StudyVoteDto studyVote){

        StudyVote studyVoteEntity = StudyVoteDtoMapper.INSTANCE.dtoToEntity(studyVote);
        studyVoteService.save(studyVoteEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除学习-投票管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "学习投票管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/study_vote/{id}")
    public Result removeStudyVote(@PathVariable int id){

        studyVoteService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "修改学习-投票管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "studyVote", value = "学习投票管理", required = true, dataType = "StudyVoteDto")
    })
    @PutMapping("/study_vote/{id}")
    public Result modifyStudyVote(@PathVariable int id,
                                  @RequestBody StudyVoteDto studyVote){

        StudyVote studyVoteEntity = StudyVoteDtoMapper.INSTANCE.dtoToEntity(studyVote);
        studyVoteEntity.setId(id);
        studyVoteService.modify(studyVoteEntity);
        return Result.success();

    }

}
