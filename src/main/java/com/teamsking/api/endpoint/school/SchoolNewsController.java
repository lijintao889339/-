package com.teamsking.api.endpoint.school;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.school.SchoolNewsDto;
import com.teamsking.api.dto.school.SchoolNewsDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.school.SchoolNews;
import com.teamsking.domain.service.school.SchoolNewsService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*@author linhao
*/
@RestController
@Slf4j
@Api(tags = "学校-新闻管理操作接口")
public class SchoolNewsController extends BaseController {

    @Autowired
    SchoolNewsService schoolNewsService;

    /**
     * 获取学校-新闻管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "学校-新闻管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value ="页码", required = true, example = "1") ,
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/school_newses")
    public Result schoolNewsList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<SchoolNews> schoolNewsList = schoolNewsService.list();
        List<SchoolNewsDto> schoolNewsDtoList = SchoolNewsDtoMapper.INSTANCE.entityListToDtoList(schoolNewsList);
        return Result.success().addData("pager",warpPage(schoolNewsDtoList));
    }

    /**
     * 添加学校-新闻管理
     * @param schoolNews
     * @return
     */
    @ApiOperation(value = "添加学校-新闻管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "schoolNews", value = "学校-新闻", required = true, dataType = "SchoolNewsDto")
    })
    @PostMapping("/school_news")
    public Result addSchoolNews(@RequestBody SchoolNewsDto schoolNews){

        SchoolNews schoolNewsEntity = SchoolNewsDtoMapper.INSTANCE.dtoToEntity(schoolNews);
        schoolNewsService.save(schoolNewsEntity);
        return Result.success();
    }

    /**
     * 删除学校-新闻管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除学校-新闻管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学校-新闻的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/school_news/{id}")
    public Result removeSchoolNews(@PathVariable("id") int id){

        schoolNewsService.remove(id);
        return Result.success();
    }

    /**
     * 修改学校-新闻管理
     * @param schoolNews
     * @param id
     * @return
     */
    @ApiOperation(value = "修改学校-新闻管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "schoolNews", value = "学校-新闻", required = true, dataType = "SchoolNewsDto")
    })
    @PutMapping("/school_news/{id}")
    public Result modifySchoolNews(@RequestBody SchoolNewsDto schoolNews,
                                      @PathVariable("id") int id){

        SchoolNews schoolNewsEntity = SchoolNewsDtoMapper.INSTANCE.dtoToEntity(schoolNews);
        schoolNewsEntity.setId(id);
        schoolNewsService.modify(schoolNewsEntity);
        return Result.success();
    }

}
