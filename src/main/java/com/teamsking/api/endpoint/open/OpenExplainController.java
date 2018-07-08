package com.teamsking.api.endpoint.open;


import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenExplainDto;
import com.teamsking.api.dto.open.OpenExplainDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenExplain;
import com.teamsking.domain.service.open.OpenExplainService;
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
@Api(tags = "班次-说明管理操作接口")
public class OpenExplainController extends BaseController {

    @Autowired
    OpenExplainService openExplainService;


    @ApiOperation(value = "班次-说明管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_explains")
    public Result list(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenExplain> openExplainList = openExplainService.list();
        List<OpenExplainDto> openExplainDtoList = OpenExplainDtoMapper.INSTANCE.entityListToDtoList(openExplainList);
        return Result.success().addData("pager",warpPage(openExplainDtoList));

    }


    @ApiOperation(value = "添加班次-说明管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openExplain", value = "班次说明管理", required = true, dataType = "OpenExplainDto")
    })
    @PostMapping("/open_explain")
    public Result addOpenExplain(@RequestBody OpenExplainDto openExplain){

        OpenExplain openExplainEntity = OpenExplainDtoMapper.INSTANCE.dtoToEntity(openExplain);
        openExplainService.save(openExplainEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除班次-说明管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班次说明管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/open_explain/{id}")
    public Result removeOpenExplain(@PathVariable int id){

        openExplainService.remove(id);
        return Result.success();


    }


    @ApiOperation(value = "修改班次-说明管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openExplain", value = "班次说明管理", required = true, dataType = "OpenExplainDto")
    })
    @PutMapping("/open_explain/{id}")
    public Result modifyOpenExplain(@PathVariable int id,
                                    @RequestBody OpenExplainDto openExplain){

        OpenExplain openExplainEntity = OpenExplainDtoMapper.INSTANCE.dtoToEntity(openExplain);
        openExplainEntity.setId(id);
        openExplainService.modify(openExplainEntity);
        return Result.success();

    }




}
