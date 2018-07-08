package com.teamsking.api.endpoint.tag;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.tag.UserTagDto;
import com.teamsking.api.dto.tag.UserTagDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.tag.UserTag;
import com.teamsking.domain.service.tag.UserTagService;
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
@Api(tags = "标签-用户关系管理操作接口")
public class UserTagController extends BaseController {


    @Autowired
    UserTagService userTagService;


    @ApiOperation(value = "标签-用户关系管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/user_tags")
    public Result userTagList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<UserTag> userTagList = userTagService.list();
        List<UserTagDto> userTagDtoList = UserTagDtoMapper.INSTANCE.entityListToDtoList(userTagList);
        return Result.success().addData("pager", warpPage(userTagDtoList));

    }



    @ApiOperation(value = "添加标签-用户关系管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "userTag", value = "标签用户关系管理", required = true, dataType = "UserTagDto")
    })
    @PostMapping("/user_tag")
    public Result addUserTag(@RequestBody UserTagDto userTag){

        UserTag userTagEntity = UserTagDtoMapper.INSTANCE.dtoToEntity(userTag);
        userTagService.save(userTagEntity);
        return Result.success();

    }



    @ApiOperation(value = "删除标签-用户关系管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "标签用户关系管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/user_tag/{id}")
    public Result removeUserTag(@PathVariable int id){

        userTagService.remove(id);
        return Result.success();

    }


    @ApiOperation(value = "修改标签-用户关系管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "userTag", value = "标签用户关系管理", required = true, dataType = "UserTagDto")
    })
    @PutMapping("/user_tag/{id}")
    public Result modifyUserTag(@PathVariable int id,
                                @RequestBody UserTagDto userTag){

        UserTag userTagEntity = UserTagDtoMapper.INSTANCE.dtoToEntity(userTag);
        userTagEntity.setId(id);
        userTagService.modify(userTagEntity);
        return Result.success();

    }

}
