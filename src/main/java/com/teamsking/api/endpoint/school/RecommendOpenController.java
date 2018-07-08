package com.teamsking.api.endpoint.school;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.school.RecommendOpenDto;
import com.teamsking.api.dto.school.RecommendOpenDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.school.RecommendOpen;
import com.teamsking.domain.service.school.RecommendOpenService;
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
@Api(tags = "学校-推荐课程管理操作接口")
public class RecommendOpenController extends BaseController {

    @Autowired
    RecommendOpenService recommendOpenService;

    /**
     * 获取学校-推荐课程管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "学校-推荐课程管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value ="页码", required = true, example = "1") ,
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/recommend_opens")
    public Result recommendOpenList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<RecommendOpen> recommendOpenList = recommendOpenService.list();
        List<RecommendOpenDto> recommendOpenDtoList = RecommendOpenDtoMapper.INSTANCE.entityListToDtoList(recommendOpenList);
        return Result.success().addData("pager",warpPage(recommendOpenDtoList));
    }

    /**
     * 添加学校-推荐课程管理
     * @param recommendOpen
     * @return
     */
    @ApiOperation(value = "添加学校-推荐课程", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recommendOpen", value = "学校-推荐课程", required = true, dataType = "RecommendOpenDto")
    })
    @PostMapping("/recommend_open")
    public Result addRecommendOpen(@RequestBody RecommendOpenDto recommendOpen){

        RecommendOpen recommendOpenEntity = RecommendOpenDtoMapper.INSTANCE.dtoToEntity(recommendOpen);
        recommendOpenService.save(recommendOpenEntity);
        return Result.success();
    }

    /**
     * 删除学校-推荐课程管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除学校-推荐课程", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学校-推荐课程的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/recommend_open/{id}")
    public Result removeRecommendOpen(@PathVariable("id") int id){

        recommendOpenService.remove(id);
        return Result.success();
    }

    /**
     * 修改学校-推荐合作课程管理
     * @param recommendOpen
     * @param id
     * @return
     */
    @ApiOperation(value = "修改学校-推荐课程", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recommendOpen", value = "学校-推荐课程", required = true, dataType = "RecommendOpenDto")
    })
    @PutMapping("/recommend_open/{id}")
    public Result modifyRecommendOpen(@RequestBody RecommendOpenDto recommendOpen,
                                             @PathVariable("id") int id){

        RecommendOpen recommendOpenEntity = RecommendOpenDtoMapper.INSTANCE.dtoToEntity(recommendOpen);
        recommendOpenEntity.setId(id);
        recommendOpenService.modify(recommendOpenEntity);
        return Result.success();
    }


}
