package com.teamsking.api.endpoint.study;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.study.StudyQuestionnaireDto;
import com.teamsking.api.dto.study.StudyQuestionnaireDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.study.StudyQuestionnaire;
import com.teamsking.domain.service.study.StudyQuestionnaireService;
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
@Api(tags = "学习-问卷调查记录操作接口")
public class StudyQuestionnaireController extends BaseController {

    @Autowired
    StudyQuestionnaireService studyQuestionnaireService;


    @ApiOperation(value = "学习-问卷调查记录列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/study_questionnaires")
    public Result studyQuestionnaireList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<StudyQuestionnaire> studyQuestionnaireList = studyQuestionnaireService.list();
        List<StudyQuestionnaireDto> studyQuestionnaireDtoList = StudyQuestionnaireDtoMapper.INSTANCE.entityListToDtoList(studyQuestionnaireList);
        return Result.success().addData("pager", warpPage(studyQuestionnaireDtoList));

    }



    @ApiOperation(value = "添加学习-问卷调查记录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "studyQuestionnaire", value = "学习问卷调查记录", required = true, dataType = "StudyQuestionnaireDto")
    })
    @PostMapping("/study_questionnaire")
    public Result addStudyQuestionnaire(@RequestBody StudyQuestionnaireDto studyQuestionnaire){

        StudyQuestionnaire studyQuestionnaireEntity = StudyQuestionnaireDtoMapper.INSTANCE.dtoToEntity(studyQuestionnaire);
        studyQuestionnaireService.save(studyQuestionnaireEntity);
        return Result.success();

    }



    @ApiOperation(value = "删除学习-问卷调查记录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "学习问卷调查记录", required = true, dataType = "Integer")
    })
    @DeleteMapping("/study_questionnaire/{id}")
    public Result removeStudyQuestionnaire(@PathVariable int id){

        studyQuestionnaireService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "修改学习-问卷调查记录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "studyQuestionnaire", value = "学习问卷调查记录", required = true, dataType = "StudyQuestionnaireDto")
    })
    @PutMapping("/study_questionnaire/{id}")
    public Result modifyStudyQuestionnaire(@PathVariable int id,
                                           @RequestBody StudyQuestionnaireDto studyQuestionnaire){


        StudyQuestionnaire studyQuestionnaireEntity = StudyQuestionnaireDtoMapper.INSTANCE.dtoToEntity(studyQuestionnaire);
        studyQuestionnaireEntity.setId(id);
        studyQuestionnaireService.modify(studyQuestionnaireEntity);
        return Result.success();

    }


}
