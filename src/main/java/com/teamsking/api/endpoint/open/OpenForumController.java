package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenForumDto;
import com.teamsking.api.dto.open.OpenForumDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenForum;
import com.teamsking.domain.service.open.OpenForumService;
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
@Api(tags = "班次-讨论管理操作接口")
public class OpenForumController extends BaseController {

    @Autowired
    OpenForumService openForumService;


    @ApiOperation(value = "班次-讨论管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_forums")
    public Result openForumList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenForum> openForumList = openForumService.list();
        List<OpenForumDto> openForumDtoList = OpenForumDtoMapper.INSTANCE.entityListToDtoList(openForumList);
        return Result.success().addData("pager",warpPage(openForumDtoList));

    }



    @ApiOperation(value = "添加班次-讨论管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openForum", value = "班次讨论管理", required = true, dataType = "OpenForumDto")
    })
    @PostMapping("/open_forum")
    public Result addOpenForum(@RequestBody OpenForumDto openForum){

        OpenForum openForumEntity = OpenForumDtoMapper.INSTANCE.dtoToEntity(openForum);

        openForumService.save(openForumEntity);
        return Result.success();

    }



    @ApiOperation(value = "删除班次-讨论管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班次讨论管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("open_forum/{id}")
    public Result removeOpenForum(@PathVariable int id){

        openForumService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "修改班次-讨论管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openForum", value = "班次讨论管理", required = true, dataType = "OpenForumDto")
    })
    @PutMapping("/open_forum/{id}")
    public Result modifyOpenForum(@PathVariable int id,
                                  @RequestBody OpenForumDto openForum){

        OpenForum openForumEntity = OpenForumDtoMapper.INSTANCE.dtoToEntity(openForum);
        openForumEntity.setId(id);
        openForumService.modify(openForumEntity);
        return Result.success();

    }


}
