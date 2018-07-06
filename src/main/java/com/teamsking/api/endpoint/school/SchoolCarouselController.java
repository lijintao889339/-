package com.teamsking.api.endpoint.school;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.school.SchoolCarouselDto;
import com.teamsking.api.dto.school.SchoolCarouselDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.school.SchoolCarousel;
import com.teamsking.domain.service.school.SchoolCarouselService;
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
@Api(tags = "学校-轮播图操作接口")
public class SchoolCarouselController extends BaseController {

    @Autowired
    SchoolCarouselService schoolCarouselService;

    /**
     * 获取学校-轮播图管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "学校-轮播图管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value ="页码", required = true, example = "1") ,
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/school_carousels")
    public Result schoolCarouselList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<SchoolCarousel> schoolCarouselList = schoolCarouselService.list();
        List<SchoolCarouselDto> schoolCarouselDtoList = SchoolCarouselDtoMapper.INSTANCE.entityListToDtoList(schoolCarouselList);
        return Result.success().addData("pager",warpPage(schoolCarouselDtoList));
    }

    /**
     * 添加学校-轮播图管理
     * @param schoolCarousel
     * @return
     */
    @ApiOperation(value = "添加学校-轮播图管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "schoolCarousel", value = "学校-轮播图", required = true, dataType = "SchoolCarouselDto")
    })
    @PostMapping("/school_carousel")
    public Result addSchoolCarousel(@RequestBody SchoolCarouselDto schoolCarousel){

        SchoolCarousel schoolCarouselEntity = SchoolCarouselDtoMapper.INSTANCE.dtoToEntity(schoolCarousel);
        schoolCarouselService.save(schoolCarouselEntity);
        return Result.success();
    }

    /**
     * 删除学校-轮播图管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除学校-轮播图管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学校-轮播图的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/school_carousel/{id}")
    public Result removeSchoolCarousel(@PathVariable("id") int id){

        schoolCarouselService.remove(id);
        return Result.success();
    }

    /**
     * 修改学校-轮播图管理
     * @param id
     * @param schoolCarousel
     * @return
     */
    @ApiOperation(value = "修改学校-轮播图管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "schoolCarousel", value = "学校-轮播图", required = true, dataType = "SchoolCarouselDto")
    })
    @PutMapping("/school_carousel/{id}")
    public Result modifySchoolCarousel(@PathVariable("id") int id,
                                       @RequestBody SchoolCarouselDto schoolCarousel){

        SchoolCarousel schoolCarouselEntity = SchoolCarouselDtoMapper.INSTANCE.dtoToEntity(schoolCarousel);
        schoolCarouselEntity.setId(id);
        schoolCarouselService.modify(schoolCarouselEntity);
        return Result.success();
    }

}
