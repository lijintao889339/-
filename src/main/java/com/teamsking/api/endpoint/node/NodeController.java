package com.teamsking.api.endpoint.node;


import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.node.NodeDto;
import com.teamsking.api.dto.node.NodeDtoMapper;
import com.teamsking.api.dto.node.NodeVideoDto;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
            @ApiImplicitParam(name = "nodeVideoDto", value = "班次视频", required = true, dataType = "NodeVideoDto")
    })
    @PostMapping("/add_video_node/{openId}")
    public Result addVideoNode(@RequestBody NodeVideoDto nodeVideoDto,
                                    @PathVariable int openId){

        nodeService.saveVideo(nodeVideoDto,openId);

        return Result.success();

    }

}
