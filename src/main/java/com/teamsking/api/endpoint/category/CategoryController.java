package com.teamsking.api.endpoint.category;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.category.*;
import com.teamsking.api.dto.open.OpenDto;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.category.Category;
import com.teamsking.domain.entity.open.Open;
import com.teamsking.domain.service.category.CategoryService;
import com.teamsking.domain.service.open.OpenService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "类别管理操作接口")
public class CategoryController extends BaseController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    OpenService openService;


    @ApiOperation(value = "课程类别列表", produces = "application/json")

    @GetMapping("/categories")
    public Result categoryList(){

        List<CategoryListViewDto> categoryList = categoryService.getAllCategory();
        return Result.success().addData("pager", categoryList);

    }


    @ApiOperation(value = "创建课程分类接口", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "addCourseCategory", value = "创建课程分类", required = true, dataType = "AddCourseCategoryDto")
    })
    @PostMapping("/category")
    public Result addCategory(@RequestBody AddCourseCategoryDto addCourseCategory){

        Category categoryEntity = CategoryDtoMapper.INSTANCE.dtoToEntity1(addCourseCategory);
        categoryService.saveFirstLabel(categoryEntity);
        return Result.success();

    }



    @ApiOperation(value = "添加二级分类", produces= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "一级分类的主键", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "addCategoryNameDto", value = "添加二级分类", required = true, dataType = "AddCategoryNameDto")
    })
    @PostMapping("/add_category_name/{id}")
    public Result addCategoryName(@RequestBody AddCategoryNameDto addCategoryNameDto,
                                  @PathVariable("id") int id){

        Category categoryEntity = CategoryDtoMapper.INSTANCE.dtoToEntity2(addCategoryNameDto);
        categoryEntity.setParentId(id);
        categoryService.saveSecondLabel(categoryEntity);
        return Result.success();

    }


    @ApiOperation(value = "课程分类更新接口", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "editCourseCategory", value = "课程分类更新接口", required = true, dataType = "EditCourseCategoryDto")
    })
    @PutMapping("/edit_course_category/{id}")
    public Result modifyEditCourseCategory(@PathVariable int id,
                                           @RequestBody EditCourseCategoryDto editCourseCategory){

        Category categoryEntity = CategoryDtoMapper.INSTANCE.dtoToEntity3(editCourseCategory);
        categoryEntity.setId(id);
        categoryService.modify(categoryEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除课程分类",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "课程分类的主键", required = true, paramType = "query", dataType = "int")
    })
    @DeleteMapping("/category/{id}")
    public Result removeFirstCategory(@PathVariable int id){

        categoryService.removeCategoryById(id);
        return Result.success();
    }


    @ApiOperation(value = "查看课程分类下面的班课", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", paramType = "query", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "id", value = "分类的主键", required = true, dataType = "int")
    })
    @GetMapping("/category/{id}/opens")
    public Result getCategoryOpens(@RequestParam int pageNo, @RequestParam int pageSize, @PathVariable int id){

        return Result.success().addData("pager",warpPage(categoryService.getCategoryOpensById(fixPage(pageNo),fixPage(pageSize),id)));
    }

    @ApiOperation(value = "一级分类列表", consumes = "application/json")
    @GetMapping("/first_categories")
    public Result getFirstCategory(){

        return Result.success().addData("pager",categoryService.getAllFirstCategory());
    }

    @ApiOperation(value = "二级分类列表", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "分类的主键", required = true, dataType = "int")
    })
    @GetMapping("/second_categories/{id}")
    public Result getSecondCategory(@PathVariable("id") int id){

        return Result.success().addData("pager",categoryService.getSecondCategoryById(id));
    }

}
