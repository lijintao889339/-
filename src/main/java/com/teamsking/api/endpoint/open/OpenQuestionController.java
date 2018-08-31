package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.AddOpenQuestionDto;
import com.teamsking.api.dto.open.OpenQuestionDto;
import com.teamsking.api.dto.open.OpenQuestionDtoMapper;
import com.teamsking.api.dto.open.OpenQuestionQueryDto;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenQuestion;
import com.teamsking.domain.service.open.OpenQuestionService;
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
@Api(tags = "班次-问卷调查管理操作接口")
public class OpenQuestionController extends BaseController {

    @Autowired
    OpenQuestionService openQuestionService;

    /**
     * 获取班次-问卷调查详情
     * @param openId
     * @return
     */
    @ApiOperation(value = "班次下问卷详情", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int"),
            @ApiImplicitParam(name = "questionId", value = "问卷的主键", required = true, dataType = "int")
    })
    @GetMapping("/open_question_info/{openId}/{questionId}")
    public Result openQuestion(@PathVariable int openId, @PathVariable int questionId){

        OpenQuestionDto openQuestionDto = openQuestionService.getQuestion(openId,questionId);
        return Result.success().addData("openQuestion",openQuestionDto);
    }


    @ApiOperation(value = "班次下的问卷管理列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "课程的主键", required = true, dataType = "int")
    })
    @GetMapping("/open_questions/{openId}")
    public Result openQuestionList(@PathVariable int openId){

        List<OpenQuestionQueryDto> openQuestionQueryDtoList = openQuestionService.getQuestionList(openId);

        if (null == openQuestionQueryDtoList){
            return Result.success().addData("openQuestionList", null);
        }else {
            return Result.success().addData("openQuestionList", openQuestionQueryDtoList);
        }
    }

    /**
     * 添加班次-问卷调查管理
     * @param addOpenQuestionDto
     * @param openId
     * @return
     */
    @ApiOperation(value = "添加班次-问卷调查", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addOpenQuestionDto", value = "课程-问卷调查", required = true, dataType = "AddOpenQuestionDto"),
            @ApiImplicitParam(name = "openId", value = "班次主键", required = true, dataType = "int")
    })
    @PostMapping("/open_question/{openId}")
    public Result addOpenQuestion(@RequestBody AddOpenQuestionDto addOpenQuestionDto, @PathVariable int openId){

        openQuestionService.save(addOpenQuestionDto,openId);
        return Result.success();
    }



    /**
     * 创建问卷的同时发放
     * @param addOpenQuestionDto
     * @param openId
     * @return
     */
    @ApiOperation(value = "创建问卷的同时发放", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addOpenQuestionDto", value = "课程-问卷调查", required = true, dataType = "AddOpenQuestionDto"),
            @ApiImplicitParam(name = "openId", value = "班次主键", required = true, dataType = "int")
    })
    @PostMapping("/publish_open_question/{openId}")
    public Result publishOpenQuestion(@RequestBody AddOpenQuestionDto addOpenQuestionDto, @PathVariable int openId){

        openQuestionService.savePublishOpenQuestion(addOpenQuestionDto,openId);
        return Result.success();
    }



    /**
     * 删除班次-问卷调查管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除班次-问卷调查", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "班次-问卷调查的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/open_question/{id}")
    public Result removeOpenQuestion(@PathVariable("id") int id){

        openQuestionService.remove(id);
        return Result.success();
    }

    /**
     * 修改班次-问卷调查管理
     * @param id
     * @param openQuestion
     * @return
     */
    @ApiOperation(value = "修改班次-问卷调查", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openQuestion", value = "课程-问卷调查", required = true, dataType = "OpenQuestionDto")
    })
    @PutMapping("/open_question/{id}")
    public Result modifyOpenQuestion(@PathVariable("id") int id,
                                     @RequestBody OpenQuestionDto openQuestion){

        OpenQuestion openQuestionEntity = OpenQuestionDtoMapper.INSTANCE.dtoToEntity(openQuestion);
        openQuestionEntity.setId(id);
        openQuestionService.modify(openQuestionEntity);
        return Result.success();
    }


}
