package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenQuestionDto;
import com.teamsking.api.dto.open.OpenQuestionDtoMapper;
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
     * 获取班次-问卷调查管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "班次-问卷调查管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_questions")
    public Result openQuestionList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenQuestion> openQuestionList = openQuestionService.list();
        List<OpenQuestionDto> openSectionDtoList = OpenQuestionDtoMapper.INSTANCE.entityListToDtoList(openQuestionList);
        return Result.success().addData("pager",warpPage(openSectionDtoList));

    }

    /**
     * 添加班次-问卷调查管理
     * @param openQuestion
     * @return
     */
    @ApiOperation(value = "添加班次-问卷调查", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openQuestion", value = "课程-问卷调查", required = true, dataType = "OpenQuestionDto")
    })
    @PostMapping("/open_question")
    public Result addOpenQuestion(@RequestBody OpenQuestionDto openQuestion){

        OpenQuestion openQuestionEntity = OpenQuestionDtoMapper.INSTANCE.dtoToEntity(openQuestion);
        openQuestionService.save(openQuestionEntity);
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
