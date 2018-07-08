package com.teamsking.api.endpoint.open;


import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenQuestionOptionDto;
import com.teamsking.api.dto.open.OpenQuestionOptionDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenQuestionOption;
import com.teamsking.domain.service.open.OpenQuestionOptionService;
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
@Api(tags = "班次-问题调查选项管理操作接口")
public class OpenQuestionOptionController extends BaseController {

    @Autowired
    OpenQuestionOptionService openQuestionOptionService;


    @ApiOperation(value = "班次-问题调查选项管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
        @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_question_options")
    public Result openQuestionOptionList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenQuestionOption> openQuestionOptionList = openQuestionOptionService.list();
        List<OpenQuestionOptionDto> openQuestionOptionDtoList = OpenQuestionOptionDtoMapper.INSTANCE.entityListToDtoList(openQuestionOptionList);
        return Result.success().addData("pager",warpPage(openQuestionOptionDtoList));

    }


    @ApiOperation(value = "添加班次-问题调查选项管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openQuestionOption", value = "班次问题调查选项管理", required = true, dataType = "OpenQuestionOptionDto")
    })
    @PostMapping("/open_question_option")
    public Result addOpenQuestionOption(@RequestBody OpenQuestionOptionDto openQuestionOption){

        OpenQuestionOption openQuestionOptionEntity = OpenQuestionOptionDtoMapper.INSTANCE.dtoToEntity(openQuestionOption);
        openQuestionOptionService.save(openQuestionOptionEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除班次-问题调查选项管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班次问题调查选项管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/open_question_option/{id}")
    public Result removeOpenQuestionOption(@PathVariable int id){

        openQuestionOptionService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "修改班次-问题调查选项管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openQuestionOption", value = "班次问题调查选项管理", required = true, dataType = "OpenQuestionOptionDto")
    })
    @PutMapping("/open_question_option/{id}")
    public Result modifyOpenQuestionOption(@PathVariable int id,
                                           @RequestBody OpenQuestionOptionDto openQuestionOption){

        OpenQuestionOption openQuestionOptionEntity = OpenQuestionOptionDtoMapper.INSTANCE.dtoToEntity(openQuestionOption);
        openQuestionOptionEntity.setId(id);
        openQuestionOptionService.modify(openQuestionOptionEntity);
        return Result.success();

    }


}
