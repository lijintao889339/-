package com.teamsking.api.endpoint.quiz;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.quiz.QuizDto;
import com.teamsking.api.dto.quiz.QuizDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.quiz.Quiz;
import com.teamsking.domain.service.quiz.QuizService;
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
@Api(tags = "试题的接口")
public class QuizController extends BaseController {

    @Autowired
    QuizService quizService;

    /**
     * 获取试题的列表
     * @param openId
     * @param courseId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "试题列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页面", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("open/{openId}/course/{courseId}/quizs")
    public Result quizList(@PathVariable("openId") int openId,
                           @PathVariable("courseId") int courseId,
                           int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        QuizDto quiz = new QuizDto();
        quiz.setOpenId(openId);
        quiz.setCourseId(courseId);
        Quiz quizEntity = QuizDtoMapper.INSTANCE.dtoToEntity(quiz);
        List<Quiz> quizList = quizService.list(quizEntity);

        List<QuizDto> quizDtoList = QuizDtoMapper.INSTANCE.entityListToDtoList(quizList);
        return Result.success().addData("pager",warpPage(quizDtoList));
    }

    /**
     * 添加试题操作
     * @param openId
     * @param courseId
     * @param quiz
     * @return
     */
    @ApiOperation(value = "添加试题", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quiz", value = "试题", required = true, dataType = "QuizDto")
    })
    @PostMapping("open/{openId}/course/{courseId}/quiz")
    public Result addQuiz(@PathVariable("openId") int openId,
                          @PathVariable("courseId") int courseId,
                          @RequestBody QuizDto quiz){

        Quiz quizEntity = QuizDtoMapper.INSTANCE.dtoToEntity(quiz);
        quizEntity.setOpenId(openId);
        quizEntity.setCourseId(courseId);
        quizService.save(quizEntity);
        return Result.success();
    }

    /**
     * 删除试题操作
     * @param openId
     * @param courseId
     * @param id
     * @param quiz
     * @return
     */
    @ApiOperation(value = "删除试题", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quiz", value = "试题", required = true, dataType = "QuizDto")
    })
    @DeleteMapping("open/{openId}/course/{courseId}/quiz/{id}")
    public Result removeQuiz(@PathVariable("openId") int openId,
                             @PathVariable("courseId") int courseId,
                             @PathVariable("id") int id,
                             @RequestBody QuizDto quiz){

        Quiz quizEntity = QuizDtoMapper.INSTANCE.dtoToEntity(quiz);
        quizEntity.setOpenId(openId);
        quizEntity.setCourseId(courseId);
        quizEntity.setId(id);
        quizService.remove(quizEntity);
        return Result.success();
    }

    /**
     * 修改试题操作
     * @param openId
     * @param courseId
     * @param id
     * @param quiz
     * @return
     */
    @ApiOperation(value = "添加试题", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quiz", value = "试题", required = true, dataType = "QuizDto")
    })
    @PutMapping("open/{openId}/course/{courseId}/quiz/{id}")
    public Result modifyQuiz(@PathVariable("openId") int openId,
                             @PathVariable("courseId") int courseId,
                             @PathVariable("id") int id,
                             @RequestBody QuizDto quiz){

        Quiz quizEntity = QuizDtoMapper.INSTANCE.dtoToEntity(quiz);
        quizEntity.setOpenId(openId);
        quizEntity.setCourseId(courseId);
        quizEntity.setId(id);
        quizService.modify(quizEntity);
        return Result.success();
    }

}
