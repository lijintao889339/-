package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenItemDto;
import com.teamsking.api.dto.open.OpenItemDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenItem;
import com.teamsking.domain.service.open.OpenItemService;
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
@Api(tags = "班次项管理操作接口")
public class OpenItemController extends BaseController {

    @Autowired
    OpenItemService openItemService;


    @ApiOperation(value = "班次-项管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_items")
    public Result openItemList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenItem> openItemList = openItemService.list();
        List<OpenItemDto> openItemDtoList = OpenItemDtoMapper.INSTANCE.entityListToDtoList(openItemList);
        return Result.success().addData("pager",warpPage(openItemDtoList));

    }



    @ApiOperation(value = "添加班次-项管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openItem", value = "班次项管理", required = true, dataType = "OpenItemDto")
    })
    @PostMapping("/open_item")
    public Result addOpenItem(@RequestBody OpenItemDto openItem){

        OpenItem openItemEntity = OpenItemDtoMapper.INSTANCE.dtoToEntity(openItem);
        openItemService.save(openItemEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除班次-项管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班次项管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/open_item/{id}")
    public Result removeOpenItem(@PathVariable int id){

        openItemService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "修改班次-项管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openItem", value = "班次项管理", required = true, dataType = "OpenItemDto")
    })
    @PutMapping("open_item/{id}")
    public Result modifyOpenItem(@PathVariable int id,
                                 @RequestBody OpenItemDto openItem){

        OpenItem openItemEntity = OpenItemDtoMapper.INSTANCE.dtoToEntity(openItem);
        openItemEntity.setId(id);
        openItemService.modify(openItemEntity);

        return Result.success();

    }


}
