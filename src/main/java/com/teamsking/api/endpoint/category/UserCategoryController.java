package com.teamsking.api.endpoint.category;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.category.UserCategoryDto;
import com.teamsking.api.dto.category.UserCategoryDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.category.UserCategory;
import com.teamsking.domain.service.category.UserCategoryService;
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
@Api(tags = "类别-用户关系管理操作接口")
public class UserCategoryController extends BaseController {


    @Autowired
    UserCategoryService userCategoryService;



    @ApiOperation(value = "类别-用户关系管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/user_categories")
    public Result userCategoryList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<UserCategory> userCategoryList = userCategoryService.list();
        List<UserCategoryDto> userCategoryDtoList = UserCategoryDtoMapper.INSTANCE.entityListToDtoList(userCategoryList);
        return Result.success().addData("pager", warpPage(userCategoryDtoList));

    }


    @ApiOperation(value = "添加类别-用户关系管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "userCategory", value = "类别用户关系管理", required = true, dataType = "UserCategoryDto")
    })
    @PostMapping("/user_category")
    public Result addUserCategory(@RequestBody UserCategoryDto userCategory){

        UserCategory userCategoryEntity = UserCategoryDtoMapper.INSTANCE.dtoToEntity(userCategory);
        userCategoryService.save(userCategoryEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除类别-用户关系管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "类别用户关系管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/user_category/{id}")
    public Result removeUserCategory(@PathVariable int id){

        userCategoryService.remove(id);
        return Result.success();

    }


    @ApiOperation(value = "修改类别-用户关系管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "userCategory", value = "类别用户关系管理", required = true, dataType = "UserCategoryDto")
    })
    @PutMapping("/user_category/{id}")
    public Result modifyUserCategory(@PathVariable int id,
                                     @RequestBody UserCategoryDto userCategory){

        UserCategory userCategoryEntity = UserCategoryDtoMapper.INSTANCE.dtoToEntity(userCategory);
        userCategoryEntity.setId(id);
        userCategoryService.modify(userCategoryEntity);
        return Result.success();

    }


}
