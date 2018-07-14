package com.teamsking.api.endpoint.category;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.category.AddCourseCategoryDto;
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
        List<AddCourseCategoryDto> categoryDtoList = CategoryDtoMapper.INSTANCE.entityListToDtoList1(categoryList);
        return Result.success().addData("pager", warpPage(categoryDtoList));

    }


    @ApiOperation(value = "创建课程分类", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "addCourseCategory", value = "创建课程分类", required = true, dataType = "AddCourseCategoryDto")
    })
    @PostMapping("/category")
    public Result addCategory(@RequestBody AddCourseCategoryDto addCourseCategory){

        Category categoryEntity = CategoryDtoMapper.INSTANCE.dtoToEntity1(addCourseCategory);
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
