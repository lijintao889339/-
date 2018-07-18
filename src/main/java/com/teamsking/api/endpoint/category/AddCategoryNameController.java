package com.teamsking.api.endpoint.category;

import com.teamsking.api.dto.category.AddCategoryNameDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "添加二级分类接口")
public class AddCategoryNameController extends BaseController {


    @Autowired
    CategoryService categoryService;


    @ApiOperation(value = "添加二级分类", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "addCourseCategory", value = "添加二级分类", required = true, dataType = "AddCourseCategoryDto")
    })
    @PostMapping("/add_category_name")
    public Result addCategoryName(@RequestBody AddCategoryNameDto addCategoryName){

        Category categoryEntity = CategoryDtoMapper.INSTANCE.dtoToEntity2(addCategoryName);
        categoryService.save(categoryEntity);
        return Result.success();

    }

}
