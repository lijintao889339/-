package com.teamsking.api.endpoint.category;

import com.teamsking.api.dto.category.CategoryDtoMapper;
import com.teamsking.api.dto.category.EditCourseCategoryDto;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@Api(tags = "课程分类更新操作接口")
public class EditCourseCategoryController extends BaseController {

    @Autowired
    CategoryService categoryService;


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

}
