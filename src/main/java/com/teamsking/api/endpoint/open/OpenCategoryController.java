package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenCategoryDto;
import com.teamsking.api.dto.open.OpenCategoryDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenCategory;
import com.teamsking.domain.service.open.OpenCategoryService;
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
@Api(tags = "班次-科学管理操作接口")
public class OpenCategoryController extends BaseController {

    @Autowired
    OpenCategoryService openCategoryService;


    @ApiOperation(value = "班次-科学管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_categories")
    public Result openCategoryList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));

        List<OpenCategory> openCategoryList = openCategoryService.list();

        List<OpenCategoryDto> openCategoryDtoList = OpenCategoryDtoMapper.INSTANCE.entityListToDtoList(openCategoryList);

        return Result.success().addData("pager", warpPage(openCategoryDtoList));

    }


    @ApiOperation(value = "添加班次-科学管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openCategory", value = "班次科学管理", required = true, dataType = "OpenCategoryDto")
    })
    @PostMapping("/open_category")
    public Result addOpenCategory(@RequestBody OpenCategoryDto openCategory){

        OpenCategory openCategoryEntity = OpenCategoryDtoMapper.INSTANCE.dtoToEntity(openCategory);
        openCategoryService.save(openCategoryEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除班次-科学管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班次科学管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/open_category/{id}")
    public Result removeOpenCategory(@PathVariable int id){

        openCategoryService.remove(id);

        return Result.success();

    }


    @ApiOperation(value = "修改班次-科学管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openCategory", value = "班次科学管理", required = true, dataType = "OpenCategoryDto")
    })
    @PutMapping("/open_category/{id}")
    public Result modifyOpenCategory(@PathVariable int id,
                                     @RequestBody OpenCategoryDto openCategory){
        OpenCategory openCategoryEntity = OpenCategoryDtoMapper.INSTANCE.dtoToEntity(openCategory);
        openCategoryEntity.setId(id);
        openCategoryService.modify(openCategoryEntity);

        return Result.success();

    }




}
