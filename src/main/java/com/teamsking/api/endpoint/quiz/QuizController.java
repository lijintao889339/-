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
@Api(tags = "试题的接口")
public class QuizController extends BaseController {

    @Autowired
    QuizService quizService;

    /**
     * 获取试题的列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "试题列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页面", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/quizs")
    public Result quizList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<Quiz> quizList = quizService.list();
        List<QuizDto> quizDtoList = QuizDtoMapper.INSTANCE.entityListToDtoList(quizList);
        return Result.success().addData("pager",warpPage(quizDtoList));
    }

    /**
     * 添加试题操作
     * @param quiz
     * @return
     */
    @ApiOperation(value = "添加试题", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quiz", value = "试题", required = true, dataType = "QuizDto")
    })
    @PostMapping("/quiz")
    public Result addQuiz(@RequestBody QuizDto quiz){

        Quiz quizEntity = QuizDtoMapper.INSTANCE.dtoToEntity(quiz);
        quizService.save(quizEntity);
        return Result.success();
    }

    /**
     * 删除试题操作
     * @param id
     * @return
     */
    @ApiOperation(value = "删除试题", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "试题的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/quiz/{id}")
    public Result removeQuiz(@PathVariable("id") int id){

        quizService.remove(id);
        return Result.success();
    }

    /**
     * 修改试题操作
     * @param id
     * @param quiz
     * @return
     */
    @ApiOperation(value = "添加试题", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quiz", value = "试题", required = true, dataType = "QuizDto")
    })
    @PutMapping("/quiz/{id}")
    public Result modifyQuiz(@PathVariable("id") int id,
                             @RequestBody QuizDto quiz){

        Quiz quizEntity = QuizDtoMapper.INSTANCE.dtoToEntity(quiz);
        quizEntity.setId(id);
        quizService.modify(quizEntity);
        return Result.success();
    }

}
