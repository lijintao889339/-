package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenChapterDto;
import com.teamsking.api.dto.open.OpenChapterDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenChapter;
import com.teamsking.domain.service.open.OpenChapterService;
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

/**
*@author linhao
*/
@RestController
@Slf4j
@Api(tags = "班次-章管理操作接口")
public class OpenChapterController extends BaseController {

    @Autowired
    OpenChapterService openChapterService;

    /**
     * 获取班次-章管理的列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "班次-章列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_chapters")
    public Result openChapterList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenChapter> openChapterList = openChapterService.list();
        List<OpenChapterDto> openChapterDtoList = OpenChapterDtoMapper.INSTANCE.entityListToDtoList(openChapterList);
        return Result.success().addData("pager",warpPage(openChapterDtoList));
    }

    /**
     * 添加班次-章管理
     * @param openChapter
     * @return
     */
    @ApiOperation(value = "添加班次—章", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openChapter", value = "班次-章", required = true, dataType = "OpenChapterDto")
    })
    @PostMapping("/open_chapter")
    public Result addOpenChapter(@RequestBody OpenChapterDto openChapter){

        OpenChapter openChapterEntity = OpenChapterDtoMapper.INSTANCE.dtoToEntity(openChapter);
        openChapterService.save(openChapterEntity);
        return Result.success();
    }

    /**
     * 删除班次-章管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除班次—章", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "班次-章的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/open_chapter/{id}")
    public Result removeOpenChapter(@PathVariable("id") int id){

        openChapterService.remove(id);
        return Result.success();
    }

    /**
     * 修改班次-章管理
     * @param id
     * @param openChapter
     * @return
     */
    @ApiOperation(value = "修改班次-章", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openChapter", value = "班次-章", required = true, dataType = "OpenChapterDto")
    })
    @PutMapping("/open_chapter/{id}")
    public Result modifyOpenChapter(@PathVariable("id") int id,
                                    @RequestBody OpenChapterDto openChapter){

        OpenChapter openChapterEntity = OpenChapterDtoMapper.INSTANCE.dtoToEntity(openChapter);
        openChapterEntity.setId(id);
        openChapterService.modify(openChapterEntity);
        return Result.success();
    }

}
