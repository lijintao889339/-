package com.teamsking.api.endpoint.tag;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.tag.TagDto;
import com.teamsking.api.dto.tag.TagDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.tag.Tag;
import com.teamsking.domain.service.tag.TagService;
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
@Api(tags = "标签管理操作接口")
public class TagController extends BaseController {

    @Autowired
    TagService tagService;


    @ApiOperation(value = "标签管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/tags")
    public Result tagList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<Tag> tagList = tagService.list();
        List<TagDto> tagDtoList = TagDtoMapper.INSTANCE.entityListToDtoList(tagList);
        return Result.success().addData("pager", warpPage(tagDtoList));

    }



    @ApiOperation(value = "添加标签管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "tag", value = "标签管理", required = true, dataType = "TagDto")
    })
    @PostMapping("/tag")
    public Result addTag(@RequestBody TagDto tag){

        Tag tagEntity = TagDtoMapper.INSTANCE.dtoToEntity(tag);
        tagService.save(tagEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除标签管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "标签管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/tag/{id}")
    public Result removeTag(@PathVariable int id){

        tagService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "修改标签管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "tag", value = "标签管理", required = true, dataType = "TagDto")
    })
    @PutMapping("/tag/{id}")
    public Result modifyTag(@PathVariable int id,
                            @RequestBody TagDto tag){

        Tag tagEntity = TagDtoMapper.INSTANCE.dtoToEntity(tag);
        tagEntity.setId(id);
        tagService.modify(tagEntity);
        return Result.success();


    }


}
