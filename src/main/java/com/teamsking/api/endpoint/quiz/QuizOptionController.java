package com.teamsking.api.endpoint.quiz;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.quiz.QuizOptionDto;
import com.teamsking.api.dto.quiz.QuizOptionDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.quiz.QuizOption;
import com.teamsking.domain.service.quiz.QuizOptionService;
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
@Api(tags = "试题选项接口")
public class QuizOptionController extends BaseController {

    @Autowired
    QuizOptionService quizOptionService;

    /**
     * 获取试题选项
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "试题选项列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/quiz_options")
    public Result quizOptionList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<QuizOption> quizOptionList = quizOptionService.list();
        List<QuizOptionDto> quizOptionDtoList = QuizOptionDtoMapper.INSTANCE.entityListToDtoList(quizOptionList);
        return Result.success().addData("pager",warpPage(quizOptionDtoList));

    }

    /**
     * 添加试题选项
     * @param quizOption
     * @return
     */
    @ApiOperation(value = "添加试题选项", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quizOption", value = "试题选项", required = true, dataType = "QuizOptionDto")
    })
    @PostMapping("/quiz_option")
    public Result addQuizOption(@RequestBody QuizOptionDto quizOption){

        QuizOption quizOptionEntity = QuizOptionDtoMapper.INSTANCE.dtoToEntity(quizOption);
        quizOptionService.save(quizOptionEntity);
        return Result.success();
    }

    /**
     * 删除试题选项
     * @param id
     * @return
     */
    @ApiOperation(value = "删除试题选项", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quizOption", value = "试题选项", required = true, dataType = "QuizOptionDto")
    })
    @DeleteMapping("/quiz_option/{id}")
    public Result removeQuizOption(@PathVariable("id") int id){

        quizOptionService.remove(id);
        return Result.success();
    }

    /**
     * 修改试题选项
     * @param id
     * @param quizOption
     * @return
     */
    @ApiOperation(value = "修改试题选项", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quizOption", value = "试题选项", required = true, dataType = "QuizOptionDto")
    })
    @PutMapping("/quiz_option/{id}")
    public Result modifyQuizOption(@PathVariable("id") int id,
                                   @RequestBody QuizOptionDto quizOption){

        QuizOption quizOptionEntity = QuizOptionDtoMapper.INSTANCE.dtoToEntity(quizOption);
        quizOptionEntity.setId(id);
        quizOptionService.modify(quizOptionEntity);
        return Result.success();
    }
}
