package com.teamsking.api.endpoint.node;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.node.*;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.node.Node;
import com.teamsking.domain.service.node.NodeService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/nodes")
    public Result nodeList(int pageNo,int pageSize){
        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<Node> nodeList = nodeService.list();
        List<NodeDto> nodeDtoList = NodeDtoMapper.INSTANCE.entityListToDtoList(nodeList);
        return Result.success().addData("pager", warpPage(nodeDtoList));


    }

    @ApiOperation(value = "查询视频信息", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班课id", required = true, dataType = "Integer")
    })
    @GetMapping("/query_node/{openId}")
    public Result queryVideo(@PathVariable int openId){

        return Result.success().addData("list",nodeService.query(openId));

    }




    @ApiOperation(value = "添加资源管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "node", value = "资源管理", required = true, dataType = "NodeDto")
    })
    @PostMapping("/node")
    public Result addNode(@RequestBody NodeDto node){
        Node nodeEntity = NodeDtoMapper.INSTANCE.dtoToEntity(node);
        nodeService.save(nodeEntity);
        return Result.success();

    }

    @ApiOperation(value = "删除资源管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "资源管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/node/{id}")
    public Result removeNode(@PathVariable int id){
        nodeService.remove(id);
        return Result.success();
    }


    @ApiOperation(value = "修改资源管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "node", value = "资源管理", required = true, dataType = "NodeDto")
    })
    @PutMapping("/node/{id}")
    public Result modifyNode(@PathVariable int id,
                             @RequestBody NodeDto node){

        Node nodeEntity = NodeDtoMapper.INSTANCE.dtoToEntity(node);
        nodeEntity.setId(id);
        nodeService.modify(nodeEntity);
        return Result.success();

    }



    @ApiOperation(value = "根据班课id添加视频", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nodeVideoDto", value = "班次视频", required = true, dataType = "NodeVideoDto"),
            @ApiImplicitParam(name = "openId", value = "班次主键", required = true, dataType = "Integer")
    })
    @PostMapping("/add_video_node/{openId}")
    public Result addVideoNodeByOpenId(@RequestBody NodeVideoDto nodeVideoDto,
                                    @PathVariable int openId){

        nodeService.saveVideoByOpenId(nodeVideoDto,openId);

        return Result.success();

    }


    @ApiOperation(value = "根据节id添加视频", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nodeVideoDto", value = "班次视频", required = true, dataType = "NodeVideoDto"),
            @ApiImplicitParam(name = "sectionId", value = "节id", required = true, dataType = "Integer")
    })
    @PostMapping("/save_video_node/{sectionId}")
    public Result addVideoBySectionId(@RequestBody NodeVideoDto nodeVideoDto,
                               @PathVariable int sectionId){

        nodeService.saveVideoBySectionId(nodeVideoDto,sectionId);

        return Result.success();

    }



    @ApiOperation(value = "获取班课视频信息列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int"),
    })
    @GetMapping("/node_video/{openId}")
    public Result getNodeVideoByOpenId(@PathVariable int openId){

        List<Node> nodeVideoDtoList = nodeService.getNodeVideoListByOpenId(openId);
        return Result.success().addData("nodeVideoDtoList",nodeVideoDtoList);
    }




    @ApiOperation(value = "根据班课id获取视频列表(分页)", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openId", value = "班课的id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open/{openId}/video")
    public Result getOpenTestByOpenIdList(@PathVariable int openId, @RequestParam int pageNo, @RequestParam int pageSize){

        Page page = nodeService.getNodeVideoByOpenIdList(openId,fixPage(pageNo),fixPage(pageSize));
        if (null == page){
            return Result.success().addData("pager",null);
        }else {
            return Result.success().addData("pager", warpPage(page));
        }
    }






    @ApiOperation(value = "根据班课id添加课件", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nodeVideoDto", value = "班次课件", required = true, dataType = "NodeVideoDto"),
            @ApiImplicitParam(name = "openId", value = "班次主键", required = true, dataType = "Integer")
    })
    @PostMapping("/add_node_doc/{openId}")
    public Result addNodeDocByOpenId(@RequestBody NodeDocDto nodeDocDto,
                                       @PathVariable int openId){

        nodeService.saveNodeDocByOpenId(nodeDocDto,openId);

        return Result.success();

    }


    @ApiOperation(value = "根据节id添加课件", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nodeVideoDto", value = "班次课件", required = true, dataType = "NodeVideoDto"),
            @ApiImplicitParam(name = "sectionId", value = "节主键", required = true, dataType = "Integer")
    })
    @PostMapping("/save_node_doc/{sectionId}")
    public Result addNodeDocBySectionId(@RequestBody NodeDocDto nodeDocDto,
                                     @PathVariable int sectionId){

        nodeService.saveNodeDocBySectionId(nodeDocDto,sectionId);

        return Result.success();

    }


    @ApiOperation(value = "根据id查询视频信息", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "班课id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "Integer")
    })
    @GetMapping("/node/{id}/open/{openId}/name")
    public Result getNodeNameById(@PathVariable int id,
                                  @PathVariable int openId){

        return Result.success().addData("nodeNameDto",nodeService.getNodeNameById(id,openId));

    }

}
