package com.teamsking.api.endpoint.school;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.school.RecommendCooperationDto;
import com.teamsking.api.dto.school.RecommendCooperationDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.school.RecommendCooperation;
import com.teamsking.domain.service.school.RecommendCooperationService;
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
@Api(tags = "学校-推荐合作课程操作接口")
public class RecommendCooperationController extends BaseController {

    @Autowired
    RecommendCooperationService recommendCooperationService;

    /**
     * 获取学校-推荐合作课程管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "学校-推荐合作课程管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value ="页码", required = true, example = "1") ,
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/recommend_cooperations")
    public Result recommendCooperationList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<RecommendCooperation> recommendCooperationList = recommendCooperationService.list();
        List<RecommendCooperationDto> recommendCooperationDtoList = RecommendCooperationDtoMapper.INSTANCE.entityListToDtoList(recommendCooperationList);
        return Result.success().addData("pager",warpPage(recommendCooperationDtoList));
    }

    /**
     * 添加学校-推荐合作课程管理
     * @param recommendCooperation
     * @return
     */
    @ApiOperation(value = "添加学校-推荐合作课程", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recommendCooperation", value = "学校-推荐合作课程", required = true, dataType = "RecommendCooperationDto")
    })
    @PostMapping("/recommend_cooperation")
    public Result addRecommendCooperation(@RequestBody RecommendCooperationDto recommendCooperation){

        RecommendCooperation recommendCooperationEntity = RecommendCooperationDtoMapper.INSTANCE.dtoToEntity(recommendCooperation);
        recommendCooperationService.save(recommendCooperationEntity);
        return Result.success();
    }

    /**
     * 删除学校-推荐合作课程管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除学校-推荐合作课程", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学校-推荐合作课程的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/recommend_cooperation/{id}")
    public Result removeRecommendCooperation(@PathVariable("id") int id){

        recommendCooperationService.remove(id);
        return Result.success();
    }

    /**
     * 修改学校-推荐合作课程管理
     * @param recommendCooperation
     * @param id
     * @return
     */
    @ApiOperation(value = "修改学校-推荐合作课程", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recommendCooperation", value = "学校-推荐合作课程", required = true, dataType = "RecommendCooperationDto")
    })
    @PutMapping("/recommend_cooperation/{id}")
    public Result modifyRecommendCooperation(@RequestBody RecommendCooperationDto recommendCooperation,
                                             @PathVariable("id") int id){

        RecommendCooperation recommendCooperationEntity = RecommendCooperationDtoMapper.INSTANCE.dtoToEntity(recommendCooperation);
        recommendCooperationEntity.setId(id);
        recommendCooperationService.modify(recommendCooperationEntity);
        return Result.success();
    }

}
