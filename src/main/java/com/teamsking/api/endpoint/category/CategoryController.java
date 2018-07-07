package com.teamsking.api.endpoint.category;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.category.CategoryDto;
import com.teamsking.api.dto.category.CategoryDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.category.Category;
import com.teamsking.domain.service.category.CategoryService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "类别管理操作接口")
public class CategoryController extends BaseController {

    @Autowired
    CategoryService categoryService;


    @ApiOperation(value = "类别管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/categories")
    public Result categoryList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<Category> categoryList = categoryService.list();
        List<CategoryDto> categoryDtoList = CategoryDtoMapper.INSTANCE.entityListToDtoList(categoryList);
        return Result.success().addData("pager", warpPage(categoryDtoList));

    }


    @ApiOperation(value = "添加类别管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "category", value = "类别管理", required = true, dataType = "CategoryDto")
    })
    @PostMapping("/category")
    public Result addCategory(@RequestBody CategoryDto category){

        Category categoryEntity = CategoryDtoMapper.INSTANCE.dtoToEntity(category);
        categoryService.save(categoryEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除类别管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "类别管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/category/{id}")
    public Result removeCategory(@PathVariable int id){

        categoryService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "修改类别管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "category", value = "类别管理", required = true, dataType = "CategoryDto")
    })
    @PutMapping("/category/{id}")
    public Result modifyCategory(@PathVariable int id,
                                 @RequestBody CategoryDto category){

        Category categoryEntity = CategoryDtoMapper.INSTANCE.dtoToEntity(category);
        categoryEntity.setId(id);
        categoryService.modify(categoryEntity);
        return Result.success();

    }



}
