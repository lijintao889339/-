package com.teamsking.api.endpoint.study;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.study.StudyScoreDto;
import com.teamsking.api.dto.study.StudyScoreDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.study.StudyScore;
import com.teamsking.domain.service.study.StudyScoreService;
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
@Api("学习-成绩操作接口")
public class StudyScoreController extends BaseController {

    @Autowired
    StudyScoreService studyScoreService;



    @ApiOperation(value = "学习-成绩管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/study_scores")
    public Result studyScoreList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<StudyScore> studyScoreList = studyScoreService.list();
        List<StudyScoreDto> studyScoreDtoList = StudyScoreDtoMapper.INSTANCE.entityListToDtoList(studyScoreList);
        return Result.success().addData("pager", warpPage(studyScoreDtoList));

    }


    @ApiOperation(value = "添加学习-成绩管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "studyScore", value = "学习成绩管理", required = true, dataType = "StudyScoreDto")
    })
    @PostMapping("/study_score")
    public Result addStudyScore(@RequestBody StudyScoreDto studyScore){

        StudyScore studyScoreEntity = StudyScoreDtoMapper.INSTANCE.dtoToEntity(studyScore);
        studyScoreService.save(studyScoreEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除学习-成绩管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "学习成绩管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/study_score/{id}")
    public Result removeStudyScore(@PathVariable int id){

        studyScoreService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "修改学习-成绩管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "studyScore", value = "学习成绩管理", required = true, dataType = "StudyScoreDto")
    })
    @PutMapping("/study_score/{id}")
    public Result modifyStudyScore(@PathVariable int id,
                                   @RequestBody StudyScoreDto studyScore){

        StudyScore studyScoreEntity = StudyScoreDtoMapper.INSTANCE.dtoToEntity(studyScore);
        studyScoreEntity.setId(id);
        studyScoreService.modify(studyScoreEntity);
        return Result.success();

    }


}
