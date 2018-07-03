package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenQuestionSubjectDto;
import com.teamsking.api.dto.open.OpenQuestionSubjectDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenQuestionSubject;
import com.teamsking.domain.service.open.OpenQuestionSubjectService;
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
@Api(tags = "班次-问卷调查题干管理操作接口")
public class OpenQuestionSubjectController extends BaseController {

    @Autowired
    OpenQuestionSubjectService openQuestionSubjectService;

    /**
     * 获取班次-问卷调查题干管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "班次-问卷调查题干管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_question_subjects")
    public Result OpenQuestionSubjectList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenQuestionSubject> openQuestionSubjectList = openQuestionSubjectService.list();
        List<OpenQuestionSubjectDto> openQuestionSubjectDtoList = OpenQuestionSubjectDtoMapper.INSTANCE.entityListToDtoList(openQuestionSubjectList);
        return Result.success().addData("pager",warpPage(openQuestionSubjectDtoList));
    }

    /**
     * 添加班次-问卷调查题干管理
     * @param openQuestionSubject
     * @return
     */
    @ApiOperation(value = "添加班次-问卷调查题干", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openQuestionSubject", value = "班次-问卷调查题干", required = true, dataType = "OpenQuestionSubjectDto")
    })
    @PostMapping("/open_question_subject")
    public Result addOpenQuestionSubject(@RequestBody OpenQuestionSubjectDto openQuestionSubject){

        OpenQuestionSubject openQuestionSubjectList = OpenQuestionSubjectDtoMapper.INSTANCE.dtoToEntity(openQuestionSubject);
        openQuestionSubjectService.save(openQuestionSubjectList);
        return Result.success();
    }

    /**
     * 删除班次-问卷调查题干管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除班次-问卷调查题干", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "班次-问卷调查题干的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/open_question_subject/{id}")
    public Result removeOpenQuestionSubject(@PathVariable("id") int id){

        openQuestionSubjectService.remove(id);
        return Result.success();
    }

    @ApiOperation(value = "修改班次-问卷调查题干", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openQuestionSubject", value = "课程-问卷调查题干", required = true, dataType = "OpenQuestionSubjectDto")
    })
    @PutMapping("/open_question_subject/{id}")
    public Result modify(@PathVariable("id") int id,
                         @RequestBody OpenQuestionSubjectDto openQuestionSubject){

        OpenQuestionSubject openQuestionSubjectList = OpenQuestionSubjectDtoMapper.INSTANCE.dtoToEntity(openQuestionSubject);
        openQuestionSubjectList.setId(id);
        openQuestionSubjectService.modify(openQuestionSubjectList);
        return Result.success();
    }

}
