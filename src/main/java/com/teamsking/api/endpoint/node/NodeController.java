package com.teamsking.api.endpoint.node;


import com.github.pagehelper.PageHelper;

import com.teamsking.api.dto.node.NodeDto;
import com.teamsking.api.dto.node.NodeDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.node.Node;
import com.teamsking.domain.service.node.NodeService;
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
@Api(tags = "资源管理操作接口")
@Slf4j
public class NodeController extends BaseController {

    @Autowired
    NodeService nodeService;

    @ApiOperation(value = "资源管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/nodes/node_folder/{folderId}/school/{schoolId}/open/{openId}/course/{courseId}")
    public Result nodeList(@PathVariable int courseId,
                           @PathVariable int openId,
                           @PathVariable int folderId,
                           @PathVariable int schoolId,
                           int pageNo,int pageSize){
        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        NodeDto node = new NodeDto();
        node.setCourseId(courseId);
        node.setOpenId(openId);
        node.setSchoolId(schoolId);
        node.setFolderId(folderId);
        Node nodeEntity = NodeDtoMapper.INSTANCE.dtoToEntity(node);
        List<Node> nodeList = nodeService.list(nodeEntity);
        List<NodeDto> nodeDtoList = NodeDtoMapper.INSTANCE.entityListToDtoList(nodeList);
        return Result.success().addData("pager", warpPage(nodeDtoList));


    }

    @ApiOperation(value = "添加资源管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "node", value = "资源管理", required = true, dataType = "NodeDto")
    })
    @PostMapping("/node/node_folder/{folderId}/school/{schoolId}/open/{openId}/course/{courseId}")
    public Result addNode(@PathVariable int courseId,
                          @PathVariable int openId,
                          @PathVariable int folderId,
                          @PathVariable int schoolId,
                          @RequestBody NodeDto node){
        Node nodeEntity = NodeDtoMapper.INSTANCE.dtoToEntity(node);
        nodeEntity.setCourseId(courseId);
        nodeEntity.setOpenId(openId);
        nodeEntity.setSchoolId(schoolId);
        nodeEntity.setFolderId(folderId);
        nodeService.save(nodeEntity);
        return Result.success();

    }

    @ApiOperation(value = "删除资源管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "node", value = "资源管理", required = true, dataType = "NodeDto")
    })
    @DeleteMapping("/node/{id}/node_folder/{folderId}/school/{schoolId}/open/{openId}/course/{courseId}")
    public Result removeNode(@PathVariable int courseId,
                             @PathVariable int id,
                             @PathVariable int openId,
                             @PathVariable int schoolId,
                             @PathVariable int folderId,
                             @RequestBody NodeDto node){

        Node nodeEntity = NodeDtoMapper.INSTANCE.dtoToEntity(node);
        nodeEntity.setCourseId(courseId);
        nodeEntity.setId(id);
        nodeEntity.setOpenId(openId);
        nodeEntity.setSchoolId(schoolId);
        nodeEntity.setFolderId(folderId);
        nodeService.remove(nodeEntity);
        return Result.success();
    }


    @ApiOperation(value = "修改资源管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "node", value = "资源管理", required = true, dataType = "NodeDto")
    })
    @PutMapping("/node/{id}/node_folder/{folderId}/school/{schoolId}/open/{openId}/course/{courseId}")
    public Result modifyNode(@PathVariable int id,
                             @PathVariable int courseId,
                             @PathVariable int openId,
                             @PathVariable int schoolId,
                             @PathVariable int folderId,
                             @RequestBody NodeDto node){

        Node nodeEntity = NodeDtoMapper.INSTANCE.dtoToEntity(node);
        nodeEntity.setCourseId(courseId);
        nodeEntity.setId(id);
        nodeEntity.setSchoolId(schoolId);
        nodeEntity.setOpenId(openId);
        nodeEntity.setFolderId(folderId);
        nodeService.modify(nodeEntity);
        return Result.success();

    }

}
