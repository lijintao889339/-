package com.teamsking.api.endpoint.study;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.study.StudyWrongQuestionDto;
import com.teamsking.api.dto.study.StudyWrongQuestionDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.study.StudyWrongQuestion;
import com.teamsking.domain.service.study.StudyWrongQuestionService;
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
@Api(tags = "学习-错题记录管理操作")
public class StudyWrongQuestionController extends BaseController {

    @Autowired
    StudyWrongQuestionService studyWrongQuestionService;



    @ApiOperation(value = "学习-错题记录管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/study_wrong_questions")
    public Result studyWrongQuestionList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<StudyWrongQuestion> studyWrongQuestionList = studyWrongQuestionService.list();
        List<StudyWrongQuestionDto> studyWrongQuestionDtoList = StudyWrongQuestionDtoMapper.INSTANCE.entityListToDtoList(studyWrongQuestionList);
        return Result.success().addData("pager", warpPage(studyWrongQuestionDtoList));

    }


    @ApiOperation(value = "添加学习-错题记录管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "studyWrongQuestion", value = "学习错题记录管理", required = true, dataType = "StudyWrongQuestionDto")
    })
    @PostMapping("/study_wrong_question")
    public Result addStudyWrongQuestion(@RequestBody StudyWrongQuestionDto studyWrongQuestion){

        StudyWrongQuestion studyWrongQuestionEntity = StudyWrongQuestionDtoMapper.INSTANCE.dtoToEntity(studyWrongQuestion);
        studyWrongQuestionService.save(studyWrongQuestionEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除学习-错题记录管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "学习错题记录管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/study_wrong_question/{id}")
    public Result removeStudyWrongQuestion(@PathVariable int id){

        studyWrongQuestionService.remove(id);
        return Result.success();

    }


    @ApiOperation(value = "修改学习-错题记录管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "studyWrongQuestion", value = "学习错题记录管理", required = true, dataType = "StudyWrongQuestionDto")
    })
    @PutMapping("/study_wrong_question/{id}")
    public Result modifyStudyWrongQuestion(@PathVariable int id,
                                           @RequestBody StudyWrongQuestionDto studyWrongQuestion){

        StudyWrongQuestion studyWrongQuestionEntity = StudyWrongQuestionDtoMapper.INSTANCE.dtoToEntity(studyWrongQuestion);
        studyWrongQuestionEntity.setId(id);
        studyWrongQuestionService.modify(studyWrongQuestionEntity);
        return Result.success();

    }


}
