package com.teamsking.api.endpoint.study;

import com.github.pagehelper.Page;
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
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@Api("学习-成绩操作接口")
public class StudyScoreController extends BaseController {

    @Autowired
    StudyScoreService studyScoreService;



    @ApiOperation(value = "班课下学生成绩列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int")
    })
    @GetMapping("/study_scores/{openId}")
    public Result studyScoreList(@RequestParam int pageNo, @RequestParam int pageSize, @PathVariable int openId){

        Page page = studyScoreService.list(fixPage(pageNo),fixPage(pageSize),openId);
        if (null == page){
            return Result.success().addData("pager", null);
        }else {
            return Result.success().addData("pager", warpPage(page));
        }
    }



    @ApiOperation(value = "模糊搜索班课下学生成绩列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int"),
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
    })
    @GetMapping("/study_scores_name/{openId}")
    public Result studyScoreListBySearching(@RequestParam int pageNo, @RequestParam int pageSize,
                                 @PathVariable int openId, @RequestParam String userName){

        Page page = studyScoreService.listBySearching(fixPage(pageNo),fixPage(pageSize),openId,userName);
        if (null == page){
            return Result.success().addData("pager", null);
        }else {
            return Result.success().addData("pager", warpPage(page));
        }
    }



    @ApiOperation(value = "根据班组和学员状态搜索成绩列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int"),
            @ApiImplicitParam(name = "groupName", value = "组名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "activationStatus", value = "学员状态", required = true, dataType = "String"),
    })
    @GetMapping("/searching_study_scores/{openId}")
    public Result studyScoreListByGroupNameAndStatus(@RequestParam int pageNo, @RequestParam int pageSize,@PathVariable int openId,
                                                     @RequestParam String groupName, @RequestParam String activationStatus){

        Page page = studyScoreService.listByGroupNameAndStatus(fixPage(pageNo),fixPage(pageSize),openId,groupName,activationStatus);
        if (null == page){
            return Result.success().addData("pager", null);
        }else {
            return Result.success().addData("pager", warpPage(page));
        }
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
