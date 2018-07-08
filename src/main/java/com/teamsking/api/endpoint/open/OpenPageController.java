package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenPageDto;
import com.teamsking.api.dto.open.OpenPageDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenPage;
import com.teamsking.domain.service.open.OpenPageService;
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
@Api(tags = "班次-页面管理操作接口")
public class OpenPageController extends BaseController {

    @Autowired
    OpenPageService openPageService;


    @ApiOperation(value = "班次-页面管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_pages")
    public Result openPageList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenPage> openPageList = openPageService.list();
        List<OpenPageDto> openPageDtoList = OpenPageDtoMapper.INSTANCE.entityListToDtoList(openPageList);

        return Result.success().addData("pager",warpPage(openPageDtoList));

    }



    @ApiOperation(value = "添加班次-页面管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openPage", value = "班次页面管理", required = true, dataType = "OpenPageDto")
    })
    @PostMapping("/open_page")
    public Result addOpenPage(@RequestBody OpenPageDto openPage){

        OpenPage openPageEntity = OpenPageDtoMapper.INSTANCE.dtoToEntity(openPage);
        openPageService.save(openPageEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除班次-页面管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班次页面管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/open_page/{id}")
    public Result removeOpenPage(@PathVariable int id){

        openPageService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "添加班次-页面管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openPage", value = "班次页面管理", required = true, dataType = "OpenPageDto")
    })
    @PutMapping("/open_page/{id}")
    public Result modifyOpenPage(@PathVariable int id,
                                 @RequestBody OpenPageDto openPage){

        OpenPage openPageEntity = OpenPageDtoMapper.INSTANCE.dtoToEntity(openPage);
        openPageEntity.setId(id);
        openPageService.modify(openPageEntity);
        return Result.success();

    }





}
