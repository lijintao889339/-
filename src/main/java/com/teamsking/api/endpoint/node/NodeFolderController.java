package com.teamsking.api.endpoint.node;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.node.NodeFolderDto;
import com.teamsking.api.dto.node.NodeFolderDtoMapper;
import com.teamsking.api.dto.node.NodeFolderSelDto;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.node.NodeFolder;
import com.teamsking.domain.service.node.NodeFolderService;
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

    @ApiOperation(value = "删除资源目录管理", consumes= "application/json")
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



    @ApiOperation(value = "批量删除课件资源", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "Integer")
    })
    @DeleteMapping("/node_folder")
    public Result removeNodeFolderByIds(@RequestParam Integer[] ids){

        nodeFolderService.removeByIds(ids);

        return Result.success();

    }






    @ApiOperation(value = "根据openId查询课件资源视频库一级目录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openId", value = "班课id", required = true, dataType = "Integer")
    })
    @GetMapping("/video_open_folders/{openId}")
    public Result getNodeFolderList(@PathVariable int openId){

        return Result.success().addData("nodeFolderSelDtoList",nodeFolderService.getNodeFolderListByOpenId(openId));

    }


    @ApiOperation(value = "根据一级目录id查询课件资源视频库子目录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "一级目录id", required = true, dataType = "Integer")
            //@ApiImplicitParam(name = "openId", value = "班课id", required = true, dataType = "Integer")
    })
    @GetMapping("/video/id/open_folders")
    public Result getTwoVideoListById(//@PathVariable int openId,
                                      @PathVariable int id){

        return Result.success().addData("nodeFolderSelDtoList",nodeFolderService.getTwoVideoListById(id));

    }



    @ApiOperation(value = "根据openId查询课件资源文档库一级目录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openId", value = "班课id", required = true, dataType = "Integer")
    })
    @GetMapping("/doc_open_folders/{openId}")
    public Result getOneDocListByOpenId(@PathVariable int openId){

        return Result.success().addData("nodeFolderSelDtoList",nodeFolderService.getOneDocListByOpenId(openId));

    }

    @ApiOperation(value = "根据一级目录id查询课件资源文档库子目录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "一级目录id", required = true, dataType = "Integer")
            //@ApiImplicitParam(name = "openId", value = "班课id", required = true, dataType = "Integer")
    })
    @GetMapping("/doc/{id}/open_folders")
    public Result getTwoDocListById(//@PathVariable int openId,
                                      @PathVariable int id){

        return Result.success().addData("nodeFolderSelDtoList",nodeFolderService.getTwoDocListById(id));

    }



    @ApiOperation(value = "根据openId查询课件资源习题库一级目录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openId", value = "班课id", required = true, dataType = "Integer")
    })
    @GetMapping("/quiz_open_folders/{openId}")
    public Result getOneQuizListByOpenId(@PathVariable int openId){

        return Result.success().addData("nodeFolderSelDtoList",nodeFolderService.getOneQuizListByOpenId(openId));

    }

    @ApiOperation(value = "根据一级目录id查询课件资源习题库子目录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "一级目录id", required = true, dataType = "Integer")
            //@ApiImplicitParam(name = "openId", value = "班课id", required = true, dataType = "Integer")
    })
    @GetMapping("/quiz/{id}/open_folders")
    public Result getTwoQuizListById(//@PathVariable int openId,
                                    @PathVariable int id){

        return Result.success().addData("nodeFolderSelDtoList",nodeFolderService.getTwoQuizListById(id));

    }



    @ApiOperation(value = "创建一级视频库目录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "nodeFolder", value = "创建视频库目录", required = true, dataType = "NodeFolder")
    })
    @PostMapping("/fist_video_node_folder")
    public Result saveFistVideo(@RequestBody NodeFolder nodeFolder){

        nodeFolderService.saveFirstVideo(nodeFolder);
        return Result.success();

    }

    @ApiOperation(value = "创建视频库子目录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "nodeFolder", value = "创建视频库目录", required = true, dataType = "NodeFolder")
    })
    @PostMapping("/two_video_node_folder/{id}")
    public Result saveTwoVideo(@RequestBody NodeFolder nodeFolder,
                               @PathVariable int id){

        nodeFolderService.saveTwoVideo(nodeFolder,id);
        return Result.success();

    }




    @ApiOperation(value = "创建一级文档库目录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "nodeFolder", value = "创建文档库目录", required = true, dataType = "NodeFolder")
    })
    @PostMapping("/fist_doc_node_folder")
    public Result saveFistDoc(@RequestBody NodeFolder nodeFolder){

        nodeFolderService.saveFirstDoc(nodeFolder);
        return Result.success();

    }

    @ApiOperation(value = "创建文档库子目录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "nodeFolder", value = "创建文档库目录", required = true, dataType = "NodeFolder")
    })
    @PostMapping("/two_doc_node_folder/{id}")
    public Result saveTwoDoc(@RequestBody NodeFolder nodeFolder,
                               @PathVariable int id){

        nodeFolderService.saveTwoDoc(nodeFolder,id);
        return Result.success();

    }




    @ApiOperation(value = "创建一级习题库目录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "nodeFolder", value = "创建习题库目录", required = true, dataType = "NodeFolder")
    })
    @PostMapping("/fist_quiz_node_folder")
    public Result saveFistQuiz(@RequestBody NodeFolder nodeFolder){

        nodeFolderService.saveFirstQuiz(nodeFolder);
        return Result.success();

    }

    @ApiOperation(value = "创建习题库子目录", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "nodeFolder", value = "创建文档库目录", required = true, dataType = "NodeFolder")
    })
    @PostMapping("/two_quiz_node_folder/{id}")
    public Result saveTwoQuiz(@RequestBody NodeFolder nodeFolder,
                             @PathVariable int id){

        nodeFolderService.saveTwoQuiz(nodeFolder,id);
        return Result.success();

    }




}
