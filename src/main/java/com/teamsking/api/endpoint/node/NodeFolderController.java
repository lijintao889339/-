package com.teamsking.api.endpoint.node;

import com.github.pagehelper.PageHelper;

import com.teamsking.api.dto.node.NodeFolderDto;
import com.teamsking.api.dto.node.NodeFolderDtoMapper;

import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.node.NodeFolder;

import com.teamsking.domain.service.node.NodeFolderService;
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
@Api(tags = "资源目录管理操作接口")
@Slf4j
public class NodeFolderController extends BaseController {

    @Autowired
    NodeFolderService nodeFolderService;


    @ApiOperation(value = "资源目录管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/node_folders")
    public Result nodeFolderList(int pageNo,int pageSize){
        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));

        List<NodeFolder> nodeFolderList = nodeFolderService.list();
        List<NodeFolderDto> nodeFolderDtoList = NodeFolderDtoMapper.INSTANCE.entityListToDtoList(nodeFolderList);
        return Result.success().addData("pager", warpPage(nodeFolderDtoList));

    }


    @ApiOperation(value = "添加资源目录管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "nodeFolder", value = "资源目录管理", required = true, dataType = "NodeFolderDto")
    })
    @PostMapping("/node_folder")
    public Result addNodeFolder(@RequestBody NodeFolderDto nodeFolder){

        NodeFolder nodeFolderEntity = NodeFolderDtoMapper.INSTANCE.dtoToEntity(nodeFolder);
        nodeFolderService.save(nodeFolderEntity);
        return Result.success();

    }

    @ApiOperation(value = "修改资源目录管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "资源目录管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/node_folder/{id}")
    public Result removeNodeFolder(@PathVariable int id){

        nodeFolderService.remove(id);
        return Result.success();

    }


    @ApiOperation(value = "修改资源目录管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "nodeFolder", value = "资源目录管理", required = true, dataType = "NodeFolderDto")
    })
    @PutMapping("/node_folder/{id}")
    public Result modifyNodeFolder(@PathVariable int id,
                                   @RequestBody NodeFolderDto nodeFolder){
        NodeFolder nodeFolderEntity = NodeFolderDtoMapper.INSTANCE.dtoToEntity(nodeFolder);
        nodeFolderEntity.setId(id);
        nodeFolderService.modify(nodeFolderEntity);
        return Result.success();

    }

}
