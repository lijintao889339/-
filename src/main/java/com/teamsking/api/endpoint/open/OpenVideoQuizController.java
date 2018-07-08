package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenVideoQuizDto;
import com.teamsking.api.dto.open.OpenVideoQuizDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenVideoQuiz;
import com.teamsking.domain.service.open.OpenVideoQuizService;
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
@Api(tags = "视频内试题操作接口")
public class OpenVideoQuizController extends BaseController {

    @Autowired
    OpenVideoQuizService openVideoQuizService;


    @ApiOperation(value = "视频内试题列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_video_quizs")
    public Result openVideoQuiz(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<OpenVideoQuiz> openVideoQuizList = openVideoQuizService.list();
        List<OpenVideoQuizDto> openVideoQuizDtoList = OpenVideoQuizDtoMapper.INSTANCE.entityListToDtoList(openVideoQuizList);
        return Result.success().addData("pager", warpPage(openVideoQuizDtoList));

    }



    @ApiOperation(value = "添加视频内试题", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openVideoQuiz", value = "视频内试题", required = true, dataType = "OpenVideoQuizDto")
    })
    @PostMapping("/open_video_quiz")
    public Result addOpenVideoQuiz(@RequestBody OpenVideoQuizDto openVideoQuiz){

        OpenVideoQuiz openVideoQuizEntity = OpenVideoQuizDtoMapper.INSTANCE.dtoToEntity(openVideoQuiz);
        openVideoQuizService.save(openVideoQuizEntity);
        return Result.success();

    }



    @ApiOperation(value = "删除视频内试题", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "视频内试题", required = true, dataType = "Integer")
    })
    @DeleteMapping("/open_video_quiz/{id}")
    public Result removeOpenVideoQuiz(@PathVariable int id){

        openVideoQuizService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "修改视频内试题", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openVideoQuiz", value = "视频内试题", required = true, dataType = "OpenVideoQuizDto")
    })
    @PutMapping("/open_video_quiz/{id}")
    public Result modifyOpenVideoQuiz(@PathVariable int id,
                                      @RequestBody OpenVideoQuizDto openVideoQuiz){

        OpenVideoQuiz openVideoQuizEntity = OpenVideoQuizDtoMapper.INSTANCE.dtoToEntity(openVideoQuiz);
        openVideoQuizEntity.setId(id);
        openVideoQuizService.modify(openVideoQuizEntity);
        return Result.success();

    }


}
