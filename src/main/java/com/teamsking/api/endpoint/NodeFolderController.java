package com.teamsking.api.endpoint;

import afu.org.checkerframework.checker.igj.qual.I;
import com.github.pagehelper.PageHelper;

import com.teamsking.api.dto.NodeFolderDto;
import com.teamsking.api.dto.NodeFolderDtoMapper;

import com.teamsking.domain.entity.NodeFolder;

import com.teamsking.domain.service.NodeFolderService;
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
public class NodeFolderController extends BaseController{

    @Autowired
    NodeFolderService nodeFolderService;


    @ApiOperation(value = "资源目录管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/parent/{parentId}/node_folders/open/{openId}/course/{courseId}")
    public Result nodeFolderList(@PathVariable int courseId,
                                 @PathVariable int openId,
                                 @PathVariable int parentId,
                                 int pageNo,int pageSize){
        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        NodeFolderDto nodeFolder = new NodeFolderDto();
        nodeFolder.setCourseId(courseId);
        nodeFolder.setOpenId(openId);
        nodeFolder.setParentId(parentId);
        NodeFolder nodeFolderEntity = NodeFolderDtoMapper.INSTANCE.dtoToEntity(nodeFolder);
        List<NodeFolder> nodeFolderList = nodeFolderService.list(nodeFolderEntity);
        List<NodeFolderDto> nodeFolderDtoList = NodeFolderDtoMapper.INSTANCE.entityListToDtoList(nodeFolderList);
        return Result.success().addData("pager", warpPage(nodeFolderDtoList));

    }


    @ApiOperation(value = "添加资源目录管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "nodeFolder", value = "资源目录管理", required = true, dataType = "NodeFolderDto")
    })
    @PostMapping("/parent/{parentId}/node_folder/open/{openId}/course/{courseId}")
    public Result addNodeFolder(@PathVariable int courseId,
                                @PathVariable int openId,
                                @PathVariable int parentId,
                                @RequestBody NodeFolderDto nodeFolder){
        NodeFolder nodeFolderEntity = NodeFolderDtoMapper.INSTANCE.dtoToEntity(nodeFolder);
        nodeFolderEntity.setCourseId(courseId);
        nodeFolderEntity.setParentId(parentId);
        nodeFolderEntity.setOpenId(openId);
        nodeFolderService.save(nodeFolderEntity);
        return Result.success();

    }

    @ApiOperation(value = "修改资源目录管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "nodeFolder", value = "资源目录管理", required = true, dataType = "NodeFolderDto")
    })
    @DeleteMapping("/parent/{parentId}/node_folder/{id}/open/{openId}/course/{courseId}")
    public Result removeNodeFolder(@PathVariable int courseId,
                                   @PathVariable int id,
                                   @PathVariable int openId,
                                   @PathVariable int parentId,
                                   @RequestBody NodeFolderDto nodeFolder){

        NodeFolder nodeFolderEntity = NodeFolderDtoMapper.INSTANCE.dtoToEntity(nodeFolder);
        nodeFolderEntity.setCourseId(courseId);
        nodeFolderEntity.setParentId(parentId);
        nodeFolderEntity.setId(id);
        nodeFolderEntity.setOpenId(openId);
        nodeFolderService.remove(nodeFolderEntity);
        return Result.success();

    }


    @ApiOperation(value = "修改资源目录管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "nodeFolder", value = "资源目录管理", required = true, dataType = "NodeFolderDto")
    })
    @PutMapping("/parent/{parentId}/node_folder/{id}/open/{openId}/course/{courseId}")
    public Result modifyNodeFolder(@PathVariable int courseId,
                                   @PathVariable int openId,
                                   @PathVariable int id,
                                   @PathVariable int parentId,
                                   @RequestBody NodeFolderDto nodeFolder){
        NodeFolder nodeFolderEntity = NodeFolderDtoMapper.INSTANCE.dtoToEntity(nodeFolder);
        nodeFolderEntity.setCourseId(courseId);
        nodeFolderEntity.setParentId(parentId);
        nodeFolderEntity.setId(id);
        nodeFolderEntity.setOpenId(openId);
        nodeFolderService.modify(nodeFolderEntity);
        return Result.success();

    }

}
